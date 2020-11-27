package com.wz.loader;

import com.wz.catalina.Context;
import com.wz.container.Container;

public interface Loader {
	public ClassLoader getClassLoader();
	public Container getContainer();
	public void setContainer(Container c);
	
	public Context getDefaultContext();
	public void setDeafultContext(Context c);
	
	public boolean getDelegate();
	public void setDelegate(boolean delegate);
	
	public String setInfo();
	public boolean getReloadable();
	
	public void addRepository(String repository);
	public String[] findRepositories();
	
	public boolean modified();

	public void removePropertyChangerListener();
	public void addPropertyChangeListener();

}
