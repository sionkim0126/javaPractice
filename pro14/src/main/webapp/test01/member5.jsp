<%@page import="sec01.ex01.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="m1" class="sec01.ex01.MemberBean" />
<jsp:setProperty name="m1" property="*" />
<jsp:useBean id="membersList" class="java.util.ArrayList" />
<jsp:useBean id="membersMap" class="java.util.HashMap" />
<%
	membersMap.put("id", "teaGang");
	membersMap.put("pwd", "1212");
	membersMap.put("name", "김태강");
	membersMap.put("email", "teaGang@test.com");
	
	MemberBean m2 = new MemberBean("teaSung", "1212", "김태성", "ace01@test.com");
	
	membersList.add(m1);
	membersList.add(m2);
	
	membersMap.put("membersList", membersList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원출력 창</title>
</head>
<body>
	<table align="center" border="1">
		<tr align="center" bgcolor="#99ccff">
			<td width="20%"><b>아이디</b></td>
			<td width="20%"><b>비밀번호</b></td>
			<td width="20%"><b>이름</b></td>
			<td width="20%"><b>이메일</b></td>
			<td width="20%"><b>주소</b></td>
		</tr>
		<tr align="center">
			<td>${membersMap.id }</td>
			<td>${membersMap.pwd }</td>
			<td>${membersMap.name }</td>
			<td>${membersMap.email }</td>
			<td>${requestScope.address}</td>
		</tr>
		<tr align="center">
			<td>${membersMap.membersList[0].id }</td>
			<td>${membersMap.membersList[0].pwd }</td>
			<td>${membersMap.membersList[0].name }</td>
			<td>${membersMap.membersList[0].email }</td>
			<td>${requestScope.address}</td>
		</tr>
		<tr align="center">
			<td>${membersMap.membersList[1].id }</td>
			<td>${membersMap.membersList[1].pwd }</td>
			<td>${membersMap.membersList[1].name }</td>
			<td>${membersMap.membersList[1].email }</td>
			<td>${requestScope.address}</td>
		</tr>
	</table>
</body>
</html>