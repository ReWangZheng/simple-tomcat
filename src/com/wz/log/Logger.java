package com.wz.log;

import com.wz.container.Container;

public interface Logger {
	public static final int FATAL=Integer.MIN_VALUE;
	public static final int ERROR=1;
	public static final int WARANG=2;
	public static final int INFORMATION=2;
	public static final int DEBUG=4;
	public Container getConainer();
	public void setConainer(Container container);
	public String getInfo();
	public int getVerbosity();
	public void setVerbosity(String verbosity);
	public void addPropertyChangeListener();
	public void log(String message);
	public void log(String message,int verbosity);
	public void log(String message,Throwable able,int verbosity);
}
