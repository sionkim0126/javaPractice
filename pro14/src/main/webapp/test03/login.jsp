<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 창</title>
</head>
<body>
	<form action="#" method="post">
		아이디 : <input type="text" name="id"><br>
		비 번 : <input type="password" name="pwd"><br>
		<input type="reset" value="다시 입력"><input type="submit" value="로그인">
	</form>
	<br>
	<br>
	<button>
	<a href="${pageContext.request.contextPath }/test01/memberForm.jsp">회원가입</a>
	</button> 
	<br><br>
	<button>
	<a href="${contextPath }/test01/memberForm.jsp">회원가입</a>
	</button>← 긴 내장 객체의 속성을 사용할 필요 없이 간단한 변수 이름으로 컨텍스트 이름을 설정합니다.
</body>
</html>