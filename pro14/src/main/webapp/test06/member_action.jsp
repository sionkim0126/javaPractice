<%@page import="java.util.ArrayList"%>
<%@page import="sec01.ex01.MemberBean"%>
<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean id="m" class="sec01.ex01.MemberBean" />
<jsp:setProperty property="*" name="m"/>
<%
	MemberDAO memberDAO = new MemberDAO();
	memberDAO.addMember(m);
	List<MemberBean> membersList = memberDAO.listMembers();
	request.setAttribute("membersList", membersList);
%>
<title>action_forward</title>
</head>
<body>
	<jsp:forward page="membersList.jsp" />
</body>
</html>