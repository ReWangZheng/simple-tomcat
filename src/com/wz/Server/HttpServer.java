package com.wz.Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.server.ServerCloneException;

import com.wz.processor.Processor;
import com.wz.processor.ServletProcessor;
import com.wz.processor.StaticResourceProcessor;

public class HttpServer {
	public static void main(String[] args) {
		new HttpServer().work();
	}
	private int port=80;
	private String ip="127.0.0.1";
	private ServerSocket ss;
	private boolean isclose=false;
	
	public static String webroot;
	public static String NoFile;
	static {
		HttpServer.webroot="."+File.separator+"webroot";
		HttpServer.NoFile="."+File.separator+"webroot"+File.separator+"NoFile"+File.separator+"404.html";
	}
	public HttpServer() {
		try {
			ss=new ServerSocket(port, 100, InetAddress.getByName(ip));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void work() {
		// 一直接收HTTP请求
		try {
			while(true&&!isclose) {
				//得到请求
				Request request=this.accept();
				if(request==null) {
					continue;
				}
				//对请求信息进行处理
				request.parse();
				//创建响应信息
				Response res=new Response(request);
				//获取URI
				String uri=request.getUri();
				//判断请求资源类型
				Processor processor=null;
				if(uri.startsWith("/servlet/")) {
					processor=(Processor) new ServletProcessor();
				}else {
					processor=(Processor)new StaticResourceProcessor();
				}
				processor.process(request, res);
				request.cs.close();
			}
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public Request accept() {
		try {
			Socket cs;
			cs = ss.accept();
			InputStream in=cs.getInputStream();
			Request request=new Request(cs);
			return request;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
