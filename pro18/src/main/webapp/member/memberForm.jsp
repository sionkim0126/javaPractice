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
<c:set  var="contextPath" value="${pageContext.request.contextPath }" />
<title>회원가입 창</title>
</head>
<body>
	<form id="memberForm" action="${contextPath }/Member/addMember.do" method="post">
		<h1 style="text-align: center;">회원 가입 창</h1>
		<table align="center">
			<tr>
				<td width="200px">
					<p align="right">아이디</p>
				</td>
				<td width="400px">
					<input  id="id" type="text" name="id" maxlength="10">
				</td>
			</tr>
			<tr>
				<td width="200px">
					<p align="right">비밀번호</p>
				</td>
				<td width="400px">
					<input id="pwd" type="password" name="pwd" maxlength="10" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td width="200px">
					<p align="right">이 름</p>
				</td>
				<td width="400px">
					<input id="name" type="text" name="name"  maxlength="50">
				</td>
			</tr>
			<tr>
				<td width="200px">
					<p align="right">이메일</p>
				</td>
				<td width="400px">
					<input id="email" type="email" name="email"  maxlength="50">
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
	
<script  src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	document.getElementById("memberForm").addEventListener("submit", function(e){

    const id = document.getElementById("id").value.trim();
    const pwd = document.getElementById("pwd").value.trim();
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();

    if (id.length === 0 || id.length > 10) {
        alert("아이디는 1~10자 이내로 입력하세요.");
        document.getElementById("id").focus();
        e.preventDefault();
        return;
    }

    if (pwd.length === 0 || pwd.length > 10) {
        alert("비밀번호는 1~10자 이내로 입력하세요.");
        document.getElementById("pwd").focus();
        e.preventDefault();
        return;
    }

    if (name.length === 0 || name.length > 50) {
        alert("이름은 1~50자 이내로 입력하세요.");
        document.getElementById("name").focus();
        e.preventDefault();
        return;
    }

    if (email.length === 0 || email.length > 50) {
        alert("이메일은 1~50자 이내로 입력하세요.");
        document.getElementById("email").focus();
        e.preventDefault();
        return;
    }
});
</script>
</body>
</html>