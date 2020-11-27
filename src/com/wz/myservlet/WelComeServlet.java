package com.wz.myservlet;

import java.io.IOException;
import java.io.OutputStream;

import com.wz.config.ServeletConfig;
import com.wz.http.HttpPrinter;
import com.wz.http.HttpRequest;
import com.wz.http.HttpResponse;
import com.wz.session.Session;

public class WelComeServlet implements Servlet {

	@Override
	public void init(ServeletConfig config) {
		// TODO 自动生成的方法存根
		System.out.println("WelcomeServlet 初始化");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) {
		try {
			System.out.println("WelComServlet Handle");
			System.out.println("在get之前："+((HttpRequest)req).JSESSIONID);
			Session session=req.getSession(false);
			if(session!=null) {
				HttpPrinter out=res.getPrintWriter();
				out.print("<h1>您已经注册过了！<h1/>");
				out.flush();
			}else {
				session=req.getSession();
				HttpPrinter out=res.getPrintWriter();
				out.print("<h1>恭喜您，注册成功!，您的SessionID为:"+session.getId()+"<h1/>");
				out.flush();
			}
			((HttpRequest)req).in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void destroy() {
		System.out.println("WelcomeServlet 生命周期已结束！");
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
