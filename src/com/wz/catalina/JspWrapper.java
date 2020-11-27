package com.wz.catalina;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.wz.container.Container;
import com.wz.jsp.JspParser;
import com.wz.loader.Loader;
import com.wz.loader.WebappClassLoader;
import com.wz.loader.WebappLoader;
import com.wz.log.Logger;
import com.wz.myservlet.JspServlet;
import com.wz.myservlet.Servlet;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;
import com.wz.session.Manager;
import com.wz.values.SimpeWrapperValue;

import sun.tools.tree.ThisExpression;

public class JspWrapper implements Wrapper{
	Container parent;
	String name;
	String JspPath;
	JspParser parser =new JspParser();
	HashMap<String, JspServlet> map =new HashMap<>();
	private Pipeline pipeline=new StandardPipeline();

	public JspWrapper() {
		
		setName("JSP");
		
	}
	public void setJspPath(String jspPath) {
		this.JspPath = jspPath;
		if(map.containsKey(jspPath)) {
			return;
		}
		
		File jsp_file=new File("./webroot/"+jspPath);
		
		try {
			String prefix =jsp_file.getName().substring(0,jsp_file.getName().indexOf("."));
			parser.parse(jsp_file, 
					prefix);
			int state = parser.Compiler("./src/com/wz/jsp/"+prefix+".java");
			System.out.println(state);
			if(state!=0) {
				System.out.println("����ʧ��");
			}else {
				System.out.println("����ɹ�!");
			}
			WebappLoader loader =(WebappLoader) getLoader();
			Class cls = loader.createWebappClassLoader().loadClass("com.wz.jsp."+prefix);
			try {
				map.put(jspPath, (JspServlet)cls.newInstance());
			} catch (InstantiationException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	@Override
	public String getInfo() {
		return "����JSP";
	}

	@Override
	public Loader getLoader() {
		// TODO �Զ����ɵķ������
		return this.getParent().getLoader();
	}

	@Override
	public void setLoader(Loader loader) {
		
	}

	@Override
	public void setManager(Manager manager) {
		
	}

	@Override
	public void getCluster() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void setCluster() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public Container getParent() {
		return parent;
	}

	@Override
	public void setParent(Container c) {
		this.parent=c;
	}

	@Override
	public Loader getParentClassLoader() {
		return getParent().getLoader();
	}

	@Override
	public void setParentClassLoader(Loader loader) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void setRealm() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void getResources() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void setReources() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void addChild(Container c) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void addContainerListener() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void addMapper(Mapper mapper) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void addPropertyChangeListener() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public Container findChild(String name) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void findContainerListeners() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public Mapper findMapper(String protocol) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void findMappers() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void invoke(ServletRequest req, ServletResponse res) {
		SimpeWrapperValue v=new SimpeWrapperValue();
		v.setContainer(this);
		pipeline.setBasic(v);
		req.setContext((Context) parent);
		pipeline.invoke(req, res);

		
	}

	@Override
	public void setLogger(Logger loger) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public Logger getLogger() {
		return null;
	}

	@Override
	public void load() {
		
	}

	@Override
	public Servlet allocate() {
		Servlet servlet = map.get(this.JspPath);
		return servlet;
	}

	@Override
	public void setServletClass(String name) {
		
	}

}
