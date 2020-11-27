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
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void setContainer(Container c) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public Context getDefaultContext() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void setDeafultContext(Context c) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public boolean getDelegate() {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public void setDelegate(boolean delegate) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public String setInfo() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public boolean getReloadable() {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public void addRepository(String repository) {
		set.add(repository);
	}

	@Override
	public String[] findRepositories() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public boolean modified() {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public void removePropertyChangerListener() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void addPropertyChangeListener() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void addLifecycleListener(LifecycleListener l) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public LifecycleListener[] findLifecycleListeners() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void removeLifecycleListener(LifecycleListener l) {
		// TODO �Զ����ɵķ������
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
