<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%!
		String msg = "아이디를 입력하지 않았습니다. 아이디를 입력해 주세요";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과창2</title>
</head>
<body>
	<%
		String userID = request.getParameter("userID");
		if(userID.length() == 0){
	%>
	<jsp:forward page="login.jsp">
		<jsp:param value="<%=msg %>" name="msg"/>
	</jsp:forward>
	<%
		}
	%>
	<h1><%=userID %>님! 완영합니다.</h1>
	<a href="login.jsp">로그인 창 이동</a>
	
</body>
</html>