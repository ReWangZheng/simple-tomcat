package com.wz.catalina;

import com.wz.container.Container;
import com.wz.session.Manager;

public interface Context extends Container{
	public void addWrapper(Wrapper m);
	public Wrapper CreateWrapper();
	public void addServletMapping(String root,String name);
	public String findServletMapping(String pattern);
	public Manager getManager();
}
