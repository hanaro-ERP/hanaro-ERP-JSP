<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인: 하나로여신관리시스템</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/login/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/default.css?ver=1">
</head>
<body>
	<div class="container">
		<div class="loginOuterbox">
			<div class="loginInnerbox">
				<div class="loginTitleBox">사원등록</div>
				<form class="loginBodyBox" action="${pageContext.request.contextPath}/RegisterController" method="post">
					<input class="loginBodyIDPW" name="employeeId" id="employeeId" placeholder="회원번호" type="text" maxlength="8"></input>
					<input class="loginBodyIDPW" name="password" id="password" placeholder="비밀번호" type="password" maxlength="20"></input>
					<input class="loginBodyIDPW" name="name" id="name" placeholder="이름" type="text" maxlength="8"></input>
					<input class="loginBodyIDPW" name="position" id="position" placeholder="직책" type="text" maxlength="20"></input>
					<input class="loginBodyButton makeButtonGreen" id="loginSubmit" value="사원등록" type="submit"></input>
				</form>
				<p id="errorMessageBox"style="color: red"></p>
				<div class="loginSignupButton">하나은행</div>
			</div>
		</div>
	</div>
</body>
<script>
	const isFailed = "<%= request.getSession().getAttribute("failed") %>";
	if (isFailed == "failed") {
		alert("회원가입 실패");
	}
</script>
</html>
