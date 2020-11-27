package com.wz.log;

public class SystemOutLogger extends LoggerBase{
	@Override
	public void log(String message) {
		System.out.println(message);
	}

}
