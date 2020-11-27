package com.wz.loader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

import com.wz.Life.Lifecycle;
import com.wz.Life.LifecycleListener;
import com.wz.catalina.Context;
import com.wz.container.Container;

public class WebappLoader implements Loader,Lifecycle{
	private WebappClassLoader loader;
	private HashSet<String> set=new HashSet<>();
	public WebappLoader() {
		
	}
	
	@Override
	public ClassLoader getClassLoader() {
		return loader;
	}

	@Override
	public Container getContainer() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void setContainer(Container c) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Context getDefaultContext() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void setDeafultContext(Context c) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public boolean getDelegate() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void setDelegate(boolean delegate) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public String setInfo() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean getReloadable() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void addRepository(String repository) {
		set.add(repository);
	}

	@Override
	public String[] findRepositories() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean modified() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void removePropertyChangerListener() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addPropertyChangeListener() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addLifecycleListener(LifecycleListener l) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public LifecycleListener[] findLifecycleListeners() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void removeLifecycleListener(LifecycleListener l) {
		// TODO 自动生成的方法存根
	}
	
	public WebappClassLoader createWebappClassLoader() {
		URL[] urls=new URL[set.size()];
		int i=0;
		try {
			for(String repository:set) {
				urls[i]= new URL("file",null,repository);
				i++;
			}
			WebappClassLoader classLoader=new WebappClassLoader(urls);
			return classLoader;
		} catch (MalformedURLException e) {
			getContainer().getLogger().log(e.getMessage());
		}
			
		return null;
	}

	@Override
	public void start() {
		System.out.println("the class loader has been ready");
		addRepository("./bin");
		this.loader=createWebappClassLoader();
		
	}
	@Override
	public void stop() {
		System.out.println("the class loader will be destory");
		
	}

}
