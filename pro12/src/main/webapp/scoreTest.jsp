<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("UTF-8");
    	int score = Integer.parseInt(request.getParameter("score"));
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학점 변환</title>
</head>
<body>
	<h1>시험점수 <%=score %>점</h1>
	<%
		if(score >= 90){
	%>
	<h2>학점 : A</h2>
	<%
	}else if(score >= 80 && score < 90){ 
	%>
	<h2>학점 : B</h2>
	<%
	}else if(score >= 70 && score < 80){
	%>
	<h2>학점 : C</h2>
	<%
	}else if(score >= 60 && score < 70){
	%>
	<h2>학점 : D</h2>
	<%
	}else{
	%>
	<h2>학점 : F</h2>
	<%
	}
	%>
	<br><a href="/pro12/scoreTest.html">학점 입력 창 이동</a>
</body>
</html>