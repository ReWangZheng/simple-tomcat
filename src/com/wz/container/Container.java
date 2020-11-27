package com.wz.container;

import java.net.URLClassLoader;

import com.wz.catalina.Mapper;
import com.wz.loader.Loader;
import com.wz.log.Logger;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;
import com.wz.session.Manager;

public interface Container {
	public String getInfo();
	public Loader getLoader();
	public void setLoader(Loader loader);
	public void setManager(Manager manager);
	public void getCluster();
	public void setCluster();
	public String getName();
	public void setName(String name);
	public Container getParent();
	public void setParent(Container c);
	public Loader getParentClassLoader();
	public void setParentClassLoader(Loader loader);
	public void setRealm();
	public void getResources();
	public void setReources();
	public void addChild(Container c);
	public void addContainerListener();
	public void addMapper(Mapper mapper);
	public void addPropertyChangeListener();
	public Container findChild(String name);
	public void findContainerListeners();
	public Mapper findMapper(String protocol);
	public void findMappers();
	public void invoke(ServletRequest req, ServletResponse res);
	public void setLogger(Logger loger);
	public Logger getLogger();
}
