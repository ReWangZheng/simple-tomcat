package com.wz.container;

import java.net.URLClassLoader;

import javax.management.relation.RoleUnresolved;

import com.wz.catalina.Mapper;
import com.wz.loader.Loader;
import com.wz.log.Logger;
import com.wz.myservlet.Servlet;
import com.wz.myservlet.ServletManager;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;
import com.wz.processor.StaticResourceProcessor;
import com.wz.session.Manager;

public class SimpleContainer implements Container{

	@Override
	public String getInfo() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Loader getLoader() {
		return null;
	}

	@Override
	public void setLoader(Loader loader) {
		
	}

	@Override
	public void setManager(Manager manager) {
		// TODO 自动生成的方法存根
	}

	@Override
	public void getCluster() {
		// TODO 自动生成的方法存根
	}

	@Override
	public void setCluster() {
		// TODO 自动生成的方法存根
	}

	@Override
	public String getName() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Container getParent() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void setParent(Container c) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Loader getParentClassLoader() {
		return null;
	}

	@Override
	public void setParentClassLoader(Loader loader) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setRealm() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void getResources() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setReources() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addChild(Container c) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addContainerListener() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addMapper(Mapper mapper) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addPropertyChangeListener() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Container findChild(String name) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void findContainerListeners() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Mapper findMapper(String protocol) {
		return null;
	}

	@Override
	public void findMappers() {
		// TODO 自动生成的方法存根
		
	}
	private String path="com.wz.myservlet.";
	@Override
	public void invoke(ServletRequest req, ServletResponse res) {
		try {
			String uri=req.getUri();
			String ClsName=path+uri.substring(uri.lastIndexOf("/")+1);
			if(ServletManager.exit(ClsName)) {
				Class cls=Class.forName(ClsName);
				Servlet servlet=(Servlet) cls.newInstance();
				servlet.service(req, res);				
			}else {
				new StaticResourceProcessor().process(req, res);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		
	}
	@Override
	public void setLogger(Logger loger) {
	}
	@Override
	public Logger getLogger() {
		return null;
	}

}
