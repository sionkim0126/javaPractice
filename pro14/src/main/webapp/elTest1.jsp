<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %> <!-- ← 표현언어 기능을 활성화합니다. -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어로 여러 가지 데이터 출력하기</title>
</head>
<body>
	<h1>표현 언어로 여러 가지 데이터 출력하기</h1>
	<h1>
		\${100} : ${100} <br>
		\${"안녕하세요"} : ${"안녕하세요"} <br>
		\${ 10 + 1 } : ${ 10 + 1 } <br>
		\${"10" + 1} : ${ "10" + 1 } <!-- 숫자형 문자열과 실제 숫자를 더하면 문자열을 자동으로 숫자로 변환하여 더합니다. --> 
		<br>
		<%-- \${ null + 10 } : { null + 10 } <br> --%>
		<%-- \${ "안녕" + 11 } : ${ "안녕" + 11 } <br> --%> <!-- 문자열과 숫자는 더할 수 없습니다. -->
		<%-- \${ "hello" + "world" } : ${ "hello" + "world" } <br> --%> <!-- 문자열끼리는 더할 수 없습니다. --> 
	</h1>
</body>
</html>