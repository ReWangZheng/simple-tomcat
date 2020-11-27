package com.wz.jsp;

import com.sun.tools.doclets.internal.toolkit.Configuration;
import com.wz.config.ServeletConfig;
import com.wz.myservlet.JspServlet;
import com.wz.myservlet.MyServletConfig;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;

public abstract class JspServletAbs implements JspServlet{
	ServeletConfig config;
	@Override
	public void init(ServeletConfig config) {
		this.config = config;
	}

	public abstract void service(ServletRequest req, ServletResponse res);

	@Override
	public void destroy() {
	
	}

	@Override
	public MyServletConfig getConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return "JSP";
	}

}
