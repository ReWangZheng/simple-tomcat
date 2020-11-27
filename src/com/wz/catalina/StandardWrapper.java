package com.wz.catalina;

import java.awt.image.RescaleOp;
import java.io.File;
import java.net.PasswordAuthentication;
import java.net.URLClassLoader;
import java.security.Principal;
import java.util.Set;

import com.wz.Life.Lifecycle;
import com.wz.Life.LifecycleListener;
import com.wz.config.ServeletConfig;
import com.wz.config.StandardServletConfigFade;
import com.wz.container.Container;
import com.wz.loader.Loader;
import com.wz.log.Logger;
import com.wz.myservlet.MyServlet;
import com.wz.myservlet.Servlet;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;
import com.wz.session.Manager;
import com.wz.util.ParameterMap;
import com.wz.values.SimpeWrapperValue;
import com.wz.xml.Node;
import com.wz.xml.Selector;
import com.wz.xml.XMLParser;

public class StandardWrapper implements Wrapper,Pipeline,Lifecycle,ServeletConfig{
	private Pipeline pipeline=new StandardPipeline();
	private String className;
	private Loader loader;
	private String name;
	private Container parent;
	private Servlet servlet;
	private Logger Logger=null;
	private ParameterMap pm=new ParameterMap();
	public StandardWrapper(String name) {
		this.setName(name);
		parseConfig();
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
	public void load() {
		
	}
	@Override
	public Servlet allocate() {
		try {
			if(servlet==null) {
				servlet=(Servlet) getLoader().getClassLoader().loadClass(className).newInstance();
				servlet.init(new StandardServletConfigFade(this));
			}
			return servlet;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Loader getLoader() {
		if(this.loader!=null) {
			return this.loader;
		}else {
			return this.getParentClassLoader();
		}
	}
	public void setLoader(Loader loader) {
		this.loader=loader;
	}
	
	public String getInfo() {
		return "测试用例Wrapper!";
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public Container getParent() {
		return parent;
	}
	public void setParent(Container c) {
		this.parent=c;
	}
	public Loader getParentClassLoader() {
		return parent.getLoader();
	}
	public void setParentClassLoader(Loader loader) {
		parent.setLoader(loader);
	}

	public void setManager() {}
	public void getCluster() {}
	public void setCluster() {}
	public void setRealm() {}
	public void getResources() {}
	public void setReources() {}
	public void addChild(Container c) {} //无用
	public void addContainerListener() {}
	public void addMapper(Mapper m) {} //无用
	public void addPropertyChangeListener() {}
	public Container findChild(String name) {return null;} //无用
	public void findContainerListeners() {}
	public Mapper findMapper(String protocol) {return null;} //无用
	public void findMappers() {}

	
	// 以下为管道的方法
	@Override
	public Value getBasic() {
		return pipeline.getBasic();
	}

	@Override
	public void setBasic(Value v) {
		pipeline.setBasic(v);
	}
	@Override
	public Value[] getValues() {
		return pipeline.getValues();
	}
	@Override
	public void removeValue(Value value) {
		pipeline.removeValue(value);
	}
	@Override
	public void addValue(Value v) {
		this.pipeline.addValue(v);
	}
	
	
	@Override
	public void setServletClass(String name) {
		this.className=name;
	}

	@Override
	public void addLifecycleListener(LifecycleListener l) {
	}

	@Override
	public LifecycleListener[] findLifecycleListeners() {
		return null;
	}

	@Override
	public void removeLifecycleListener(LifecycleListener l) {
	}

	@Override
	public void start() {
		System.out.println(getName()+"Wrapper"+" 已经准备完毕!");
	}

	@Override
	public void stop() {
		if(servlet!=null) {
			servlet.destroy();
		}
		System.out.println(getName()+"Wrapper"+" 已经销毁!");

	}

	@Override
	public void setLogger(Logger loger) {
		loger.setConainer(this);
		this.Logger=loger;
	}

	@Override
	public Logger getLogger() {
		if(parent!=null) {
			return parent.getLogger();
		}else {
			return Logger;
		}
	}

	@Override
	public void setManager(Manager manager) {}
	
	private XMLParser parser=new XMLParser(new File("./config/servletconfig.xml"));
	
	private void parseConfig() {
		Selector selector=parser.getSelector();
		Node p_node = selector.select("/servlet/servlet-name[content="+name+"]")[0]
					.getParent();
		this.className = p_node.getSelector().select("/servlet-class")[0].getContent();
		Node[] params = p_node.getSelector().select("/init-param");
		for(Node node:params) {
			String pname=node.getSelector().select("/param-name")[0].getContent();
			String pvalue=node.getSelector().select("param-value")[0].getContent();
			pm.put(pname, pvalue);
		}
		
	}

	@Override
	public Context getServletContext() {
		if(parent != null && parent instanceof Context) {
			return (Context)parent;
		}
		return null;
	}

	@Override
	public String getServletName() {
		return this.getName();
	}

	@Override
	public String getInitParamter(String name) {
		return pm.get(name);
	}

	@Override
	public Set<String> getInitParamterNames() {
		return pm.keySet();
	}
}
