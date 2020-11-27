package com.wz.myservlet;

import com.wz.Server.Request;
import com.wz.Server.Response;
import com.wz.config.ServeletConfig;

public interface Servlet {
	public void init(ServeletConfig config);
	public void service(ServletRequest req, ServletResponse res);
	public void destroy();
	public MyServletConfig getConfig();
	public String getServletInfo();

}
