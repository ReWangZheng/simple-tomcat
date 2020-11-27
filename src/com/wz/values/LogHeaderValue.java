package com.wz.values;

import java.util.HashMap;
import java.util.Set;

import com.wz.catalina.Value;
import com.wz.catalina.ValueContext;
import com.wz.http.HttpRequest;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;

public class LogHeaderValue implements Value {
	@Override
	public String getInfo() {
		return "�÷������ڴ�ӡ�������ͷ����Ϣ";
	}

	@Override
	public void invoke(ServletRequest req, ServletResponse res, ValueContext v) {
		// TODO �Զ����ɵķ������
		Set<String>  map=req.getheaderNames();
		for(String name:map) {
			System.out.println(getInfo()+":"+name+": "+req.getHeader(name));
		}
		v.invokeNext(req, res);
	}

}
