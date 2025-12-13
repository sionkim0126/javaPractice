<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="title1" value="hello world!" />
	<c:set var="title2" value="쇼핑물 중심 JSP입니다!" />
	<c:set var="str1" value="중심" />
	<h2>여러 가지 문자열 함수 기능</h2>
	title1 = "hello world"<br>
	title2 = "쇼필물 중심 jsp 입니다"<br>
	str1 = "중심"
	<br><br>
	fn:length(title1) : ${fn:length(title1) }<br> <!-- 문자열 길이를 반환합니다 -->
	fn:toUpperCase(title1) : ${fn:toUpperCase(title1) }<br><!-- 문자열을 대문자로 변환 -->
	fn:toLowerCase(title1) : ${fn:toLowerCase(title1) }<br><br><!-- 문자열을 소문자로 변환 -->
	
	fn:substring(title1, 3, 6) : ${fn:substring(title1, 3, 6 )}<br><!-- 문자열에서 4~5번째 문자열을 반환 -->
	fn:trim(title1) : ${fn:trim(title1) }<br><!-- 문자열의 앞,뒤 공백을 없애준다. 중간에 있는 공백은 제외 -->
	fn:replace(title1, " ", "/") : ${fn:replace(title1, " ","/") }<br><br><!-- 문자열에서 공백을 /로 대체합니다. -->
	
	fn:indexOf(title2, str1) : ${fn:indexOf(title2, str1) }<br> <!-- title2 문자열에서 str1의 위치를 구합니다. -->
	fn:contains(title1, str1) : ${fn:contains(title1, str1) }<br><!-- title1 문자열에 str1 문자열이 있는지 판별합니다. -->
	fn:contains(title2, str1) : ${fn:contains(title2, str1) }<!-- title2에 str1 문자열이 있는지 반별합니다. -->
</body>
</html>