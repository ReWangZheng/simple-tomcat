package com.wz.catalina;

import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;

public interface ValueContext {
	public void invokeNext(ServletRequest req,ServletResponse res);
	public String getInfo(); // 返回实现信息

}
