<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>escapeXml 출력 실습</title>
</head>
<body>
	<h2>escapeXml 변환하기</h2>
	<h2>
		<pre>
			<c:out value="&lt;" escapeXml="true" /> <!-- secapeXml 속성이 true이므로 value의 &lt;는 그대로 출력 -->
			<c:out value="&lt;" escapeXml="felse" /> <!-- secapeXml 속성이 felse이므로 value의 &lt;는 특수 문자로 변환되어 화면에 출력 -->
			<c:out value="&gt;" escapeXml="true" />
			<c:out value="&gt;" escapeXml="felse" />
			<c:out value="&amp;" escapeXml="true" />
			<c:out value="&amp;" escapeXml="felse" />
			<c:out value="&#039;" escapeXml="true" />
			<c:out value="&#039;" escapeXml="felse" />
			<c:out value="&#034;" escapeXml="true" />
			<c:out value="&#034;" escapeXml="felse" />
		</pre>
	</h2>
</body>
</html>