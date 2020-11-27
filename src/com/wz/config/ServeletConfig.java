package com.wz.config;

import java.util.Set;

import com.wz.catalina.Context;

public interface ServeletConfig {
	public Context getServletContext();
	public String getServletName();
	public String getInitParamter(String name );
	public Set<String> getInitParamterNames();
}
