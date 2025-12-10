<%@page import="sec01.ex01.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	List<MemberBean> membersList =new ArrayList<MemberBean>();
	MemberBean m1 = new MemberBean("test1", "1111", "tester1", "test1@test.com" );
	MemberBean m2 = new MemberBean("test2", "2222", "tester2", "test2@test.com" );
	MemberBean m3 = new MemberBean("test3", "3333", "tester3", "test3@test.com" );
	membersList.add(m1);
	membersList.add(m2);
	membersList.add(m3);
%>
<c:set var="list" value="<%=membersList %>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 출력 창</title>
</head>
<body>
	<table border="1" align="center">
		<tr align="center" bgcolor="lightgreen">
			<td width="10%">이름</td>
			<td width="10%">아이디</td>
			<td width="10%">비밀번호</td>
			<td width="13%">이메일</td>
		</tr>
		<c:forEach var="member" items="${list }"> <!-- 반복문을 수행하면서 list에 저장된 MemberBean 객체가 차례대로 member에 할당됩니다. -->
			<tr align="center">
				<td>${member.name }</td>
				<td>${member.id }</td>
				<td>${member.pwd }</td>
				<td>${member.email }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>