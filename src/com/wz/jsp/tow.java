package com.wz.jsp;

import java.io.IOException;

import com.wz.http.HttpPrinter;

public class tow extends JspServletAbs{
	
	
		int count = 0;
	
	public void service(com.wz.myservlet.ServletRequest request, com.wz.myservlet.ServletResponse response) {
		HttpPrinter out;
		try {
			out = response.getPrintWriter();
					out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Insert title here</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("啥子情况");

		this.count+=1;
		String a = request.getParameter("passwd");
		if(count>5){
			out.print("<h1>密码错误!</h1>");

		}else{
			out.print("<h1>登陆成功!</h1>");

		}
			out.print("</body>");
		out.print("</html>");

			out.flush();

						System.out.println(count);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	};
}
