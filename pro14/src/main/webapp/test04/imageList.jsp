<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
    body {
        font-family: sans-serif;
    }

    .lis_type {
        list-style: none;
        padding: 0;
        width: 600px;
        margin: 30px auto;
        border: 1px solid #ddd;
        border-radius: 6px;
    }

    .lis_type > li {
        display: flex;
        align-items: center;
        padding: 12px 15px;
        border-bottom: 1px solid #eee;
    }

    /* 헤더(첫 번째 li) 스타일 */
    .lis_type > li:first-child {
        font-weight: bold;
        background: #f8f8f8;
        border-bottom: 2px solid #ddd;
    }

    .lis_type > li span {
        flex: 1;
        text-align: center;
    }

    .lis_type > li a {
        text-decoration: none;
        color: #333;
        flex: 1;
        text-align: center;
    }

    .lis_type > li img {
        border-radius: 4px;
        border: 1px solid #ddd;
    }
</style>
<meta charset="UTF-8">
<title>이미지 리스트</title>
</head>
<body>
	<ul class="lis_type">
		<li>
			<span style="margin-left: 50px">이미지</span>
			<span>이미지 이름</span>
			<span>선택하기</span>
		</li>
		<c:forEach var="i" begin="1" end="9" step="1">
			<li>
				<a href="#" style="margin-left: 50px">
					<img alt="" src="../image/재즈클럽.png" width="90px" height="90px" />
				</a>
				<a href="#">
					<strong>이미지 이름: 재즈클럽${i }</strong>
				</a>
				<a href="#">
					<input type="checkbox" name="chk${i }">
				</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>