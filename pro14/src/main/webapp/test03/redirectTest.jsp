<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>c:redirect 태그 실습</title>
</head>
<body>
	<c:redirect url="/test01/member1.jsp">
		<c:param name="id" value="re_test1" />
		<c:param name="pwd" value="1111" />
		<c:param name="name" value="c:redirect" />
		<c:param name="email" value="re@test.com" />
	</c:redirect>
</body>
</html>