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
<title>입력 창</title>
</head>
<body>
	<form action="member9.jsp" method="post">
		<h1 style="text-align: center;">회원가입 창</h1>
		<table align="center" >
			<tr>
				<td width="200px"><p align="center">아이디</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td width="200px"><p align="center">비밀번호</td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td width="200px"><p align="center">이름</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td width="200px"><p align="center">이메일</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr align="center">
				<td><input type="reset" value="다시 입력"></td>
				<td><input type="submit" value="회원가입"></td>
			</tr>
		</table>
	</form>
</body>
</html>