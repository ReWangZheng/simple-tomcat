package com.wz.connector;

import com.wz.container.Container;
import com.wz.myservlet.Servlet;
import com.wz.processor.HttpProcessor;

public interface Connector {
	public Container getContainer();
	public void setContainer(Container container);
	public void createRequest();
	public void createResponse();
	public void recycle(HttpProcessor httpProcessor);
}
