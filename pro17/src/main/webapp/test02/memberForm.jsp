<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<title>회원 가입창</title>
</head>
<body>
	<form action="${contextPath }/member/addMember.do" method="post">
		<h1 style="text-align: center;">회원 가입 창</h1>
		<table align="center">
			<tr>
				<td width="200px">
					<p align="right">아이디</p>
				</td>
				<td width="400px">
					<input type="text" name="id">
				</td>
			</tr>
			<tr>
				<td width="200px">
					<p align="right">비 번</p>
				</td>
				<td width="400px">
					<input type="password" name="pwd">
				</td>
			</tr>
			<tr>
				<td width="200px">
					<p align="right">이 름</p>
				</td>
				<td width="400px">
					<input type="text" name="name">
				</td>
			</tr>
			<tr>
				<td width="200px">
					<p align="right">이메일</p>
				</td>
				<td width="400px">
					<input type="text" name="email">
				</td>
			</tr>
			<tr>
				<td width="200px">
					&nbsp;
				</td>
				<td width="400px">
					<input type="reset" value="다시입력">
					<input type="submit" value="가입하기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>