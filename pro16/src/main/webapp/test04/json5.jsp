<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
			var _jsonInfo = '{"name" : "김시온", "age" : 26, "gender" : "man", "nicName" : "zion"}';
			$.ajax({
				type : "post",
				url : "${contextPath}/json",
				data : {jsonInfo : _jsonInfo},
				success : function(data, textStatus){
					alert("성공?");
				},
				error : function(data, textStatus){
					alert("에러발생");
				},
				complete : function(data, textStatus){

				}
			}); //end Ajax
		});
	});
</script>
</body>
</html>