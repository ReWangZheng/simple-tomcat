package com.wz.jsp;

import java.io.IOException;

import com.wz.http.HttpPrinter;

public class Fir extends JspServletAbs{
	
	
		int count = 0;
	
	public void service(com.wz.myservlet.ServletRequest request, com.wz.myservlet.ServletResponse response) {
		HttpPrinter out;
		try {
			out = response.getPrintWriter();
					out.print("<!DOCTYPE html>");







		this.count+=1;
		for(int i=0;i<100;i++){
			out.print("<h1>����ĺ�˧</h1><br>");

		}
	
			out.print("</body>");


		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	};
}