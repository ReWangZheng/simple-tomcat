package com.wz.Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.wz.http.HttpPrinter;
import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;
import com.wz.util.Cookie;

public class Response implements ServletResponse{
	private Request request;
	private HashMap<String, String> map;
	
	public Response(Request request) {
		this.request=request;
		map=new HashMap<String,String>();
		map.put("404Header","HTTP/1.1 404 File Not Found\r\n");
		map.put("200Header","HTTP/1.1 200 ok\r\n");
		map.put("Content-Type", "text/html\r\n");
	}
	public void SendStaticResouse() {
		try {
			String uri=request.getUri(); // �õ������uri
			System.out.println(request.getContent()+"����һ��");
			if(uri.endsWith("/")) {
				uri=uri+"index.html";
			}
			File file=new File(HttpServer.webroot, uri); //�õ�Ŀ���ļ�
			String header=null;
			if(file.exists()) {
				if(file.getName().endsWith("jpg")) {
					map.put("Content-Type", "image/jpg\r\n");
				}
				header=map.get("200Header")
								+"Content-Type: "+map.get("Content-Type")
								+"Content-Length: "+file.length()+"\r\n"
								+"\r\n";
			}else {
				file=new File(HttpServer.NoFile);
				header=map.get("404Header")
						+"Content-Type: "+map.get("Content-Type")
						+"Content-Length: "+file.length()+"\r\n"
						+"\r\n";
			}
			System.out.println(header);
			FileInputStream in=new FileInputStream(file); //�õ��ļ��� 
			byte[] b=header.getBytes(); //�Ȱ��ļ���������˵
			OutputStream out=request.getOutPutStream(); //�õ���������������
			out.write(b); //д��ȥ
			//����Ϳ�ʼд�ļ�
			byte[] buf=new byte[1024];
			int ch=in.read(buf);
			while(ch!=-1) {
				out.write(buf,0,ch);
				ch=in.read(buf);
			}
			out.flush();
			request.close();
			in.close();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	@Override
	public void setRequest(ServletRequest request) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public HttpPrinter getPrintWriter() {
		// TODO �Զ����ɵķ������
		return null;
	}
	@Override
	public void setHeader(String key, String v) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void addCookie(Cookie cookie) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public String getHeader(String s) {
		// TODO �Զ����ɵķ������
		return null;
	}
	@Override
	public Cookie getCookie(String s) {
		// TODO �Զ����ɵķ������
		return null;
	}
	@Override
	public HashMap<String, String> getHeaders() {
		// TODO �Զ����ɵķ������
		return null;
	}
	@Override
	public ArrayList<Cookie> getCookies() {
		// TODO �Զ����ɵķ������
		return null;
	}
}
