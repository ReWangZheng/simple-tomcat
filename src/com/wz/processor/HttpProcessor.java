package com.wz.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.security.Principal;
import java.util.ArrayList;

import javax.management.Query;
import javax.sound.midi.Soundbank;
import javax.sound.sampled.Line;

import com.wz.connector.Connector;
import com.wz.connector.HttpConnector;
import com.wz.http.HttpRequest;
import com.wz.http.HttpResponse;
import com.wz.util.Cookie;


public class HttpProcessor implements Runnable{
	private HttpRequest request; //请求对象
	private HttpResponse response; //响应对象
	private Connector connector; //连接器
	boolean KeepAlive=true; //是否保活连接
	private boolean available=false;
	private boolean stop=false;
	private Socket socket;
	private boolean OK=true; //判断是否出现错误

	public HttpProcessor(HttpConnector connector) {
		this.connector=connector;
		new Thread(this).start();
	}
	public synchronized Socket await() { // 这个方法运行在处理器进程中
		while(!available) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Socket s=this.socket;
		available=false;
		notifyAll();
		return s;
	}
	
	public synchronized void assign(Socket s) { // 这个方法运行在连接器线程中
		while(available) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.socket=s;
		available=true;
		notifyAll();
	}
	static int i =0;
	@Override
	//这也被称之为处理器线程
	public void run() {
		while(!stop) {
			Socket socket=await();
			if (socket==null) {
				continue;
			}
			process(socket); // 处理socket连接
			//处理完成之后，将其回收
			connector.recycle(this);
		}
	}
	public  void process(Socket s) {
		try {
			InputStream in=s.getInputStream(); //得到该Tcp连接的输入流
			OutputStream out=s.getOutputStream();//得到该Tcp连接的输出流
			int i=0;
			this.OK=true;
			while(OK && this.KeepAlive) {
				request=HttpRequest.fetchRequest(in); //在Request对象池里取出一个对象
				response=HttpResponse.fetchResponse(out);//在Response对象池里取出一个对象
				response.setRequest(request); //设置请求的对象
				parseRequest(in); //解析请求
				String islive=request.getHeader("Connection");
				if(islive!=null&&!islive.toLowerCase().equals("keep-alive")) {
					this.KeepAlive=false;
				}
				//如果没有出现任何错误，那么就执行向容器下发请求
				if(this.OK) {
					connector.getContainer().invoke(request, response);	
				}
				//回收对象
				request.recycle(); 
				response.recycle();
			}
			s.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String normalizeUri(String uri) {
		uri=uri.replace("\\", "/");
		return uri;
	}
	
	
	// 解析http协议请求头部
	public void parseHeader(String[] headerMes) {
		for(String s:headerMes) {
			int i=s.indexOf(": ");
			if(i<0) {
				continue;
			}
			String key=s.substring(0,i);
			String value=s.substring(i+2);
			request.addHeader(key, value);
		}
		ParseCookie(request.getHeader("Cookie"));
		if(request.getRequestedMethod().equals("POST")&&request.getHeader("Content-Type").equals("application/x-www-form-urlencoded")) {
			request.setparameterMes(headerMes[headerMes.length-1]);
		}
		
	}
	//解析Cookie
	public void ParseCookie(String cookie) {
		if(cookie==null) {
			return;
		}
		String[] cookies=cookie.split(";");
		for(String c:cookies) {
			String[] key_value=c.split("=");
			if(key_value[0].trim().toLowerCase().equals("jsessionid")) {
				request.setRequestedSessionId(key_value[1]);
			}
			request.addCookie(new Cookie(key_value[0], key_value[1]));
		}
	}
	
	public void stop() {
		stop=true;
	}
	
	//解析请求
	public void parseRequest(InputStream in) {
		try {
			if(this.socket.isClosed()) {
				this.OK=false;
				return;
			}
			String contentMes="";
			byte[] b=new byte[1024];
			int ch=0;
			while((ch=in.read(b))==b.length) {
				contentMes=contentMes+new String(b,0,ch);
			}
			if(ch<b.length&&ch>0) {
				contentMes=contentMes+new String(b,0,ch);
			}
			if(ch==-1) {
				this.OK=false;
				return;
			}
			String[] headerMes=contentMes.split("\r\n");
			//解析请求方式
			String h1=headerMes[0];
			int h1_index=h1.indexOf(" ");
			int h2_index=h1.lastIndexOf(" ");
			//赋值
			String method=h1.substring(0,h1_index);
			String AllURI=h1.substring(h1_index+1,h2_index);
			String protocol=h1.substring(h2_index+1);
			//判断请求是否有效
			if(method=="" || AllURI=="") {
				this.OK=false;
				return;
			}
			//设置请求方法 Get or Post
			request.setRequestedMethod(method);
			//设置请求协议版本
			request.setProtocol(protocol);
			// 在来解析Querry
			int h3_index=AllURI.indexOf("?");
			String uri;
			if(h3_index!=-1) {
				uri=AllURI.substring(0,h3_index);
				String Querry=AllURI.substring(h3_index+1);
				request.setQuerryString(Querry);
				// 判断是否含有Jsessionid
				String match="jsessionid=";
				int i=Querry.indexOf(match);
				System.out.println("????"+i);
				if(i!=-1) {
					String rest=Querry.substring(i+match.length());
					i=rest.indexOf("&");
					String id=null;
					if(i!=-1) {
						id=rest.substring(0,i);
					}else {
						id=rest;
					}
					request.setRequestedSessionId(id);
				}
			}else {
				uri=AllURI;
			}
			//如果请求地址为NULL，则出现错误
			if(uri==null || uri.equals("")) {
				this.OK=false;
			}
			// 标准化uri
			uri=normalizeUri(uri);
			//设置请求uri
			request.setInetAddress(socket.getInetAddress());
			request.setRequestedUri(uri);
			parseHeader(headerMes);
		}catch (SocketException e) {
			this.OK=false;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
