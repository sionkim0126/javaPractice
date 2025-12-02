<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include02.jsp</title>
</head>
<body>
	<h2>안녕하세요 쇼핑물 중심 JSP 시작입니다.</h2>
	<jsp:include page="image_output.jsp" flush="true">
		<jsp:param value="고릴라" name="name"/>
		<jsp:param value="Gorilla.png" name="imgName"/>
	</jsp:include><br>
	<h2>안녕하세요 쇼필물 중심 JSP 끝 부분입니다.</h2>
</body>
</html>