package com.wz.catalina;

import com.wz.container.Container;

//�ýӿڵ�ʵ�������ͨ���ӿڷ�����һ��servlet���������
public interface Contained {
	public Container getContained();
	public void setContainer(Container c);
}
