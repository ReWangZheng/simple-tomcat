package com.wz.session;

import com.wz.container.Container;

public interface Manager {
	public Container getContainer();
	public void setContainer(Container container);
	public void setDefaultContext();
	public boolean getDistributeable();
	public void setDistributeable(boolean d);
	public String getInfo();
	public int getMaxInactiveInterval(int interval);
	public void add(Session session);
	public void addPropertyChangerListener();
	public Session createSession();
	public Session findSession(String id);
	public Session[] findSessions();
	public void load();
	public void remove(Session session);
	public void removePropertyChangerListener();
	public void unload();
	
}
