package com.wz.values;

import com.wz.catalina.Value;
import com.wz.catalina.ValueContext;
import com.wz.http.HttpRequest;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;

public class LogClientValue implements Value{
	@Override
	public String getInfo() {
		return "该方法用于打印客户端的ip地址";
	}
	@Override
	public void invoke(ServletRequest req, ServletResponse res, ValueContext v) {
		HttpRequest hq=(HttpRequest) req;
		System.out.println(getInfo()+":"+hq.getInetAddress());
		v.invokeNext(req, res);
	}
	
}
