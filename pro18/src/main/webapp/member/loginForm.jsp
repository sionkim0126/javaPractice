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
<meta charset="UTF-8">
<c:set  var="contextPath" value="${pageContext.request.contextPath }" />
<title>로그인 창</title>
</head>
<body>
	<h1 style="text-align:center">게시판 로그인 창</h1>
	  <form method="post" action="${contextPath }/Member/login.do">
		<table border="1" align="center">
			<tr  bgcolor="lightgreen">
				<td colspan="2" align="center">
					로그인
				</td>
			</tr>
			<tr>
				<td align="right">
					아이디 :&nbsp;
				</td>
				<td>
					<input type="text" name="id" >
				</td>
			</tr>
			<tr>
				<td>
					비밀번호 :&nbsp;
				</td>
				<td>
					<input type="password" name="pwd" >
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="submit" value="로그인"> 
					<input type="button" value="회원가입" onclick="fn_addMem()">
				</td>
			</tr>
		</table>
	  	 <c:if test="${not empty loginError}">
        	<p align="center" style="color:red;">${loginError}</p>
    	</c:if>
	  </form>

<script  src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function fn_addMem(){
		location.href = "${contextPath}/Member/memberForm.do";
	}
</script>
</body>
</html>