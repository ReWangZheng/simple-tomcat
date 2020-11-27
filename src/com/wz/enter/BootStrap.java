package com.wz.enter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.Principal;
import java.util.Properties;

import com.wz.catalina.Contained;
import com.wz.catalina.Context;
import com.wz.catalina.JspWrapper;
import com.wz.catalina.Mapper;
import com.wz.catalina.StandardContext;
import com.wz.catalina.StandardMapper;
import com.wz.catalina.Pipeline;
import com.wz.catalina.StandardWrapper;
import com.wz.catalina.Wrapper;
import com.wz.connector.HttpConnector;
import com.wz.container.SimpleContainer;
import com.wz.loader.WebappLoader;
import com.wz.log.FileLogger;
import com.wz.log.Logger;
import com.wz.myservlet.MyServlet;
import com.wz.myservlet.ServletManager;
import com.wz.myservlet.WelComeServlet;
import com.wz.session.Session;
import com.wz.session.StandardManager;
import com.wz.values.LogClientValue;
import com.wz.values.LogHeaderValue;
import com.wz.values.SimpeWrapperValue;

public class BootStrap {
	public static void main(String[] args) {
		try {
			//�½�һ��������
			HttpConnector connector=new HttpConnector();
			//��������wrapper����
			Wrapper wrapper1=new StandardWrapper("MyServlet");
			Wrapper wrapper2=new StandardWrapper("WelcomeServlet");
			Wrapper jsp_wrapper = new JspWrapper();
			//�ٽ���һ��Context����
			Context context=new StandardContext();
			context.addChild(wrapper1);
			context.addChild(wrapper2);
			context.addChild(jsp_wrapper);
			// �ٸ��������Value
			((Pipeline)context).addValue(new LogClientValue());
			((Pipeline)context).addValue(new LogHeaderValue());
			//�ٴ���ӳ����
			Mapper mapper=new StandardMapper();
			mapper.setProtocol("HTTP/1.1");
			context.addMapper(mapper);
			//�����������
			WebappLoader loader=new WebappLoader();
			
//			URLClassLoader loader2=new URLClassLoader(urls);
			
			//�ٸ�Context�����������
			context.setLoader(loader);
			//������·��
			context.addServletMapping("/Myservlet", "MyServlet");
			context.addServletMapping("/Welcome", "WelcomeServlet");
			//������������־������
			Logger logger=new FileLogger();
			context.setLogger(logger);
			//����������Session������
			StandardManager manager=new StandardManager();
			context.setManager(manager);
			//�ٸ���������������
			connector.setContainer(context);
			
			connector.initialize();
			connector.start();
			
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			String line="";
			try {
				while((line=reader.readLine())!=null) {
					if(line.equals("stop")) {
						connector.stop();
						break;
					}
				}
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
