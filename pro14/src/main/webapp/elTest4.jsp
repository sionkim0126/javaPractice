<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %> <!-- ← 표현언어 기능을 활성화합니다. -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어로 여러 가지 논리 연산자 사용</title>
</head>
<body>
	<h1>표현 언어로 여러 가지 논리 연산자 사용</h1>
	<h1>
		\${(10==10) && (20==20)} : ${(10==10) && (20==20)} <br>
		\${(10==10) and (20!=20) } : ${(10==10) and (20!=20)} <br>
	</h1>
</body>
</html>