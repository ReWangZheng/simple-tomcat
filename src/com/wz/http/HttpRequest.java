package com.wz.http;

import java.io.InputStream;
import java.net.InetAddress;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

import com.wz.catalina.Context;
import com.wz.myservlet.ServletRequest;
import com.wz.session.Manager;
import com.wz.session.Session;
import com.wz.util.Cookie;
import com.wz.util.ParameterMap;

public class HttpRequest implements ServletRequest{
	public InputStream in;
	protected HashMap<String, String> header=new HashMap<>();
	protected ArrayList<Cookie> cookies=new ArrayList<>();
	protected ParameterMap param=new ParameterMap();
	protected String QuerryString;
	protected String uri;
	public String JSESSIONID=null;
	protected static Stack<HttpRequest> pool=new Stack<>();
	protected static int maxRequest=20;
	protected InetAddress ip;
	private Context context;
	public void recycle() {
		if(pool.size()<maxRequest) {
			in=null;
			param.clear();;
			uri=null;
			JSESSIONID=null;
			cookies.clear();;
			header.clear();;
			pool.push(this);
		}
	}
	public InetAddress getInetAddress() {
		return ip;
	}
	public void setInetAddress(InetAddress ip) {
		this.ip=ip;
	}
	public static HttpRequest fetchRequest(InputStream in) {
		if(pool.size()>0) {
			HttpRequest res=pool.pop();
			res.in=in;
			return res;
		}
		return new HttpRequest(in);
	}
	private HttpRequest(InputStream inputStream) {
		this.in=inputStream;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	protected String method;
	protected String parameterMes;
	protected String protocol="";
	
	public void setparameterMes(String p) {
		this.parameterMes=p;
	}
	public void setQuerryString(String que) {
		this.QuerryString=que;
	}
	
	public void setRequestedUri(String uri) {
		this.uri=uri;
	}
	
	public void setRequestedSessionId(String id) {
		this.JSESSIONID=id;
	}
	
	@Override
	public String getUri() {
		return uri;
	}
	@Override
	public String getHeader(String key) {
		return header.get(key);
	}
	@Override
	public Set<String> getheaderNames() {
		return header.keySet();
	}
	@Override
	public HashMap<String, String> getHeaders() {
		// TODO 自动生成的方法存根
		return this.header;
	}
	@Override
	public String getParameter(String key) {
		return this.param.get(key);
	}
	@Override
	public ParameterMap getParameterMap() {
		return this.param;
	}
	@Override
	public Set<String> getParameterNames() {
		return param.keySet();
	}
	@Override
	public Collection<String> getParameterValues() {
		return param.values();
	}

	public void setRequestedMethod(String method) {
		this.method=method;
	}
	public String getRequestedMethod() {
		return method;
	}
	
	public void addHeader(String key,String v) {
		this.header.put(key, v);
	}
	
	public void addCookie(Cookie c) {
		cookies.add(c);
	}
	@Override
	public Cookie getCookie(String key) {
		for(Cookie cookie :cookies) {
			if(cookie.getKey().equals(key)) {
				return cookie;
			}
		}
		return null;
	}
	@Override
	public ArrayList<Cookie> getCookies() {
		// TODO 自动生成的方法存根
		return cookies;
	}
	@Override
	public Session getSession() {
		return getSession(true);
	}
	@Override
	public Session getSession(boolean isNew) {
		Manager manager=context.getManager();
		if(JSESSIONID==null && isNew==false) {
			return null;
		}else if(JSESSIONID==null && isNew==true) {
			Session session=manager.createSession();
			JSESSIONID=session.getId();
			return session;
		}else{
			Session session=manager.findSession(JSESSIONID);

			if(session==null && isNew==true) {
				return manager.createSession();
			}
			return session;
		}
	}
	@Override
	public void setContext(Context context) {
		this.context=context;
	}
}
