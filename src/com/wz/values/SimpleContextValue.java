package com.wz.values;

import com.wz.catalina.Contained;
import com.wz.catalina.Context;
import com.wz.catalina.Mapper;
import com.wz.catalina.Value;
import com.wz.catalina.ValueContext;
import com.wz.catalina.Wrapper;
import com.wz.container.Container;
import com.wz.http.HttpRequest;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;

public class SimpleContextValue implements Value,Contained{
	protected Container container;
	@Override
	public void invoke(ServletRequest req, ServletResponse res, ValueContext v) {
		HttpRequest hreq=(HttpRequest) req;
		Context context=(Context) getContained();
		Mapper mapper=context.findMapper(hreq.getProtocol());
		Wrapper wrapper=(Wrapper) mapper.map(req, true);
		if(wrapper!=null) {
			wrapper.invoke(hreq, res);
		}
	}

	@Override
	public String getInfo() {
		return "";
	}

	@Override
	public Container getContained() {
		return this.container;
	}

	@Override
	public void setContainer(Container c) {
		this.container=c;
	}

}
