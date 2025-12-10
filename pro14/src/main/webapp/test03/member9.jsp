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
<title>정보 출력 창</title>
</head>
<body>
	<table border="1" align="center">
		<tr align="center" bgcolor="lightblue">
			<td width="10%">아이디</td>
			<td width="10%">비밀번호</td>
			<td width="10%">이름</td>
			<td width="10%">이메일</td>
		</tr>
		<c:choose>
			<c:when test="${empty param.id }">
				<tr align="center">
					<td colspan="4">아이디를 입력하세요!</td>
				</tr>
			</c:when>
		<c:otherwise>
			<tr align="center">
				<td><c:out value="${param.id }" /></td>
				<td><c:out value="${param.pwd }" /></td>
				<td><c:out value="${param.name }" /></td>
				<td><c:out value="${param.email }" /></td>
			</tr>
		</c:otherwise>
		</c:choose>
	</table>
</body>
</html>