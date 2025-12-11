<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험 점수 입력 창</title>
</head>
<body>
	<form action="scoreResult2.jsp" method="post">
		시험점수 : <input type="number" name="score" /><br>
		<input type="submit" value="학점 반환">
	</form>
</body>
</html>