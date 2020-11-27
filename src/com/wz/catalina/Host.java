package com.wz.catalina;

import com.wz.container.Container;

public interface Host extends Container{
	public String getAppBase();
	public void setAppBase();
	public boolean getAutoDeploy();
	public void setAutoDeploy(boolean autoDeploy);
	
	public void addDefaultContext(Context context);
	public Context getDefaultContext();
	
	public String getName();
	public void setName(String name);
	
	public Context importDefaultContext(Context context);
	
	public Context map(String url);
	
	
}
