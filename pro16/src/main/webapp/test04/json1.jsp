<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>json자료형 실습</title>
</head>
<body>
	<input type="button" value="출력" id="checkJson">
	<div id="output"></div>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(function (){
		$("#checkJson").click(function (){
			var jsonStr = '{"name" : ["홍길동", "이순신", "임꺽정"]}';
			var jsonInfo = JSON.parse(jsonStr);
			var output = "회원이름<br>";
			output += "==========<br>";
			for(var i in jsonInfo.name){
				output += jsonInfo.name[i] + "<br>";
			}
			$("#output").html(output);
		});
	});
</script>
</body>
</html>