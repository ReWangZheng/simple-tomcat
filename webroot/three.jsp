<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
ɶ�����
	<%!
		int count = 0;
	%>
	
	<%
		this.count+=1;
		String a = request.getParameter("passwd");
		if(count>5){
	%>	
		<h1>�������!</h1>
	<%
		}else{
	%>
		<h1>��½�ɹ�!</h1>
	<%
		}
	%>
	
</body>
</html>