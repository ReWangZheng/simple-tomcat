package com.wz.myservlet;

import com.wz.config.ServeletConfig;
import com.wz.http.HttpResponse;

public class StaticResourseServlet implements Servlet{

	@Override
	public void init(ServeletConfig config) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) {
		((HttpResponse)res).SendStaticResouse();
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
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
