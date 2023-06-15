<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="./login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/default.css?ver=1">
</head>
<body>
	<div class="container">
		<div class="loginOuterbox">
			<div class="loginInnerbox">
				<div class="loginTitleBox">
					로그인
				</div>
				<form class="loginBodyBox" action="${pageContext.request.contextPath}/LoginController" method="post">
					<input class="loginBodyIDPW" name="employeeId"  id="employeeId" placeholder="회원번호" type="text" maxlength="20"></input>
					<input class="loginBodyIDPW" name="password" id="password" placeholder="비밀번호" type="password" maxlength="50"></input>
					<input class="loginBodyButton" id="loginSubmit" value="로그인" type="submit"></input>
				</form>
				<div class="loginSignupButton">
					하나은행
				</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/view/login/login.js "></script>
</body>
</html>
