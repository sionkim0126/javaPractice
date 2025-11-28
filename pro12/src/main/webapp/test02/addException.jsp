<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
</head>
<body>
	====== toString()내용 ====== <br>
	<h1><%=exception.toString() %></h1>
	========= getMessage()내용 =========== <br>
	<h2><%= exception.getMessage() %></h2>
	============printStackTrace()내용 *콘솔표시*==============<br>
	<h2><% exception.printStackTrace(); %></h2>
	<h3>숫자만 입력 가능합니다. 다시 시도해주세요</h3>
	<a href="add.html">입력 창 이동</a>
</body>
</html>