package com.wz.catalina;

import com.wz.container.Container;
import com.wz.myservlet.ServletRequest;

public interface Mapper{
	public Container getContainer();
	public void setContainer(Container c);
	public String getProtocol();
	public void setProtocol(String protocol);
	public Container map(ServletRequest request,boolean update);
}
