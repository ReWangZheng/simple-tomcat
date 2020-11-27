package com.wz.myservlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketException;
import java.security.Principal;

import com.wz.Server.Request;
import com.wz.Server.Response;
import com.wz.config.ServeletConfig;
import com.wz.http.HttpPrinter;
import com.wz.http.HttpRequest;
import com.wz.http.HttpResponse;
import com.wz.session.Session;
import com.wz.util.Constant;
import com.wz.util.Cookie;

public class MyServlet implements Servlet{
	
	public MyServlet(){
		
	}
	
	@Override
	public void init(ServeletConfig config) {
		System.out.println("Myservlet 初始化");
		String name=config.getInitParamter("name");
		System.out.println(config.getServletName());
	}
	@Override
	public void service(ServletRequest req, ServletResponse res) {
		System.out.println("Myserlet Handle");
		res.addCookie(new Cookie("username", "zhangsan"));
		String mes=((HttpResponse)res).getMessage();
		Session session=req.getSession(false);
		try {
			HttpPrinter out=res.getPrintWriter();
			out.setAutoflush(true);
			File file=new File("./webroot/index.html");
			if(session==null) {
				out.println("<h1>您没有权限访问!<h1/>");
				return;
			}
			out.writeFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void destroy() {
		System.out.println("Myservlet 生命周期已结束！");
		
	}
	@Override
	public MyServletConfig getConfig() {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public String getServletInfo() {
		// TODO 自动生成的方法存根
		return null;
	}

	
	
}
