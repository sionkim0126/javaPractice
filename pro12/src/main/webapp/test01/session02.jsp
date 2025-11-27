<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String name = (String)session.getAttribute("name");
    	String address = (String)session.getAttribute("address");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 테스트 3</title>
</head>
<body>
	<h2>이름은 <%=name %>입니다.</h2>
	<h2>주소는 <%=address %> 입니다.</h2>
</body>
</html>