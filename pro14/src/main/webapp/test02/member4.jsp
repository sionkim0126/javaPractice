<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	session.setAttribute("address", "인천광역시 미추홀구");
	/* 표현언어에서는 동일한 속성 이름에 접근할 경우 우선순위는 
		page > request > session> application 순으로 적용된다. */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 출력 창4</title>
</head>
<body>
	<h2 align="center">내장객체 스코프 우선순위</h2>
	<h2 align="center">request vs session</h2>
	<table align="center" border="1">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%">이름</td>
			<td width="7%">이메일</td>
			<td width="5%">주소</td>
		</tr>
		<tr align="center">
			<td>${id }</td>
			<td>${pwd }</td>
			<td>${name }</td>
			<td>${email }</td>
			<td>${address }</td>
		</tr>
	</table>
</body>
</html>