<%@ page import="sec01.ex01.MemberBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="membersList" class="java.util.ArrayList" />
<jsp:useBean id="membersMap" class="java.util.HashMap" />
<%
	membersMap.put("id", "test1");
	membersMap.put("pwd", "1212");
	membersMap.put("name", "tester");
	membersMap.put("email", "test@test.com");
	
	MemberBean m1 = new MemberBean("test2", "1212", "tester2", "test2@test.com");
	MemberBean m2 = new MemberBean("test3", "3333", "tester3", "test3@test.com");
	
	membersList.add(m1);
	membersList.add(m2);
	
	membersMap.put("membersList", membersList);
%>
<c:set var="membersList" value="${membersMap['membersList'] }" />

<%-- <c:set>태그를 이용해 HashMap에 저장된 ArrayList에 접근하기 위해 사용하기 편리한 이름으로 설정  --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 출력 창</title>
</head>
<body>
	<table align="center" border="1">
		<tr align="center" bgcolor="#99ccff">
			<td width="20%"><b>아이디</b></td>
			<td width="20%"><b>비밀번호</b></td>
			<td width="20%"><b>이름</b></td>
			<td width="20%"><b>이메일</b></td>
		</tr>
		<tr align="center">
			<td>${membersMap.id }</td>
			<td>${membersMap.pwd }</td>
			<td>${membersMap.name }</td>
			<td>${membersMap.email }</td>
		</tr>
		<tr align="center">
			<td>${membersList[0].id }</td> 
			<td>${membersList[0].pwd }</td>
			<td>${membersList[0].name }</td>
			<td>${membersList[0].email }</td>
		</tr>
		<tr align="center">
			<td>${membersMap['membersList'][1].id }</td> <!-- c태그 없이 map에서 arrayList 출력  -->
			<td>${membersMap['membersList'][1].pwd }</td>
			<td>${membersMap['membersList'][1].name }</td>
			<td>${membersMap['membersList'][1].email }</td>
		</tr>
		
	</table>
</body>
</html>