package com.wz.config;

import java.util.Set;

import com.wz.catalina.Context;

public class StandardServletConfigFade implements ServeletConfig {
	private ServeletConfig sc;
	public StandardServletConfigFade(ServeletConfig sc) {
		this.sc=sc;
	}
	@Override
	public Context getServletContext() {
		// TODO �Զ����ɵķ������
		return sc.getServletContext();
	}
	@Override
	public String getServletName() {
		// TODO �Զ����ɵķ������
		return sc.getServletName();
	}
	@Override
	public String getInitParamter(String name) {
		// TODO �Զ����ɵķ������
		return sc.getInitParamter(name);
	}
	@Override
	public Set<String> getInitParamterNames() {
		// TODO �Զ����ɵķ������
		return sc.getInitParamterNames();
	}
	

}
