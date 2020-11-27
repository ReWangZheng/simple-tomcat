package com.wz.session;

import java.io.Serializable;
import java.util.ArrayList;

public class StandardSession implements Session,Serializable{
	private static final long serialVersionUID = 1L;
	private transient String authType=null;
	private long creationTime=0L;
	private transient boolean expiring=false;
	private String id=null;
	private long lastaccessedTime=creationTime;
	private transient ArrayList<?> listeners=new ArrayList<>();
	private Manager manager=null;
	private int maxaInactiveInterval=-1; /*最大存在时间*/
	private boolean isNew=false;
	private boolean isValid=false;
	private long thisAcessedTime=creationTime;
	public StandardSession(String id) {
		this.id=id;
	}
	@Override
	public String getAuthType() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void setAuthType(String authType) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public long getCreationTime() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public void setCreationTime() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public String getId() {
		// TODO 自动生成的方法存根
		return id;
	}

	@Override
	public void setId(String id) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public String getInfo() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public long getLastAccessedTime() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public Manager getManager() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void setManager(Manager manager) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public int getMaxInactiveInterval() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public void setNew(boolean isNew) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void getPrincipal() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setPrincipal() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Session getSession() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void setValid(boolean isvalid) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void access() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addSessionListener() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void expire() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Object getNote(String name) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void recycle() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void removeNote(String name) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void removeSessionListener() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setNote(String name, Object value) {
		// TODO 自动生成的方法存根
		
	}

}
