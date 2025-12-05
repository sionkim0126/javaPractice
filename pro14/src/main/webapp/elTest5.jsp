<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    isELIgnored="false" %> <!-- ← 표현언어 기능을 활성화합니다. -->
<jsp:useBean id="m1" class="sec01.ex01.MemberBean" scope="page" />
<jsp:setProperty name="m1" property="name" value="이순신" />
<jsp:useBean id="m2" class="java.util.ArrayList" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어로 여러 가지 연산자 사용</title>
</head>
<body>
	<h1>표현 언어로 여러 가지 연산자 사용</h1>
	<h2>empty 연산자 </h2>
	<h2>빈 문자열은 ture 값이 있으면 false </h2>
	<h1>
		\${empty m1} : ${empty m1}<br>
		\${not empty m1} : ${not empty m1}<br><br>
		
		\${empty m2} : ${empty m2}<br>
		\${not empty m2} : ${not empty m2}<br><br>
		
		\${empty "hello"} : ${empty "hello"}<br>
		\${empty null} : ${empty null}<br>
		\${empty ""} : ${empty ""}<br>
	</h1>
</body>
</html>