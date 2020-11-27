package com.wz.values;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

import com.wz.catalina.Contained;
import com.wz.catalina.Value;
import com.wz.catalina.ValueContext;
import com.wz.catalina.Wrapper;
import com.wz.container.Container;
import com.wz.myservlet.MyServlet;
import com.wz.myservlet.Servlet;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;
import com.wz.myservlet.StaticResourseServlet;

public class SimpeWrapperValue implements Value,Contained{
	private Wrapper wraper;
	@Override
	public void invoke(ServletRequest req, ServletResponse res, ValueContext v) {
		Servlet servlet=wraper.allocate();
		servlet.service(req, res);
	}
	@Override
	public String getInfo() {
		// TODO �Զ����ɵķ������
		return "�÷�����һ�������У���������Wrapper�е�servlet���Ӷ�����request";
	}
	@Override
	public Container getContained() {
		return wraper;
	}
	@Override
	public void setContainer(Container c) {
		this.wraper=(Wrapper) c;
	}

}
