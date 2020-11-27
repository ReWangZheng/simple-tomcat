package com.wz.myservlet;

import java.io.IOException;
import java.io.OutputStream;

import com.wz.config.ServeletConfig;
import com.wz.http.HttpPrinter;
import com.wz.http.HttpRequest;
import com.wz.http.HttpResponse;
import com.wz.session.Session;

public class WelComeServlet implements Servlet {

	@Override
	public void init(ServeletConfig config) {
		// TODO �Զ����ɵķ������
		System.out.println("WelcomeServlet ��ʼ��");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) {
		try {
			System.out.println("WelComServlet Handle");
			System.out.println("��get֮ǰ��"+((HttpRequest)req).JSESSIONID);
			Session session=req.getSession(false);
			if(session!=null) {
				HttpPrinter out=res.getPrintWriter();
				out.print("<h1>���Ѿ�ע����ˣ�<h1/>");
				out.flush();
			}else {
				session=req.getSession();
				HttpPrinter out=res.getPrintWriter();
				out.print("<h1>��ϲ����ע��ɹ�!������SessionIDΪ:"+session.getId()+"<h1/>");
				out.flush();
			}
			((HttpRequest)req).in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void destroy() {
		System.out.println("WelcomeServlet ���������ѽ�����");
	}

	@Override
	public MyServletConfig getConfig() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO �Զ����ɵķ������
		return null;
	}

}
