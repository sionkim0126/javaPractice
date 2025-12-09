<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	request.setAttribute("id", "test1");
	request.setAttribute("pwd", "1212");
	session.setAttribute("name", "test1");
	application.setAttribute("email", "test1@admin.com");
	/* request.setAttribute("address", "서울시 강남구"); */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward4</title>
</head>
<body>
	<jsp:forward page="member4.jsp" />
</body>
</html>