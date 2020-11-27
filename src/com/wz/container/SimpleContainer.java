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
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
	}

	@Override
	public void getCluster() {
		// TODO �Զ����ɵķ������
	}

	@Override
	public void setCluster() {
		// TODO �Զ����ɵķ������
	}

	@Override
	public String getName() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public Container getParent() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void setParent(Container c) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public Loader getParentClassLoader() {
		return null;
	}

	@Override
	public void setParentClassLoader(Loader loader) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void setRealm() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void getResources() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void setReources() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void addChild(Container c) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void addContainerListener() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void addMapper(Mapper mapper) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void addPropertyChangeListener() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public Container findChild(String name) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void findContainerListeners() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public Mapper findMapper(String protocol) {
		return null;
	}

	@Override
	public void findMappers() {
		// TODO �Զ����ɵķ������
		
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
