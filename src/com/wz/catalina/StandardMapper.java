package com.wz.catalina;

import com.wz.container.Container;
import com.wz.myservlet.ServletRequest;

public class StandardMapper implements Mapper {
	private String protocol=null;
	private StandardContext context=null;
	@Override
	public Container getContainer() {
		return this.context;
	}

	@Override
	public void setContainer(Container c) {
		this.context=(StandardContext) c;
	}

	@Override
	public String getProtocol() {
		return this.protocol;
	}
	

	@Override
	public void setProtocol(String protocol) {
		this.protocol=protocol;
	}

	@Override
	public Container map(ServletRequest request, boolean update) {
		String pattern=request.getUri();
		String name=context.findServletMapping(pattern);
		Wrapper wrapper=(Wrapper) context.findChild(name);
		if(wrapper instanceof JspWrapper) {
			((JspWrapper)wrapper).setJspPath(pattern);
		}
		return wrapper;
	}

}
