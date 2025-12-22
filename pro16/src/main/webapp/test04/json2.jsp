<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="button" id="checkJson" value="출력">
	<div id="output"></div>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#checkJson").click(function(){
			var jsonStr = '{"age" : [22, 33, 44]}'; /* json형태의 문자열을 정의(json 선언이 아님)*/
			var jsonInfo = JSON.parse(jsonStr);/* parse()메서드를 이용하여 문자열 형태의 JSON을 JavaScript객체로 변환! */
			var output = "회원나이<br>";
			output += "===========<br>";
			for(var i in jsonInfo.age){
				output +=  jsonInfo.age[i] + "<br>";
			}
				/* 실무에서 많이 사용되는 방법 
					템플릿 문자열은 백택(`)을 사용한다. ㄹㅈㄷ*/
			$("#output").html(output);
		});
	});
</script>
</body>
</html>