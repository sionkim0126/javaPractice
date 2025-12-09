<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%-- core태크 라이브러리를 사용하기 위해 반드시 선언해야합니다. --%> 
<%
	request.setCharacterEncoding("UTF-8");
%>
	<c:set var="id" value="test1" scope="page" />
	<c:set var="pwd" value="1212" scope="page" />
	<c:set var="name" value="${'홍길동' }" scope="page" />
	<c:set var="age" value="${22 }" scope="page" />
	<c:set var="height" value="${172 }" scope="page" />
	
	<style>
		.h2{
			text-align: center;	
		}
	</style>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 출력 창</title>
</head>
<body>
	<h2 class="h2">JSTL / Jsp Standard Tag Library 자바 표준 커스텀 태그</h2>
	<h2 class="h2">JSTL중 Core 태그 라이브러리 사용하기</h2>
	<table align="center" border="1">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="7%"><b>이름</b></td>
			<td width="7%"><b>나이</b></td>
			<td width="7%"><b>키</b></td>
		</tr>
		<tr align="center">
			<td>${id }</td>
			<td>${pwd }</td>
			<td>${name }</td>
			<td>${age }</td>
			<td>${height }</td>
		</tr>
	</table>
</body>
</html>