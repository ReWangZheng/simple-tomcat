package com.wz.loader;

public interface Reloader {
	public void addRepository(String repository);
	public String[] findRepository();
	
	public boolean modified(); //the most import method
	
	
}
