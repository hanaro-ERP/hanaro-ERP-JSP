<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/header.css?ver=1">
</head>
<body>
	<header>
		<div class="headerLogoContainer">
			<a class="headerLogo" href="${pageContext.request.contextPath}/navigation/main">
				<green style="color: #2EB400">하나로</green>여신
				<br>
				관리시스템
			</a>
		</div>
		<ul class="headerMenuContainer">
			<li><a href="${pageContext.request.contextPath}/navigation/customer">고객관리</a></li>
			<li><a href="${pageContext.request.contextPath}/navigation/employee">직원관리</a></li>
			<li><a href="${pageContext.request.contextPath}/navigation/loanList">여신관리</a></li>
			<li><a href="${pageContext.request.contextPath}/navigation/depositList">수신관리</a></li>
		</ul>
			<div class="headerSessionInformationContainer">
				<div class="headerSessionTimeout">
					<div class="headerSessionTime"></div>
					<input class="headerSessionButton" value="연장" type="submit">
				</div>
				<div class="headerSessionName"></div>
				<form id="logout" action="${pageContext.request.contextPath}/AuthController/Logout/" method="post">
					<input class="headerSessionLogoutButton" value="로그아웃" type="submit">
				</form>
			</div>
	</header>
</body>
	<script src="${pageContext.request.contextPath}/js/components/header.js "></script>
<script>
	const headerSessionName = document.querySelector(".headerSessionName");
	
	const loginName = "<%= request.getSession().getAttribute("loginName") %>";
	headerSessionName.innerHTML = loginName + " <%= request.getSession().getAttribute("loginPosition") %>님";
	
	const isAdmin = "<%= request.getSession().getAttribute("isAdmin") %>";
	if (isAdmin === "true") {
		headerSessionName.classList.add("adminName");
	}
	
	const headerSessionTime = document.querySelector(".headerSessionTime");
	const sessionTimeout = <%= request.getSession().getMaxInactiveInterval() %>;
	
	let sessionStartTime = new Date();
	headerSessionTime.textContent = parseInt(sessionTimeout/60) + "분 " + sessionTimeout%60 + "초 뒤 자동 로그아웃";
	const timerReset = document.querySelector(".headerSessionButton");
	timerReset.addEventListener('click', ()=> {
		location.reload();
	});
	
	if (loginName == "null") {
		window.location.href = "${pageContext.request.contextPath}/navigation/login";
		alert("로그인이 필요합니다.");
	}
	
	function updateSessionTimer() {
		const headerSessionTime = document.querySelector(".headerSessionTime");
		let sessionTimeout = <%= session.getMaxInactiveInterval() %>;
		
		const timer = setInterval(function() {
			const nowTime = new Date();
			const elapsedTime = parseInt((nowTime - sessionStartTime)/1000);
			const remainingTime = sessionTimeout - elapsedTime;
			
			if (remainingTime == 120) {
				alert("2분 후에 세션이 만료됩니다.");
				headerSessionTime.classList.add("warning");
			}
			if (remainingTime <= 0) {
				clearInterval(timer);
				headerSessionTime.textContent = "세션 만료";
				<%
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
				response.setHeader("Pragma", "no-cache"); // HTTP 1.0
				response.setHeader("Expires", "0"); // Proxies
				%>
				window.location.href = "${pageContext.request.contextPath}/navigation/login";	
			} else {
				if (remainingTime <= 59) {
					headerSessionTime.textContent = remainingTime + " 초 뒤 자동 로그아웃";	
				} else {
					headerSessionTime.textContent = parseInt(remainingTime/60) + "분 " + remainingTime%60 + "초 뒤 자동 로그아웃";
				}
			}
		}, 1000);
	}

	updateSessionTimer();
</script>
</html>
