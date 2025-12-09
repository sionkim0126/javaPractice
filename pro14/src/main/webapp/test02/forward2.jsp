<%@page import="sec01.ex01.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	MemberBean member = new MemberBean("lee", "1212", "이순신갓", "lee@jo.kr");
	request.setAttribute("member", member); /* request에 memberBean객체를 바인딩 */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward2</title>
</head>
<body>
	<jsp:forward page="member2.jsp" />
</body>
</html>