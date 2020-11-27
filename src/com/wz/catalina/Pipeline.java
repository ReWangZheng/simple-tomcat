package com.wz.catalina;

import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;

public interface Pipeline {
	public void invoke(ServletRequest req,ServletResponse res);
	public Value getBasic();
	public void addValue(Value v);
	public void setBasic(Value v);
	public Value[] getValues();
	public void removeValue(Value value);
}
