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
					<input class="headerSessionButton" value="연장" type="submit">
				</div>
				<div class="headerSessionName">이상준 대리님</div>
				<form id="logout" action="${pageContext.request.contextPath}/LogoutController" method="post">
					<input class="headerSessionLogoutButton" value="로그아웃" type="submit">
				</form>
			</div>
	</header>
</body>
<script>
	const headerSessionName = document.querySelector(".headerSessionName");
	const loginName = "<%= request.getSession().getAttribute("loginName") %>";
	headerSessionName.innerHTML = loginName + " <%= request.getSession().getAttribute("loginPosition") %>님";
	const headerSessionTime = document.querySelector(".headerSessionTime");
	headerSessionTime.textContent = <%= request.getSession().getMaxInactiveInterval() %> + " 초 뒤 자동 로그아웃";
	const timerReset = document.querySelector(".headerSessionButton");
	timerReset.addEventListener('click', ()=> {
		location.reload();
	});
	
	if (loginName == "null") {
		window.location.href = "${pageContext.request.contextPath}/view/login/login.jsp";
		alert("로그인이 필요합니다.");
	}
	
	function updateSessionTimer() {
		const headerSessionTime = document.querySelector(".headerSessionTime");
		let sessionTimeout = <%= session.getMaxInactiveInterval() %>;
		
		const timer = setInterval(function() {
			sessionTimeout--;

			if (sessionTimeout <= 0) {
				clearInterval(timer);
				headerSessionTime.textContent = "세션 만료";
				window.location.href = "${pageContext.request.contextPath}/view/login/login.jsp";	
			} else {
				headerSessionTime.textContent = sessionTimeout + " 초 뒤 자동 로그아웃";
			}
		}, 1000);
	}

	updateSessionTimer();
</script>
</html>
