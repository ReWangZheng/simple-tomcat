package com.wz.log;

import com.wz.container.Container;

public abstract class LoggerBase implements Logger{
	protected int verbosity=ERROR;
	protected Container container;
	@Override
	public Container getConainer() {
		return container;
	}

	@Override
	public void setConainer(Container container) {
		this.container=container;
	}

	@Override
	public String getInfo() {
		return null;
	}

	@Override
	public int getVerbosity() {
		return verbosity;
	}

	@Override
	public void setVerbosity(String ver) {
		if("FATAL".equalsIgnoreCase(ver)) {
			this.verbosity=FATAL;
		}else if("ERROR".equalsIgnoreCase(ver)) {
			this.verbosity=ERROR;
		}else if("WARANG".equalsIgnoreCase(ver)) {
			this.verbosity=WARANG;
		}else if("INFORMATION".equalsIgnoreCase(ver)) {
			this.verbosity=INFORMATION;
		}else if("DEBUG".equalsIgnoreCase(ver)) {
			this.verbosity=DEBUG;
		}
	}

	@Override
	public void addPropertyChangeListener() {
		// TODO 自动生成的方法存根
		
	}

	public abstract void log(String message);
	@Override
	public void log(String message, int verbosity) {
		if(this.verbosity<verbosity) {
			log(message);
		}
	}
	@Override
	public void log(String message, Throwable able, int verbosity) {
		if(this.verbosity<verbosity) {
			log(message);
		}
	}
}
