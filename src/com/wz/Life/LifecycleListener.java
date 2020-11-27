package com.wz.Life;


// 该接口就是监听器了，当事件发生了，就会触发下面的方法

public interface LifecycleListener {
	public void lifecycleEvent(LifecycleEvent event);
}
