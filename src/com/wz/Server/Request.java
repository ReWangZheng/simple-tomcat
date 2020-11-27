package com.wz.Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import javax.sound.sampled.Line;

import com.wz.catalina.Context;
import com.wz.myservlet.ServletRequest;
import com.wz.session.Session;
import com.wz.util.Cookie;
import com.wz.util.ParameterMap;

public class Request implements ServletRequest{
	private InputStream in;
	private OutputStream out;
	private String contentMes="";
	private String method="";
	private String route="";
	private HashMap<String, String> map;
	public Socket cs;
	public Request(Socket cs) {
		try {
			this.cs=cs;
			in=cs.getInputStream();
			out=cs.getOutputStream();
			map=new HashMap<>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String parseUri(String requestString) {
		return null;
	}
	public void parse() {
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(in));
			String line="";
			while(true) {
				line=reader.readLine();
				if(line==null || line.equals(""))
					break;
				contentMes+=line+"\n";
			}
			this.parseContentMes(contentMes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void parseContentMes(String mes) {
		if(mes==null || mes.equals("")) {
			return;
		}
		String[] headerMes=mes.split("\n");
		//解析请求方式
		String h1=headerMes[0];
		if(h1==null || h1.equals("")) {
			return;
		}
		int h1_index=h1.indexOf(" ");
		int h2_index=h1.lastIndexOf(" ");
		if (h1_index==-1) {
			System.out.println(h1);
		}
		this.method=mes.substring(0,h1_index);
		this.route=mes.substring(h1_index+1,h2_index);
		for(String line:headerMes) {
			String[] line_mes=line.split(": ");
			if(line_mes.length!=2) {
				continue;
			}
			map.put(line_mes[0], line_mes[1]);
		}
	}
	public OutputStream getOutPutStream() {
		return out;
	}
	public void close() {
		try {
			cs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getUri() {
		return route;
	}
	public String getContent() {
		return contentMes;
	}
	@Override
	public String getHeader(String header) {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public Set<String> getheaderNames() {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public HashMap<String, String> getHeaders() {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public String getParameter(String key) {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public ParameterMap getParameterMap() {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public Set<String> getParameterNames() {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public Collection<String> getParameterValues() {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public String getRequestedMethod() {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public Cookie getCookie(String key) {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public ArrayList<Cookie> getCookies() {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public Session getSession() {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public Session getSession(boolean isNew) {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public void setContext(Context context) {
		// TODO 自动生成的方法存根
		
	}
	
}
