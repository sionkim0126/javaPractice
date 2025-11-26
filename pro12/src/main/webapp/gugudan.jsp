<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("UTF-8");
    	int dan = Integer.parseInt(request.getParameter("dan"));
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 출력</title>
</head>
<body>
	<h1 align="center"><%=dan %>단 출력 ↓</h1>
	<table border='1' width='800' align="center">
		<tr align="center" bgcolor="#FFFF66">
			<td colspan="2"><%=dan %>단 출력 </td>
		</tr>
		<%
			for(int i = 1; i < 10; i++){
				if(i % 2 == 0){
		%>
		<tr align="center" bgcolor="#CCFF66">
			<td width="400">
				<%=dan %> * <%=i %>
			</td>
			<td width="400"><%=i*dan %></td>
		</tr>
		<%
			}else{
		%>
		<tr align="center" bgcolor="#CCCCFF">
			<td width="400">
				<%=dan %> * <%=i %>
			</td>
			<td width="400"><%=i*dan %></td>
		</tr>
		<%
				}
			}
		%>
		<tr align="center" >
			<td colspan="2">	
				<a href="/pro12/gugudan.html">단수 입력 이동</a>
			</td>
		</tr>
	</table>
</body>
</html>