<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<meta charset="UTF-8">
<title>글 쓰기창</title>

</head>
<body>
	<h1 style="text-align: center;">새 글 쓰기</h1>
	<form name="articleForm" method="post" action="${contextPath }/board2/addArticle.do" enctype="multipart/form-data">
	<table border="0" align="center">
		<tr>
			<td align="right">글제목 : </td>
			<td colspan="2"><input type="text" size="67" maxlength="500" name="title"></td>
		</tr>
		<tr>
			<td align="right">글내용 : </td>
			<td colspan="2"><textarea name="content" rows="10" cols="65" maxlength="4000" ></textarea></td>
		</tr>
		<tr>
			<td align="right">이미지파일 첨부 : </td>
			<td><input type="file" name="imageFileName" onchange="readURL(this);"/></td>
			<td><img id="preview" src="#" width="200px" height="200px" /></td>
		</tr>
		<tr>
			<td align="right"> </td>
			<td colspan="2">
				<input type="submit" value="글쓰기">
				<input type="button" value="목록보기" onclick="backToList(this.form)" /> 
			</td>
		</tr>
	</table>
	
	</form>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function readURL(input){
		if(input.files && input.files[0]){
			var reader = new FileReader(); // F와 R은 대문자여야 합니다.
			reader.onload = function(e){
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function backToList(obj){
		obj.action = "${contextPath}/board2/listArticles.do";
		obj.submit();
	}
</script>
</body>
</html>