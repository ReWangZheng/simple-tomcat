package com.wz.util;

public class Cookie{
	public String key;
	public String value;
	public Cookie(String key,String value) {
		this.key=key;
		this.value=value;
	}
	
	public Cookie() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
