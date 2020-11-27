package com.wz.catalina;

import com.wz.container.Container;

//该接口的实现类可以通过接口方法与一个servlet容器相关联
public interface Contained {
	public Container getContained();
	public void setContainer(Container c);
}
