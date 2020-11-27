package com.wz.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import com.wz.Server.Response;

public class HttpPrinter {
	private OutputStream os;
	private String Part1;
	private String Part2="";
	private HttpResponse res;
	private ByteBuffer buffer;
	private boolean autoFlush=false;
	private int balance;
	public  HttpPrinter(OutputStream os,HttpResponse res) throws IOException {
		this.os=os;
		this.res=res;
		this.buffer=ByteBuffer.allocate(1024);
		this.balance = 1024;
		System.out.println("mess£º"+res.getMessage());
		this.print(res.getMessage());
	}
	public void print(String str) throws IOException {
		this.write(str.getBytes());
	}
	public void println(String str) throws IOException {
		this.print(str+"\n");
	}
	public void setAutoflush(boolean b) {
		this.autoFlush=b;
	}
	public void write(byte[] d) throws IOException {
		if(this.balance>d.length) {
			buffer.put(d);
			this.balance -=d.length;
		}else if (d.length>buffer.capacity()) {
			flush();
			buffer=ByteBuffer.wrap(d);
			flush();
		}else {
			flush();
			buffer.put(d);
			this.balance -=d.length;

		}
		if(autoFlush)
			flush();
	}
	public void write(byte[] d,int l,int r) throws IOException {
		byte[] temp=new byte[r-l];
		for(int i=l;i<r;i++) {
			temp[i-l]=d[i];
		}
		write(temp);
	}
	public void flush() throws IOException {
		os.write(buffer.array());
		os.flush();
		buffer.clear();
		this.balance=1024;
	}
	public void writeFile(File file) throws IOException {
		FileInputStream fi=new FileInputStream(file);
		byte[] d=new byte[1024];
		int ch=0;
		while((ch=fi.read(d))!=-1) {
			this.write(d, 0, ch);
		}
	}
}
