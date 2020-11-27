package com.wz.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.Stack;

import javax.management.openmbean.OpenDataException;

import org.omg.CORBA.INITIALIZE;

import com.wz.Life.Lifecycle;
import com.wz.Server.Request;
import com.wz.container.Container;
import com.wz.container.SimpleContainer;
import com.wz.myservlet.Servlet;
import com.wz.processor.HttpProcessor;
import com.wz.processor.Processor;

public class HttpConnector implements Runnable,Connector{

	private boolean stop=false;
	
	private int port=80;
	private String ip="127.0.0.1";
	
	private ServerSocket ss;
	private boolean isclose=false;
	private HttpProcessor processor=null;
	//HttpProcessor对现池 
	private Stack<HttpProcessor> processorPool=new Stack<>();
	private int minprocessor=10;
	private int maxprocessor=20;
	private int currentProcessor=0;
	private 
	//容器
	Container container;
	public ServerSocket open() {
		try {
			return new ServerSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void run() {
		try {
			
			while(!stop) {
				Socket cs;
				cs = ss.accept();
				processor=createProcessor();
				if(processor==null) {
					cs.close();
				}
				processor.assign(cs); //这个必须直接返回才能保证，一直接收http请求。所以assign()方法是在自己的线程里进行的
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//开启服务器
	public void start() {
		while(currentProcessor<maxprocessor) {
			if(currentProcessor>maxprocessor && maxprocessor>0)
				break;
			recycle(new HttpProcessor(this));
		}
		new Thread(this).start();
	}
	
	public void initialize() {
		try {
			ss=new ServerSocket(port, 100, InetAddress.getByName(ip));
			if(container instanceof Lifecycle) {
				Lifecycle lc=(Lifecycle)container;
				lc.start();
			}
		}catch (BindException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			getContainer().getLogger().log(e.getMessage());
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Container getContainer() {
		return this.container;
	}

	@Override
	public void setContainer(Container container) {
		this.container=container;
	}

	@Override
	public void createRequest() {
		
	}

	@Override
	public void createResponse() {
		
	}
	
	public int getMinprocessor() {
		return minprocessor;
	}

	public void setMinprocessor(int minprocessor) {
		this.minprocessor = minprocessor;
	}

	public int getMaxprocessor() {
		return maxprocessor;
	}

	public void setMaxprocessor(int maxprocessor) {
		this.maxprocessor = maxprocessor;
	}
	
	public void recycle(HttpProcessor processor) {
		currentProcessor++;
		this.processorPool.push(processor);
	}
	
	public HttpProcessor createProcessor() {
		if(!this.processorPool.isEmpty())
		{
			currentProcessor--;
			return this.processorPool.pop();
		}
		return null;
	}
	
	public void stop() {
		if(stop==true) {
			return;
		}
		stop=true;
		if(processor!=null) {
			processor.stop();
		}
		if(container instanceof Lifecycle) {
			((Lifecycle) container).stop();
		}
		
		try {
			ss.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			container.getLogger().log(e.toString());
		}
	}

}
