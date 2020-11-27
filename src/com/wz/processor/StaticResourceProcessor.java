package com.wz.processor;

import com.wz.Server.Request;
import com.wz.Server.Response;
import com.wz.http.HttpResponse;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;

public class StaticResourceProcessor implements Processor{
	@Override
	public void process(ServletRequest req, ServletResponse res) {
		((HttpResponse)res).SendStaticResouse();
	}
	
}
