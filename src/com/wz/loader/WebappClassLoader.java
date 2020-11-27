package com.wz.loader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

public class WebappClassLoader extends URLClassLoader{
	private HashMap<String, Class<?>> cash;
	
	public WebappClassLoader(URL[] urls) {
		super(urls);
		cash=new HashMap<>();
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if(cash.containsKey(name)) {
			return cash.get(name);
		}
		Class<?> c =super.loadClass(name);
		cash.put(name,c);
		return c;
	}
}
