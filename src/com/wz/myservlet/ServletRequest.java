package com.wz.myservlet;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import com.wz.catalina.Context;
import com.wz.session.Session;
import com.wz.util.Cookie;
import com.wz.util.ParameterMap;

public interface ServletRequest {
	public String getUri(); // Get uri
	public String getHeader(String header); //get the header of request
	public Set<String> getheaderNames(); // Get all the names of the header
	public HashMap<String, String> getHeaders();
	public String getParameter(String key);
	public ParameterMap getParameterMap();
	public Set<String> getParameterNames();
	public Collection<String> getParameterValues();
	public String getRequestedMethod();
	public Cookie getCookie(String key);
	public ArrayList<Cookie> getCookies();
	public Session getSession();
	public Session getSession(boolean isNew);
	public void setContext(Context context);
}
