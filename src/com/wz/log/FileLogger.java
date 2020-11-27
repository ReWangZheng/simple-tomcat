package com.wz.log;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import com.wz.Life.Lifecycle;
import com.wz.Life.LifecycleListener;

public class FileLogger extends LoggerBase implements Lifecycle{
	private String date;
	private String prefix="ReganLog_";
	private String suffix=".log";
	private PrintWriter pw;
	private String dir="./";
	public static void main(String[] args) {
		FileLogger fl=new FileLogger();
		fl.log("���ִ���");
	}
	public FileLogger() {
		
	}
	@Override
	public void log(String message) {
		Timestamp ts=new Timestamp(System.currentTimeMillis());
		String tString=ts.toString().substring(0,19);
		String date=tString.substring(0,10);
		if(this.date==null || !this.date.equals(date)) {
			close();
			this.date=date;
			open();
		}
		this.pw.println(tString+":"+message);
		this.pw.flush();
	}
	public void open() {
		try {
			String filename=this.prefix+this.date+this.suffix;
			File file=new File(filename);
			if(!file.exists()) {
				file.createNewFile();
			}
			
			this.pw=new PrintWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void close() {
		if(pw!=null) {
			pw.flush();
			pw.close();
			pw=null;
		}
	}
	@Override
	public void addLifecycleListener(LifecycleListener l) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public LifecycleListener[] findLifecycleListeners() {
		// TODO �Զ����ɵķ������
		return null;
	}
	@Override
	public void removeLifecycleListener(LifecycleListener l) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void start() {
		System.out.println("��־���������������Ѿ���ʼ");
	}
	@Override
	public void stop() {
		close();
		System.out.println("��־���������������Ѿ�����");
	}

}
