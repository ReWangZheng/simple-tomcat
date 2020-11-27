package com.wz.Life;

import java.util.EventObject;

import java.awt.Event;


// 该接口可以是用来定义生命周期时间的
public class LifecycleEvent extends EventObject{
	private String type=null;
	private Object data=null;
	private Lifecycle lifecycle=null;
	public LifecycleEvent(Lifecycle lifecycle,String type) {
		this(lifecycle, type, null);
		
	}
	public LifecycleEvent(Lifecycle lifecycle,String type,Object date) {
		super(lifecycle);
		this.lifecycle=lifecycle;
		this.type=type;
		this.data=date;
	}
	
	public String getType() {
		return type;
	}
	
	public Object getData() {
		return data;
	}

}
