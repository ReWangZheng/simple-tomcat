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
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=aISO-8859-1a>");
		out.print("<title>Insert title here</title>");
		out.print("</head>");
		out.print("<body>");

		this.count+=1;
		for(int i=0;i<100;i++){
			out.print("<h1>我真的好帅</h1><br>");

		}
	
			out.print("</body>");
		out.print("</html>");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	};
}
