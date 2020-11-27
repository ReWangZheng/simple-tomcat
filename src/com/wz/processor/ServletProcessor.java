package com.wz.processor;

import com.wz.Server.Request;
import com.wz.Server.Response;
import com.wz.http.HttpResponse;
import com.wz.myservlet.Servlet;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;

public class ServletProcessor implements Processor{

	String path="com.wz.myservlet.";
	@Override
	public void process(ServletRequest req, ServletResponse res) {
		try {
			String uri=req.getUri();
			String ClsName=uri.substring(uri.lastIndexOf("/")+1);
			Class cls=Class.forName(path+ClsName);
			Servlet servlet=(Servlet) cls.newInstance();
			servlet.service(req, res);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

}
