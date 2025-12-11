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
<title>점수 출력</title>
</head>
<body>
	<c:set var="score" value="${param.score }" />
	<h1>시험 점수
		<c:out value="${score }"/>
	</h1>
	<c:choose>
		<c:when test="${score >= 90 && score <= 100 }">
			<h2>"A학점 입니다."</h2>
		</c:when>
		<c:when test="${score >= 80 && score < 90 }">
			<h2>"B학점 입니다."</h2>
		</c:when>
		<c:when test="${score >= 70 && score < 80 }">
			<h2>"C학점 입니다."</h2>
		</c:when>
		<c:when test="${score >= 60 && score < 70 }">
			<h2>"D학점 입니다."</h2>
		</c:when>
		<c:otherwise>
			<h2>"F학점 입니다. 노력하세요"</h2>
		</c:otherwise>
	</c:choose>
</body>
</html>