<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %> <!-- ← 표현언어 기능을 활성화합니다. -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어로 여러 가지 비교 연산자 사용</title>
</head>
<body>
	<h1>표현 언어로 여러 가지 비교 연산자 사용</h1>
	<h1>
		\${10 == 10} : ${10 == 10} <br> 
		\${10 eq 10} : ${10 eq 10} <br><br>
		
		\${"hello" == "hello"} : ${"hello" == "hello"} <br> 
		\${"hello" eq "hello"} : ${"hello" eq "hello"} <br><br> <!-- 문자열 비교는 == 또는 eq를 연산자로 사용합니다 -->
		
		\${20 != 10} : ${20 != 10} <br> 
		<%-- \${20 ne 10} : ${20 ne 10} <br><br> --%>
		
		\${"hello" != "apple"} : ${"hello" != "apple"} <br>
		<%-- \${"hello" ne "apple"} : ${"hello" ne "apple"} <br><br> --%>
		
		\${10 < 10} : ${10 < 10} <br>
		\${10 lt 10} : ${10 lt 10} <br><br>
		
		\${100 > 10} : ${100 > 10}<br>
		\${100 gt 10} : ${100 gt 10}<br><br>
		
		\${100 <= 10} : ${100 <= 10} <br>
		\${100 le 10} : ${100 le 10} <br><br>
		
		\${100 >= 10} : ${100 >= 10}<br>
		\${100 ge 10} : ${100 ge 10}
	</h1>
</body>
</html>