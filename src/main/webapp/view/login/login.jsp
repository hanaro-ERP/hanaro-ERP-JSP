<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" href="./login.css">
</head>

<body>
	<div class="container">
		<div class="loginOuterbox">
			<div class="loginInnerbox">
				<div class="loginTitleBox">
					로그인
				</div>
				<div class="loginBodyBox">
					<input class="loginBodyIDPW" id="loginID" placeholder="회원번호" type="text" maxlength="20"></input>
					<input class="loginBodyIDPW" id="loginPW" placeholder="비밀번호" type="password" maxlength="50"></input>
					<button class="loginBodyButton" id="loginSubmit">로그인</button>
				</div>
				<div class="loginSignupButton">
					하나은행
				</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/view/login/login.js "></script>
</body>
</html>
