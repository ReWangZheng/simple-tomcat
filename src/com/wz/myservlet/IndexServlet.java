package com.wz.myservlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.wz.config.ServeletConfig;
import com.wz.util.Cookie;

public class IndexServlet implements Servlet{

	@Override
	public void init(ServeletConfig config) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) {

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
