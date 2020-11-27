package com.wz.catalina;

import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;

import com.wz.Life.Lifecycle;
import com.wz.Life.LifecycleEvent;
import com.wz.Life.LifecycleListener;
import com.wz.Life.LifecycleSupport;
import com.wz.container.Container;
import com.wz.loader.Loader;
import com.wz.log.Logger;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;
import com.wz.session.Manager;
import com.wz.values.SimpleContextValue;

public class StandardContext implements Context,Pipeline,Lifecycle,LifecycleListener{
	private Pipeline pipeline=new StandardPipeline();
	private ArrayList<Container> childlist=new ArrayList<>(); //������
	private Container parent; //������
	private String name; // ������������
	private Mapper mapper; //ӳ����
	private Loader loader; //�������
	private HashMap<String, String> rootmapping=new HashMap<>();
	private HashMap<String, Mapper> mappers=new HashMap<>();
	private boolean started=false; // �Ƿ��Ѿ�����
	protected LifecycleSupport lifecycle=new LifecycleSupport(this);// ���������¼�֧����
	public Logger logger;
	public Manager manager; // session ������
	
	public Wrapper jspWraper;
	public StandardContext() {
		SimpleContextValue v=new SimpleContextValue();
		v.setContainer(this);
		pipeline.setBasic(v);
		addLifecycleListener(this);
	}
	@Override
	public void invoke(ServletRequest req, ServletResponse res) {
		pipeline.invoke(req, res);
	}
	public String getInfo() {return null;}
	public void setLoader(Loader loader) {
		loader.setContainer(this);
		this.loader=loader;
		
	}
	public void setManager(Manager manager) {
		this.manager=manager;
	}
	public void getCluster() {}
	public void setCluster() {}
	public Loader getLoader() {
		if(this.loader!=null) {
			return this.loader;
		}
		return parent.getLoader();
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public Container getParent() {
		return this.parent;
	}
	public void setParent(Container c) {
		this.parent=c;
	}
	public Loader getParentClassLoader() {
		return null;
	}
	public void setParentClassLoader(Loader loader) {
		this.parent.setLoader(loader);
	}
	public void addChild(Container c) {
		c.setParent(this);
		this.childlist.add(c);
	}
	public void addMapper(Mapper m) {
		this.mapper=m;
		m.setContainer(this);
		String protocol=m.getProtocol();
		if(protocol!=null) {
			mappers.put(protocol, m);
			if(mappers.size()==1) {
				this.mapper=m;
			}
		}
	}
	public Mapper findMapper(String protocol) {
		if(mapper!=null) {
			return mapper;
		}
		return mappers.get(protocol);
	}
	public void setRealm() {}
	public void getResources() {}
	public void setReources() {}
	public void addContainerListener() {}
	public void addPropertyChangeListener() {}
	public void findContainerListeners() {}
	public void findMappers() {}
	public void setJSPWraper(Wrapper jspWraper) {
		this.jspWraper=jspWraper;
		jspWraper.setParent(this);
	};
	public Container findChild(String name) {
		if(name == null) {
			return null;
		}
		if(name.endsWith(".jsp")) {
			System.out.println(name);
			return jspWraper;
		}
		for(Container c:childlist) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}

	
	@Override
	public Value getBasic() {
		return pipeline.getBasic();
	}

	@Override
	public void addValue(Value v) {
		this.pipeline.addValue(v);
	}

	@Override
	public void setBasic(Value v) {
		this.pipeline.setBasic(v);
	}

	@Override
	public Value[] getValues() {
		return this.pipeline.getValues();
	}

	@Override
	public void removeValue(Value value) {
		this.pipeline.removeValue(value);
	}

	@Override
	public void addWrapper(Wrapper m) {
		// TODO �Զ����ɵķ������
	}

	@Override
	public Wrapper CreateWrapper() {
		return null;
	}

	@Override
	public void addServletMapping(String root, String name) {
		if(root==null) {
			return;
		}
		this.rootmapping.put(root, name);
	}
	@Override
	public String findServletMapping(String pattern) {
		if(pattern.endsWith(".jsp")) {
			return "JSP";
		}
		return rootmapping.get(pattern);
	}
	@Override
	public void addLifecycleListener(LifecycleListener l) {
		this.lifecycle.addLifecycleListener(l);
	}
	@Override
	public LifecycleListener[] findLifecycleListeners() {
		return this.lifecycle.findLifecycleListeners();
	}
	@Override
	public void removeLifecycleListener(LifecycleListener l) {
		this.lifecycle.removeLifecycleListener(l);
	}
	private void startmyself() {
		System.out.println("��ʼ����");
	}
	
	private void stopmyself() {
		System.out.println("ֹͣ����");
	}
	
	public void start() {
		if(started) {
			return;
		}
		if(loader!=null && loader instanceof Lifecycle) {
			((Lifecycle)loader).start();
		}
		logger.log("����������!");
		started=true;
		startmyself();
		lifecycle.fireLifecycleEvent(BEFORE_START_EVENT, null);
		for(Container c:childlist) {
			if(c instanceof Lifecycle) {
				Lifecycle lc=(Lifecycle)c;
				lc.start();
			}
		}
		
		if(pipeline instanceof Lifecycle) {
			Lifecycle lc=(Lifecycle)pipeline;
			lc.start();
		}
		
		if(logger instanceof Lifecycle) {
			((Lifecycle)logger).start();
		}
		
		lifecycle.fireLifecycleEvent(AFTER_START_EVENET, null);
		
	}
	@Override
	public void stop() {
		if(!started) {
			return;
		}
		if(loader!=null && loader instanceof Lifecycle) {
			((Lifecycle)loader).start();
		}
		logger.log("�������ر�");
		started=false;
		startmyself();
		lifecycle.fireLifecycleEvent(BEFORE_STOP_EVENT, null);
		for(Container c:childlist) {
			if(c instanceof Lifecycle) {
				Lifecycle lc=(Lifecycle)c;
				lc.stop();
			}
		}
		
		if(pipeline instanceof Lifecycle) {
			Lifecycle lc=(Lifecycle)pipeline;
			lc.stop();
		}
		
		if(logger instanceof Lifecycle) {
			((Lifecycle)logger).stop();
		}
		
		lifecycle.fireLifecycleEvent(AFTER_STOP_EVENT, null);

	}
	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		System.out.println(event.getType()+": StandardContext��beforestar����");
	}
	@Override
	public void setLogger(Logger loger) {
		this.logger=loger;
	}
	@Override
	public Logger getLogger() {
		if(parent!=null) {
			return parent.getLogger();
		}else {
			return logger;
		}
	}
	@Override
	public Manager getManager() {
		// TODO �Զ����ɵķ������
		return this.manager;
	}
	
}
