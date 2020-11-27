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
	private int maxaInactiveInterval=-1; /*������ʱ��*/
	private boolean isNew=false;
	private boolean isValid=false;
	private long thisAcessedTime=creationTime;
	public StandardSession(String id) {
		this.id=id;
	}
	@Override
	public String getAuthType() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void setAuthType(String authType) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public long getCreationTime() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public void setCreationTime() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public String getId() {
		// TODO �Զ����ɵķ������
		return id;
	}

	@Override
	public void setId(String id) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public String getInfo() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public long getLastAccessedTime() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public Manager getManager() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void setManager(Manager manager) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public int getMaxInactiveInterval() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public void setNew(boolean isNew) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void getPrincipal() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void setPrincipal() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public Session getSession() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void setValid(boolean isvalid) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void access() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void addSessionListener() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void expire() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public Object getNote(String name) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void recycle() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void removeNote(String name) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void removeSessionListener() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void setNote(String name, Object value) {
		// TODO �Զ����ɵķ������
		
	}

}
