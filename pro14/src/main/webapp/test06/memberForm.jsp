<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력 창</title>
</head>
<body>
	<form action="member_action.jsp" method="post">
		<h1 style="text-align: center;">회원 가입창</h1>
		<table align="center">
			<tr>
				<td width="100px">
					<p align="right">아이디
				</td>
				<td>
					<input type="text" name="id">
				</td>
			</tr>
			<tr>
				<td width="100px">
					<p align="right">비 번
				</td>
				<td>
					<input type="password" name="pwd">
				</td>
			</tr>
			<tr>
				<td width="100px">
					<p align="right">이 름
				</td>
				<td>
					<input type="text" name="name">
				</td>
			</tr>
			<tr>
				<td width="100px">
					<p align="right">이메일
				</td>
				<td>
					<input type="email" name="email">
				</td>
			</tr>
			<tr>
				<td>
				</td>
				<td>
					<input type="reset" value="다시입력">
					<input type="submit" value="가입하기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>