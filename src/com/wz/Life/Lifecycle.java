package com.wz.Life;

import javax.print.DocFlavor.STRING;


// ʵ�ָýӿڵģ��ͻ�ӵ��һ����������������
public interface Lifecycle{
	public static final String START_EVENT="start";
	public static final String BEFORE_START_EVENT="before_start";
	public static final String AFTER_START_EVENET="after_start";
	public static final String STOP_EVENT="stop";
	public static final String BEFORE_STOP_EVENT="before_stop";
	public static final String AFTER_STOP_EVENT="after_stop";
	public void addLifecycleListener(LifecycleListener l);
	public LifecycleListener[] findLifecycleListeners();
	public void removeLifecycleListener(LifecycleListener l);
	public void start();
	public void stop();
}
