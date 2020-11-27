package com.wz.session;

import java.util.Random;

import com.wz.Life.Lifecycle;
import com.wz.Life.LifecycleListener;
import com.wz.container.Container;

public class StandardManager extends ManagerBase implements Lifecycle,Runnable
{
	private int checktime=60;
	protected Container container;
	@Override
	public Container getContainer() {
		return container;
	}

	@Override
	public void setContainer(Container container) {
		this.container=container;
	}

	@Override
	public void setDefaultContext() {
		
	}

	@Override
	public boolean getDistributeable() {
		return false;
	}

	@Override
	public void setDistributeable(boolean d) {
		
	}

	@Override
	public String getInfo() {
		return null;
	}

	@Override
	public int getMaxInactiveInterval(int interval) {
		return 0;
	}

	@Override
	public void addPropertyChangerListener() {
		
	}

	@Override
	public Session createSession() {
		Random random=new Random(System.currentTimeMillis());
		String id=""+random.nextInt(10000);
		Session session=new StandardSession(id);
		add(session);
		return session;
	}

	@Override
	public void load() {
		
	}

	@Override
	public void removePropertyChangerListener() {
		
	}

	@Override
	public void unload() {
		
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
		load();
	}

	@Override
	public void stop() {
		unload();
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		
	}
	private void threadSleep() {
		try {
			Thread.currentThread().sleep(1000*checktime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
