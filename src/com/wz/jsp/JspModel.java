package com.wz.jsp;

import java.io.IOException;

import com.wz.http.HttpPrinter;

public class JspModel extends JspServletAbs{
	
	public void service(com.wz.myservlet.ServletRequest req, com.wz.myservlet.ServletResponse res) {
		HttpPrinter out;
		try {
			out = res.getPrintWriter();
			
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	};
}
