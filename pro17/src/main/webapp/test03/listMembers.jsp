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
<style type="text/css">
	.cls1{
		font-size: 40px;
		text-align: center;
	}
	.cls2{
		font-size: 20px;
		text-align: center;
	}
</style>
<c:choose>
	<c:when test='${msg == "addMember" }'>
		<script type="text/javascript">
			window.onload = function(){
				alert("회원을 등록했습니다.");
			}
		</script>
	</c:when>
	<c:when test='${msg == "modified" }'>
		<script type="text/javascript">
			window.onload = function(){
				alert("회원 정보를 수정했습니다.");
			}
		</script>
	</c:when>
	<c:when test='${msg == "deleted" }'>
		<script type="text/javascript">
			window.onload = function(){
				alert("회원 정보를 삭제했습니다.");
			}
		</script>
	</c:when>
</c:choose>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
	<p class="cls1">회원정보</p>
	<table border="1" align="center">
		<tr align="center" bgcolor="lightgreen">
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비 번</b></td>
			<td width="7%"><b>이 름</b></td>
			<td width="7%"><b>이메일</b></td>
			<td width="7%"><b>가입일</b></td>
			<td width="7%"><b>수정</b></td>
			<td width="7%"><b>삭제</b></td>
		</tr>
		<c:choose>
			<c:when test="${empty membersList }">
				<tr>
					<td colspan="7"><b>등록된 회원이 없습니다.</b></td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="mem" items="${membersList }">
					<tr align="center">
						<td>${mem.id }</td>
						<td>${mem.pwd }</td>
						<td>${mem.name }</td>
						<td>${mem.email }</td>
						<td>${mem.joinDate }</td>
						<td><a href="${contextPath }/member3/modMemberForm.do?id=${mem.id}">수정</a></td>
						<td><a href="${contextPath }/member3/delMember.do?id=${mem.id}">삭제</a></td>
					</tr>		
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	<a href="${contextPath }/test03/memberForm.jsp">
		<p class="cls2">회원가입하기</p>
	</a>
</body>
</html>