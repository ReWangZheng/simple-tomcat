package com.wz.session;

public interface Session {
	public static final String SESSION_CREATED_EVENT="createSession";
	public static final String SESSION_DESTROYED_EVENT="destorySession";
	public String getAuthType();
	public void setAuthType(String authType);
	public long getCreationTime();
	public void setCreationTime();
	public String getId();
	public void setId(String id);
	public String getInfo();
	public long getLastAccessedTime();
	public Manager getManager();
	public void setManager(Manager manager);
	public int getMaxInactiveInterval();
	public void setNew(boolean isNew);
	public void getPrincipal();
	public void setPrincipal();
	public Session getSession();
	public void setValid(boolean isvalid);
	public void access();
	public void addSessionListener();
	public void expire();
	public Object getNote(String name);
	public void recycle();
	public void removeNote(String name);
	public void removeSessionListener();
	public void setNote(String name,Object value);
}
