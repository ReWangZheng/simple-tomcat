package com.wz.myservlet;

import com.wz.config.ServeletConfig;
import com.wz.http.HttpResponse;

public class StaticResourseServlet implements Servlet{

	@Override
	public void init(ServeletConfig config) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) {
		((HttpResponse)res).SendStaticResouse();
	}

	@Override
	public void destroy() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public MyServletConfig getConfig() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO �Զ����ɵķ������
		return null;
	}

}
