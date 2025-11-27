<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	/* PrintWriter out = response.getWriter(); */
	String name = (String)request.getParameter("name");
	int age = Integer.parseInt(request.getParameter("age"));

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 스코프 테스트2</title>
</head>
<body>
	<%
		if(name != null && name.length() != 0){
	%>
	<h1><%=name %>, <%=age %></h1>
	<%
		}else{
	%>
	<h1>이름을 입력하시오</h1>
	<%
		}
	%>
	<%	
		if(name != null && name.length() != 0){
	%>
	<h1><% out.println(name + ", " + age); %></h1>
	<%
		}else{
	%>
	<h1>이름을 입력하시오</h1>
	<%
		}
	%>
</body>
</html>