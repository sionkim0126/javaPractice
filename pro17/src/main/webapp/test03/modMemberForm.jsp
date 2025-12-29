<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<meta charset="UTF-8">
<title>회원 정보 수정</title>
<style type="text/css">
	.cls1{
	font-size: 40px;
	text-align: center;
	}
</style>
</head>
<body>
	<h1 class="cls1">회원 정보 수정창</h1>
	<form action="${contextPath }/member3/modMember.do?id=${memInfo.id}" method="post">
		<table align="center">
			<tr>
				<td width="200px">
					<p align="right">아이디</p>
				</td>
				<td width="400px">
					<input type="text" name="id" value="${memInfo.id }" disabled="disabled">
				</td>
			</tr>
			<tr>
				<td width="200px">
					<p align="right">비 번</p>
				</td>
				<td width="400px">
					<input type="password" name="pwd" value="${memInfo.pwd }">
				</td>
			</tr>
			<tr>
				<td width="200px">
					<p align="right">이 름</p>
				</td>
				<td width="400px">
					<input type="text" name="name" value="${memInfo.name }">
				</td>
			</tr>
			<tr>
				<td width="200px">
					<p align="right">이메일</p>
				</td>
				<td width="400px">
					<input type="text" name="email" value="${memInfo.email }">
				</td>
			</tr>
			<tr>
				<td width="200px">
					<p align="right">가입일</p>
				</td>
				<td width="400px">
					<input width="400px" type="date" name="joinDate" value="${memInfo.joinDate }" disabled="disabled">
				</td>
			</tr>
			<tr align="center">
				<td colspan="2" width="400px">
					<input type="submit" value="수정하기"><input type="reset" value="다시입력"> 
				</td>
			</tr>
		</table>
	</form>
</body>
</html>