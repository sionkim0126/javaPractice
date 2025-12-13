<%@page import="java.util.Date"%>
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
<title>포멧팅 태그 라이브러리 예제 실습</title>
</head>
<body>
	    <h2>fmt의 number 태그를 이용한 숫자 포맷팅 예제</h2>

    <!-- 가격 데이터를 JSTL 변수로 저장 -->
    <c:set var="price" value="100000000" />

    <!-- 일반 숫자 형식으로 포맷 후 변수에 저장 -->
    <fmt:formatNumber value="${price}" type="number" var="priceNumber" />

    통화로 표현 시 :
    <!-- 통화 형식으로 출력 (원화, 천 단위 콤마 적용) -->
    <fmt:formatNumber 
        value="${price}" 
        type="currency" 
        groupingUsed="true" 
        currencySymbol="₩" />
    <br>

    퍼센트로 표현 시 :
    <!-- percent는 기본적으로 값에 100을 곱함 -->
    <fmt:formatNumber 
        value="${price}" 
        type="percent" 
        groupingUsed="true" />
    <br>

    일반 숫자로 표현 시 :
    <!-- 위에서 var로 저장한 숫자 출력 -->
    ${priceNumber}
    <br><br>

    <h2>formatDate 예제</h2>

    <!-- 현재 날짜/시간을 JSTL 변수에 저장 -->
    <c:set var="now" value="<%= new Date() %>" />

    <!-- 날짜만 (긴 형식) -->
    <fmt:formatDate value="${now}" type="date" dateStyle="full"/>
    <br>

    <!-- 날짜만 (짧은 형식) -->
    <fmt:formatDate value="${now}" type="date" dateStyle="short"/>
    <br>

    <!-- 시간만 출력 -->
    <fmt:formatDate value="${now}" type="time"/>
    <br>

    <!-- 날짜 + 시간 모두 출력 -->
    <fmt:formatDate 
        value="${now}" 
        type="both" 
        dateStyle="full" 
        timeStyle="full"/>
    <br>

    <!-- 사용자 지정 날짜 포맷 -->
    <!-- yyyy: 연도 / MM: 월 / dd: 일 / HH: 24시간 / mm: 분 / ss: 초 -->
    <fmt:formatDate 
        value="${now}" 
        pattern="yyyy-MM-dd HH:mm:ss" />
    <br><br>

    한국 현재 시간 :
    <!-- 서버 기준 시간 -->
    <fmt:formatDate 
        value="${now}" 
        type="both" 
        dateStyle="full" 
        timeStyle="full" />
    <br><br>

    <!-- 타임존 변경 -->
    <fmt:timeZone value="America/New_York">
        뉴욕 현재 시간 :
        <fmt:formatDate 
            value="${now}" 
            type="both" 
            dateStyle="full" 
            timeStyle="full"/>
        <br>
    </fmt:timeZone>

</body>
</html>