package com.wz.processor;

import com.wz.Server.Request;
import com.wz.Server.Response;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;

public interface Processor {
	public void process(ServletRequest req, ServletResponse res);
}
