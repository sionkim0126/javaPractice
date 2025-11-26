<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	String name = "순신";
	public String getName(){
		return name;
	}
%>
<% String age = request.getParameter("age"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스트립트릿 연습</title>
</head>
<body>
	<h1>안녕하세요 <%=name %>님!!</h1><br>
	<h2><%=getName() %>님의 나이는 <%=age %>살 입니다.</h2>
</body>
</html>