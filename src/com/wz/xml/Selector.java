package com.wz.xml;

public interface Selector {
	public Node[] select(String path);
	public Node selectByname(String name);
}
