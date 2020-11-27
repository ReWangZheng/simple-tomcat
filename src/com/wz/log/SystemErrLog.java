package com.wz.log;

public class SystemErrLog extends LoggerBase{
	@Override
	public void log(String message) {
		System.err.println(message);
	}
}
