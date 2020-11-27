package com.wz.Life;

import java.util.ArrayList;

import javax.xml.bind.Marshaller.Listener;


// 这个类是用来注册事件监听器的
public class LifecycleSupport {
	private Lifecycle lifecycle;
	private ArrayList<LifecycleListener> listeners=new ArrayList<>();
	// 这个是用来存储LifecycleSuport的管理者
	public LifecycleSupport(Lifecycle lifecycle) {
		this.lifecycle=lifecycle;
	}
	
	// 这个就是用来添加监听器的
	public void addLifecycleListener(LifecycleListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}
	
	public LifecycleListener[] findLifecycleListeners() {
		return listeners.toArray(new LifecycleListener[]{});
	}
	//这个就是用来执行触发事件的
	public void fireLifecycleEvent(String type,Object data) {
		LifecycleEvent event=new LifecycleEvent(lifecycle, type);
		synchronized (listeners) {
			for(LifecycleListener listener:listeners) {
				listener.lifecycleEvent(event);
			}
		}
	}
	public void removeLifecycleListener(LifecycleListener listener) {
		synchronized(listener) {
			this.listeners.remove(listener);
		}
	}
}
