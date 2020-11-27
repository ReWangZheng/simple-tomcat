package com.wz.Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.wz.http.HttpPrinter;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;
import com.wz.util.Cookie;

public class Response implements ServletResponse{
	private Request request;
	private HashMap<String, String> map;
	
	public Response(Request request) {
		this.request=request;
		map=new HashMap<String,String>();
		map.put("404Header","HTTP/1.1 404 File Not Found\r\n");
		map.put("200Header","HTTP/1.1 200 ok\r\n");
		map.put("Content-Type", "text/html\r\n");
	}
	public void SendStaticResouse() {
		try {
			String uri=request.getUri(); // 得到请求的uri
			System.out.println(request.getContent()+"请求一次");
			if(uri.endsWith("/")) {
				uri=uri+"index.html";
			}
			File file=new File(HttpServer.webroot, uri); //得到目标文件
			String header=null;
			if(file.exists()) {
				if(file.getName().endsWith("jpg")) {
					map.put("Content-Type", "image/jpg\r\n");
				}
				header=map.get("200Header")
								+"Content-Type: "+map.get("Content-Type")
								+"Content-Length: "+file.length()+"\r\n"
								+"\r\n";
			}else {
				file=new File(HttpServer.NoFile);
				header=map.get("404Header")
						+"Content-Type: "+map.get("Content-Type")
						+"Content-Length: "+file.length()+"\r\n"
						+"\r\n";
			}
			System.out.println(header);
			FileInputStream in=new FileInputStream(file); //得到文件流 
			byte[] b=header.getBytes(); //先把文件读出来再说
			OutputStream out=request.getOutPutStream(); //得到与浏览器的输出流
			out.write(b); //写出去
			//下面就开始写文件
			byte[] buf=new byte[1024];
			int ch=in.read(buf);
			while(ch!=-1) {
				out.write(buf,0,ch);
				ch=in.read(buf);
			}
			out.flush();
			request.close();
			in.close();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	@Override
	public void setRequest(ServletRequest request) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public HttpPrinter getPrintWriter() {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public void setHeader(String key, String v) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void addCookie(Cookie cookie) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public String getHeader(String s) {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public Cookie getCookie(String s) {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public HashMap<String, String> getHeaders() {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public ArrayList<Cookie> getCookies() {
		// TODO 自动生成的方法存根
		return null;
	}
}
