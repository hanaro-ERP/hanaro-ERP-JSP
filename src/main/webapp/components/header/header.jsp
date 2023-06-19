<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/default.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/header/header.css?ver=1">
</head>
<body>
	<header>
		<div class="headerLogoContainer">
			<a class="headerLogo" href="">
				<green style="color: #2EB400">하나로</green>여신
				<br>
				관리시스템
			</a>
		</div>
		<ul class="headerMenuContainer">
			<li><a href="">고객관리</a></li>
			<li><a href="">직원관리</a></li>
			<li><a href="">여신관리</a></li>
			<li><a href="">수신관리</a></li>
		</ul>
		<div class="headerSessionInformationContainer">
			<div class="headerSessionTimeout">
				<div class="headerSessionTime">59분 40초 뒤 자동 로그아웃</div>
				<div class="headerSessionButton">연장</div>
			</div>
			<div class="headerSessionName">이상준 대리님</div>
			<div class="headerSessionLogoutButton">로그아웃</div>
		</div>
	</header>
</body>
</html>
