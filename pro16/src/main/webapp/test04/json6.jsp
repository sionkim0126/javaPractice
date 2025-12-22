<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<title>Insert title here</title>
</head>
<body>
	<input type="button" id="checkJson" value="출력">
	<div id="output"></div>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#checkJson").click(function(){
			$.ajax({
				type : "post",
				url : "${contextPath}/json2",
				success : function(data, textStatus){
					alert("실행중...");
					var jsonInfo = JSON.parse(data);
					var memberInfo = "회원 정보<br>";
					memberInfo += "==================<br>";
					for(var i in jsonInfo.members){						
					memberInfo += "이 름 : " + jsonInfo.members[i].name + "<br>";
					memberInfo += "나 이 : " + jsonInfo.members[i].age + "<br>";
					memberInfo += "성 별 : " + jsonInfo.members[i].gender + "<br>";
					memberInfo += "닉네임 : " + jsonInfo.members[i].nicName + "<br><br><br>";
					}
					$("#output").html(memberInfo);
				},
				error : function(textStatus){
					alert("에러발생");
				}
			});
		});
	});	
</script>
</body>
</html>