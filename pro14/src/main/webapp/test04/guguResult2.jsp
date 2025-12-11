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
<title>c:if를 이용한 구구단 출력</title>
</head>
<body>
	<c:set var="dan" value="${param.dan }" />
	<h1 style="text-align: center;">c:if를 이용한 구구단 출력</h1>
	<table border="1" wdith="800px" align="center">
		<tr align="center" bgcolor="lightblue">
			<td colspan="2">${dan }단 출력</td>
		</tr>
		<c:forEach var="i" begin="1" end="9" step="1">
			<c:if test="${i % 2 == 0 }">
				<tr align="center" bgcolor="#CCFF66">
			</c:if>
			<c:if test="${i % 2 == 1 }">
				<tr align="center" bgcolor="#CCCCFF">
			</c:if>
				<td width="400px">
				<c:out value="${dan }" /> * <c:out value="${i }" />
				</td>
				<td width="400px">
					<c:out value="${dan * i }" />
				</td>
				</tr>
		</c:forEach>
	</table>
</body>
</html>