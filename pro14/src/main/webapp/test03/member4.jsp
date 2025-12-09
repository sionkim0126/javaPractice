<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="id" value="test1" scope="page" />
<c:set var="pwd" value="1111" scope="page" />
<c:set var="name" value="${'tester1'}" scope="page" />
<c:set var="age" value="${22}" scope="page" />
<c:set var="height" value="${177}" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 출력 창</title>
</head>
<body>
	<c:if test="${true }">
		<h2>\${true }항상 참 입니다.</h2>
	</c:if>
	
	<c:if test="${11 == 11 }">
		<h2>\${11 == 11 }  두 값은 같습니다. </h2>
	</c:if>
	
	<c:if test="${11 != 21 }">
		<h2>\${11 != 21 } 두 값은 같지 않습니다. </h2>
	</c:if>
	
	<c:if test="${(id == 'test1') && (name == 'tester1') }">
		<h2>\${(id == 'test1') && (name == 'tester1') } </h2>
		<h2>아이디는 ${id }이고 이름은 ${name }입니다.</h2>
	</c:if>
	
	<c:if test="${age == 22 }">
		<h2>\${age == 22 } / ${name }의 나이는 ${age }살 입니다. </h2>
	</c:if>
	
	<c:if test="${height > 160 }">
		<h2>\${height > 160 } / ${name }의 키는 160보다 큽니다. </h2>
	</c:if>
</body>
</html>