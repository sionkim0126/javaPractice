<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 출력창</title>
</head>
<body>
	<h1>결과 출력</h1>
	<%
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
	%>
	<h2>아이디 : <%=user_id %></h2>
	<h2>비 번 : <%=user_pw %></h2>
	
</body>
</html>