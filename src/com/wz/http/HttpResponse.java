package com.wz.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import com.wz.Server.HttpServer;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;
import com.wz.session.Session;
import com.wz.util.Constant;
import com.wz.util.Cookie;

public class HttpResponse implements ServletResponse{
	private OutputStream out;
	private ServletRequest request;
	private HashMap<String, String> headers=new HashMap<>();
	private ArrayList<Cookie> cookies=new ArrayList<>();
	private static Stack<HttpResponse> pool=new Stack<>();
	private static int maxResponse=20;
	public static HttpResponse fetchResponse(OutputStream out) {
		if(pool.size()>0) {
			HttpResponse res=pool.pop();
			res.out=out;
			return res;
		}
		return new HttpResponse(out);
	}
	public void recycle() {
		if(pool.size()<maxResponse) {
			out=null;
			request=null;
			headers.clear();
			cookies.clear();
			this.p=null;
			pool.push(this);
		}
	}
	private HttpResponse(OutputStream out) {
		this.out=out;
		this.setHeader("Content-Type", "text/html");
	}
	@Override
	public void setRequest(ServletRequest request) {
		this.request=request;
	}
	private boolean issend=false;
	public String getMessage() {
		if(!issend) {
			String Part1=Constant.OK_200;
			for(String key:headers.keySet()) {
				Part1+=key+": "+headers.get(key)+Constant.CRLF;
			}
			String cookieStr=null;
			for(Cookie c:cookies) {
				if(cookieStr==null) {
					cookieStr=c.getKey()+"="+c.getValue();	
				}else {
					cookieStr="&"+c.getKey()+"="+c.getValue();
				}
			}
			System.out.println("cooooo:"+cookieStr);
			if(cookieStr!=null) {
				Part1+="Set-Cookie: "+cookieStr+Constant.CRLF;
			}
			Part1+=Constant.CRLF;
			issend=true;
			return Part1;
		}
		
		return "";
		
	}
	public void SendStaticResouse() {
		try {
			String uri=request.getUri(); // 得到请求的uri
			if(uri.equals("/")) {
				uri=uri+"index.html";
			}
			File file=new File(Constant.WebRoot, uri); //得到目标文件
			String header="";
			String ReponseLine="";
			if(file.exists()) {
				ReponseLine=Constant.OK_200;
				header+=ReponseLine;
			}else {
				ReponseLine=Constant.FILE_NOT_FOUND_REPSONSElINE_404;
				header+=ReponseLine;
				file=new File(Constant.PAGE_404);
			}
			header+="Content-Type: "+"text/html"+Constant.CRLF;
			header+="Content-Length: "+file.length()+Constant.CRLF;
			header+=Constant.CRLF;
			out.write(header.getBytes());
			FileInputStream fo=new FileInputStream(file);
			byte[] buf=new byte[1024];
			int ch=-1;
			while((ch=fo.read(buf))!=-1) {
				out.write(buf,0,ch);
			}
			out.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private HttpPrinter p;
	@Override
	public HttpPrinter getPrintWriter() throws IOException {
		Session session=request.getSession(false);
		if(session!=null) {
			addCookie(new Cookie("jsessionid", session.getId()));
		}
		if(p==null) {
			p=new HttpPrinter(this.out, this);
		}
		return p;
	}
	@Override
	public void setHeader(String key, String v) {
		// TODO 自动生成的方法存根
		this.headers.put(key, v);
	}
	@Override
	public void addCookie(Cookie cookie) {
		this.cookies.add(cookie);
	}
	@Override
	public HashMap<String, String> getHeaders() {
		// TODO 自动生成的方法存根
		return headers;
	}
	@Override
	public ArrayList<Cookie> getCookies() {
		// TODO 自动生成的方法存根
		return cookies;
	}
	@Override
	public String getHeader(String s) {
		// TODO 自动生成的方法存根
		return this.headers.get(s);
	}
	public Cookie getCookie(String s) {
		// TODO 自动生成的方法存根
		for(Cookie cookie:cookies) {
			if(cookie.value.equals(s)) {
				return cookie;
			}
		}
		return null;
	}
}
