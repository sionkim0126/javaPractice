<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %> <!-- ← 표현언어 기능을 활성화합니다. -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어로 여러 가지 산술 연산자 사용</title>
</head>
<body>
	<h1>표현 언어로 여러 가지 산술 연산자 사용</h1>
	<h1>
		\${10 + 10} : ${10 + 10} <br> 
		\${20 - 10} : ${20 - 10} <br> 
		\${10 * 10} : ${10 * 10} <br> 
		\${100/9} : ${100/9} <br> 
		<%-- ${100 div 9} : ${100 div 9} <br> <!-- div를 사용해 나누기 연산을 합니다. --> --%> 		
		\${100 % 9} : ${100 % 9} <br>
		\${100 mod 9} : ${100 mod 9} <br> <!-- mod를 사용해 나머지를 구합니다. -->
	</h1>
</body>
</html>