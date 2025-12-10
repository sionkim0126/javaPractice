<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 입력 창</title>
</head>
<body>
	<form action="result2.jsp" method="post">
		아이디 : <input type="text" name="userID"><br>
		비 번 : <input type="password" name="userPWD"><br>
		<input type="reset" value="다시입력"><input type="submit" value="로그인">
	</form>
</body>
</html>