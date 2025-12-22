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
			var jsonStr = `[
				  {"name":"박지성","age":26,"gender":"man","nicName":"맨유전설"},
				  {"name":"손흥민","age":25,"gender":"man","nicName":"톹넘전설"}
				]`;
			var jsonInfo = JSON.parse(jsonStr);
			var output = "회원정보<br>";
			output += "=================================<br>";
			for(var i in jsonInfo){
				output += "이름 :" + jsonInfo[i].name + "<br>";
				output += "나이 :" + jsonInfo[i].age + "<br>";
				output += "성별 :" + jsonInfo[i].gender + "<br>";
				output += "닉네임 :" + jsonInfo[i].nicName + "<br><br><br>";
			}
			$("#output").html(output);
		});
	});
	
</script>
</body>
</html>