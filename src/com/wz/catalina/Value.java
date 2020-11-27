package com.wz.catalina;

import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;

public interface Value {
	public void invoke(ServletRequest req,ServletResponse res,ValueContext v);
	public String getInfo();

}
