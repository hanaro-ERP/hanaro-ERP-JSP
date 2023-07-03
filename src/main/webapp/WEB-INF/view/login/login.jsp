<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하나로여신관리시스템 - 로그인</title>
<link rel="icon" href="${pageContext.request.contextPath}/public/images/favicon.svg">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/login.css">
</head>
<body>
	<div class="container">
		<div class="loginOuterbox">
			<div class="loginInnerbox">
				<div class="loginTitleBox">로그인</div>
				<form class="loginBodyBox" action="${pageContext.request.contextPath}/AuthController/Login/" method="post">
					<input class="loginBodyIDPW" name="employeeId" id="employeeId" placeholder="회원번호" type="text" maxlength="8"></input>
					<input class="loginBodyIDPW" name="password" id="password" placeholder="비밀번호" type="password" maxlength="20"></input>
					<input class="loginBodyButton" id="loginSubmit" value="로그인" type="submit"></input>
				</form>
				<p id="errorMessageBox"style="color: red"></p>
				<div class="loginSignupButton">하나로여신관리시스템</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/login/login.js"></script>
	<script>
		const sessionId = "<%= request.getSession().getAttribute("sessionId") %>";
		console.log(sessionId);
		if (sessionId !== "null") {
			window.location.href = "${pageContext.request.contextPath}/navigation/main";			
		}
 		let previousEmployeeId = "<%= request.getSession().getAttribute("employeeId") %>";
		let errorMessage = "<%= request.getSession().getAttribute("errorMessage") %>";
		const employeeIdBox = document.querySelector("#employeeId");
		const errorMessageBox = document.querySelector("#errorMessageBox");
		if (previousEmployeeId !== "null") {
			employeeIdBox.value = previousEmployeeId;			
		}
		if (errorMessage !== "null"){
			errorMessageBox.innerHTML = errorMessage;				
		}
	</script>
</body>
</html>
