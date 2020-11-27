package com.wz.catalina;

import com.wz.container.Container;
import com.wz.myservlet.Servlet;

public interface Wrapper extends Container{
	public void load();
	public Servlet allocate();
	public void setServletClass(String name);
}
