<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include01.jsp</title>
</head>
<body>
	<h2>안녕하세요 쇼핑물 중심JSP시작입니다.</h2>
	<jsp:include page="image_output.jsp" flush="true">
		<jsp:param value="엘로Hi" name="name"/>
		<jsp:param value="yellowHi.png" name="imgName"/>
	</jsp:include><br>
	<h2>안녕하세요 쇼핑물 중심JSP끝 부분 입니다.</h2>
</body>
</html>