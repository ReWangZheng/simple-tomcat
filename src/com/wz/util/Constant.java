package com.wz.util;

import java.io.File;

public class Constant {
	public static String WebRoot="."+File.separator+"webroot";
	public static String CRLF="\r\n";
	public static String FILE_NOT_FOUND_REPSONSElINE_404="HTTP/1.1 404 File Not Found\r\n";
	public static String OK_200="HTTP/1.1 200 ok\r\n";
	public static String PAGE_404=WebRoot+File.separator+"NoFile"+File.separator+"404.html";
}
