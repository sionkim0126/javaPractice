<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<String> dataList = new ArrayList<String>();
	dataList.add("hello");
	dataList.add("world");
	dataList.add("안녕하세요");
%>
<c:set var="list" value="<%=dataList %>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>반복문 실습</title>
</head>
<body>
	<c:forEach var="i" begin="1" end="10" step="1" varStatus="loop" > <!-- 반복 변수 i를 1부터 10까지 1씩 증가시키면서 반복문 수행 -->
		i = ${i } &nbsp;&nbsp;&nbsp; 반복횟수: ${loop.count } <br>
	</c:forEach>
	<br>
	<c:forEach var="i" begin="1" end="10" step="2" > <!-- 반복 변수 i를 1부터 10까지 2씩 증가 시키면서 반복문 수행 -->
		5 * ${i } = ${5*i } <br>
	</c:forEach>
	<br>
	<c:forEach var="data" items="${list }"> <!-- ArrayList 같은 걸렉션 객체에 저장된 객체(데이터)를 반복해서 반복 변수 data에 하나씩 가져와 처리합니다. -->
		${data } <br>
	</c:forEach>
	<br>
	<c:set var="fruits" value="사과, 파인애플, 바나나, 망고, 귤" /> <!-- 구분자 ,(콤마)를 이용해 문자열을 분리해서 출력합니다. -->
	<c:forTokens var="token" items="${fruits }" delims="," >
		${token } <br>
	</c:forTokens>
</body>
</html>