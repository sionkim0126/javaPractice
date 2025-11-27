<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("name", "SON");
	request.setAttribute("address", "LA - 121.12");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 바인딩 테스트</title>
</head>
<body>
	<%
		RequestDispatcher dispatch = request.getRequestDispatcher("request02.jsp");
		dispatch.forward(request, response);
	%>
</body>
</html>