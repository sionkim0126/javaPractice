<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, sec02.ex01.*"
    %>
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		h1{
			text-align: center;
		}
		table {
    		width: 800px;
   			margin: 0 auto; /* 중앙 정렬 */
    		border-collapse: collapse; /* 테두리 깔끔하게 */
		}
	</style>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
	<h1>회원정보 출력</h1>
	<%
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		MemberDTO dto = new MemberDTO();//1
		dto.setName(name);
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> membersList = dao.listMembers(dto);
		
	%>
	<table border="1" >
		<tr align="center" bgcolor="#FFFF66">
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일자</td>
		</tr>
		<%
			for(int i = 0; i < membersList.size(); i++){
				MemberDTO dto2 = (MemberDTO) membersList.get(i);//2
				String id = dto2.getId();
				String pw = dto2.getPw();
				String getName = dto2.getName();
				String email = dto2.getEmail();
				Date joinDate = dto2.getJoinDate();
		%>
		<tr align="center">
			<td><%=id %></td>
			<td><%=pw %></td>
			<td><%=getName %></td>
			<td><%=email %></td>
			<td><%=joinDate %></td>
		</tr>
		<%
			}
		%>
		<tr align="center">
			<td colspan="5"><a href="search.jsp">입력 창 이동</a></td>
		</tr>
	</table>
</body>
</html>