<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출력창</title>
</head>
<body>
	<c:if test="${empty param.userID }">
		아이디를 입력하세요 <br>
		<a href="login.jsp">로그인 창</a>
	</c:if>
	<c:if test="${not empty param.userID }">
		<h2>환영합니다. <c:out value="${param.userID }" />님! </h2>
	</c:if>
</body>
</html>