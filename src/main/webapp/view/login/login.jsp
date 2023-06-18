<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/login/login.css">
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
					<% 
						String employeeId = (String) request.getAttribute("employeeId");
						if (employeeId != null) {
					%>
						<input class="loginBodyIDPW" name="employeeId" id="employeeId" placeholder="회원번호" value="<%= employeeId %>" type="text" maxlength="8"></input>
					<%
						} else {
					%>
						<input class="loginBodyIDPW" name="employeeId" id="employeeId" placeholder="회원번호" type="text" maxlength="8"></input>
					<%
						}
					%>
					<input class="loginBodyIDPW" name="password" id="password" placeholder="비밀번호" type="password" maxlength="20"></input>
					<input class="loginBodyButton" id="loginSubmit" value="로그인" type="submit"></input>
				</form>
				<% 
					String errorMessage = (String) request.getAttribute("errorMessage");
					if (errorMessage != null) {
				%>
					<p style="color: red"><%= errorMessage %></p>
				<%
					}
				%>
				<div class="loginSignupButton">
					하나은행
				</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/view/login/login.js"></script>
</body>
</html>
