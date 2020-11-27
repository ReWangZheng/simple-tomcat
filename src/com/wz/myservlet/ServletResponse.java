package com.wz.myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.wz.http.HttpPrinter;
import com.wz.util.Cookie;

public interface ServletResponse{
	public void setRequest(ServletRequest request);
	public HttpPrinter getPrintWriter() throws IOException;
	public void setHeader(String key,String v);
	public void addCookie(Cookie cookie);
	public String getHeader(String s);
	public Cookie getCookie(String s);
	public HashMap<String, String> getHeaders();
	public  ArrayList<Cookie> getCookies();
}
