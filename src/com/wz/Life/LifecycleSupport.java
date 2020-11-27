package com.wz.Life;

import java.util.ArrayList;

import javax.xml.bind.Marshaller.Listener;


// �����������ע���¼���������
public class LifecycleSupport {
	private Lifecycle lifecycle;
	private ArrayList<LifecycleListener> listeners=new ArrayList<>();
	// ����������洢LifecycleSuport�Ĺ�����
	public LifecycleSupport(Lifecycle lifecycle) {
		this.lifecycle=lifecycle;
	}
	
	// �������������Ӽ�������
	public void addLifecycleListener(LifecycleListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}
	
	public LifecycleListener[] findLifecycleListeners() {
		return listeners.toArray(new LifecycleListener[]{});
	}
	//�����������ִ�д����¼���
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
