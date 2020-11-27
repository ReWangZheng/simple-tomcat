package com.wz.myservlet;

import java.util.HashMap;
import java.util.HashSet;

public class ServletManager {
	private static HashSet<String> set=new HashSet<>();
	public static void registered(Class c) {
		set.add(c.getName());
	}
	public static boolean exit(String name) {
		return set.contains(name);
	}
}
