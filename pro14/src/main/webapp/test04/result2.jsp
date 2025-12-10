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
<title>출력창 2</title>
</head>
<body>
	<c:if test="${empty param.userID }">
		아이디를 입력하시오 <br>
		<a href="login.jsp">로그인 창 이동</a>
	</c:if>
	<c:if test="${not empty param.userID }">
		<c:if test="${param.userID == 'admin' }">
			<h1>관리자로 로그인 했습니다.</h1>
			<form action="#">
				<input type="button" value="회원정보 삭제하기"><br>
				<input type="button" value="회원정보 수정하기">
			</form>
		</c:if>
			<c:if test="${param.userID != 'admin' }">
				<h2>환영합니다 ${param.userID }님</h2>
			</c:if>
	</c:if>
</body>
</html>