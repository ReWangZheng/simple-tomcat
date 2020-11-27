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
		// TODO 自动生成的方法存根
		return sc.getServletContext();
	}
	@Override
	public String getServletName() {
		// TODO 自动生成的方法存根
		return sc.getServletName();
	}
	@Override
	public String getInitParamter(String name) {
		// TODO 自动生成的方法存根
		return sc.getInitParamter(name);
	}
	@Override
	public Set<String> getInitParamterNames() {
		// TODO 自动生成的方法存根
		return sc.getInitParamterNames();
	}
	

}
