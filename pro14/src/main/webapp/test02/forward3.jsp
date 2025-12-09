<%@page import="sec01.ex01.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	/* List membersList = new ArrayList(); 선언 자체는 가능하지만 제네릭을 사용하는 것이 바람직하다. */
	List<MemberBean> membersList = new ArrayList<MemberBean>();
	MemberBean m1 = new MemberBean("test1", "1212", "test1", "test1@test.com");
	MemberBean m2 = new MemberBean("test2", "1212", "test2", "test2@test.com");
	membersList.add(m1);
	membersList.add(m2);
	request.setAttribute("membersList", membersList); /* request 내장 객체에 ArrayList 바인딩 */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward3</title>
</head>
<body>
	<jsp:forward page="member3.jsp"/>
</body>
</html>