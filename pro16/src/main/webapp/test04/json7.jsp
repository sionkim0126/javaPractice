<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<title>JSON 서버 통신</title>
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
				url : "${contextPath}/json3",
				success : function(data, textStatus){
					alert("실행중 다음을 눌러주세용~");
					var jsonInfo = JSON.parse(data);
					var memberInfo = "회원정보<br>";
					memberInfo += "=====================<br>";
					for(var i in jsonInfo.members){
						memberInfo += "이 름 : " + jsonInfo.members[i].name + "<br>";
						memberInfo += "나 이 : " + jsonInfo.members[i].age + "<br>";
						memberInfo += "성 별 : " + jsonInfo.members[i].gender + "<br>";
						memberInfo += "닉네임 : " + jsonInfo.members[i].nicName + "<br><br><br>";
					}
					var bookInfo = "<br><br>도서정보<br>";
					for(var i in jsonInfo.books){
						bookInfo += "제 목 : " + jsonInfo.books[i].title + "<br>";
						bookInfo += "저 자 : " + jsonInfo.books[i].writer + "<br>";
						bookInfo += "가 격 : " + jsonInfo.books[i].price + "<br>";
						bookInfo += "장 르 : " + jsonInfo.books[i].genre + "<br>";
						var imageURL = jsonInfo.books[i].image;
						bookInfo += "<img src='" + imageURL + "' style='width:150px; height:auto;' /><br><br><br>";  
					}
					$("#output").html(memberInfo + bookInfo);
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