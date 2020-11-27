package com.wz.session;

import java.util.HashMap;

public abstract class ManagerBase implements Manager {
	protected HashMap<String, Session> map=new HashMap<>();
	public void add(Session session) {
		synchronized (map) {
			map.put(session.getId(), session);	
			System.out.println("Ìí¼Ó³É¹¦£º"+session.getId());
		}
	}
	
	public void remove(Session session) {
		synchronized (map) {
			map.remove(session.getId());
		}
	}
	
	@Override
	public Session[] findSessions() {
		if(map.size()==0) {
			return null;
		}else {
			return map.values().toArray(new Session[] {});
		}
	}
	
	
	@Override
	public Session findSession(String id) {
		System.out.println(map.containsKey(id));
		if(id==null) {
			return null;
		}
		synchronized (map) {
			Session session=map.get(id);
			return session;
		}
	}

}
