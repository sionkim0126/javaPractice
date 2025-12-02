<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 창</title>
</head>
<body>
	<%
		String userID = request.getParameter("userID");
		if(userID.length() == 0){
			/* 
			RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
			dispatch.forward(request, response);
			 */
	%>
	<jsp:forward page="login.jsp"></jsp:forward>
	<%
		}
	%>
	<h1><%=userID %>님! 완영합니다.</h1>
	<a href="login.jsp">로그인 창 이동</a>
</body>
</html>