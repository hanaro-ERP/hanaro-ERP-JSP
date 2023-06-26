<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main/main.css?ver=1">
</head>
<body>
	<div class="container">
		<div class="headerBox">
			<div class="headerContent">
				<green class="mainGreen">하나로</green>여신
				<br>
				관리시스템
			</div>
			<div class="headerSessionInformationContainer">
				<div class="headerSessionTimeout">
					<div class="headerSessionTime"></div>
					<input class="headerSessionButton" value="연장" type="submit">
				</div>
				<div class="headerSessionName"></div>
				<form id="logout" action="${pageContext.request.contextPath}/LogoutController" method="post">
					<input class="headerSessionLogoutButton" value="로그아웃" type="submit">
				</form>
			</div>
		</div>
		<div class="bodyBox">
			<div class="bodyContainer">
				<div class="bodyTitle mainBlack1">
					사용하실 서비스를 선택해주세요!
				</div>
				<div class="bodyContentBox">
					<div class="bodyCardBox first">
						<a class="bodyCard" href="${pageContext.request.contextPath}/navigation/customer">
							<svg class="mainIcon" width="116" height="80" viewBox="0 0 116 80" fill="none" xmlns="http://www.w3.org/2000/svg">
								<path d="M83.7143 40C91.6 40 97.9429 33.6 97.9429 25.7143C97.9429 17.8286 91.6 11.4286 83.7143 11.4286C75.8286 11.4286 69.4286 17.8286 69.4286 25.7143C69.4286 33.6 75.8286 40 83.7143 40ZM40.8572 34.2857C50.3429 34.2857 57.9429 26.6286 57.9429 17.1429C57.9429 7.65714 50.3429 0 40.8572 0C31.3715 0 23.7143 7.65714 23.7143 17.1429C23.7143 26.6286 31.3715 34.2857 40.8572 34.2857ZM83.7143 51.4286C73.2572 51.4286 52.2858 56.6857 52.2858 67.1429V80H115.143V67.1429C115.143 56.6857 94.1715 51.4286 83.7143 51.4286ZM40.8572 45.7143C27.5429 45.7143 0.857178 52.4 0.857178 65.7143V80H40.8572V67.1429C40.8572 62.2857 42.7429 53.7714 54.4 47.3143C49.4286 46.2857 44.6286 45.7143 40.8572 45.7143Z" fill="#4E5968"/>
							</svg>
							<span class="mainIconText">고객</span>
						</a>
						<a class="bodyCard" href="${pageContext.request.contextPath}/navigation/employee">
							<svg class="mainIcon" width="80" height="80" viewBox="0 0 80 80" fill="none" xmlns="http://www.w3.org/2000/svg">
								<path d="M36 48H28C28 28.12 44.12 12 64 12V20C48.52 20 36 32.52 36 48ZM64 36V28C52.96 28 44 36.96 44 48H52C52 41.36 57.36 36 64 36ZM20 8C20 3.56 16.44 0 12 0C7.56 0 4 3.56 4 8C4 12.44 7.56 16 12 16C16.44 16 20 12.44 20 8ZM37.8 10H29.8C28.84 15.68 23.96 20 18 20H6C2.68 20 0 22.68 0 26V36H24V26.96C31.44 24.6 37 18.04 37.8 10ZM68 60C72.44 60 76 56.44 76 52C76 47.56 72.44 44 68 44C63.56 44 60 47.56 60 52C60 56.44 63.56 60 68 60ZM74 64H62C56.04 64 51.16 59.68 50.2 54H42.2C43 62.04 48.56 68.6 56 70.96V80H80V70C80 66.68 77.32 64 74 64Z" fill="#4E5968"/>
								</svg>
							<span class="mainIconText">직원</span>
						</a>
					</div>		
					<div class="bodyCardBox">
						<a class="bodyCard" href="${pageContext.request.contextPath}/navigation/loanList">
							<svg class="mainIcon" width="146" height="84" viewBox="0 0 146 84" fill="none" xmlns="http://www.w3.org/2000/svg">
								<path d="M72.6997 15.6H62.2998V20.8H57.0998C54.2398 20.8 51.8998 23.14 51.8998 26V41.5999C51.8998 44.4599 54.2398 46.7999 57.0998 46.7999H72.6997V51.9998H51.8998V62.3998H62.2998V67.5998H72.6997V62.3998L77.8997 62.3998C80.7597 62.3998 83.0997 60.0598 83.0997 57.1998V41.5999C83.0997 38.7399 80.7597 36.3999 77.8997 36.3999H62.2998V31.1999H83.0997V20.8H72.6997V15.6ZM25.8999 83.1997L109.1 83.1997C114.872 83.1997 119.448 78.5717 119.448 72.7998L119.5 10.4C119.5 5.20005 114.872 6.86646e-05 109.1 6.86646e-05L25.8999 6.86646e-05C20.1279 6.86646e-05 15.5 4.62805 15.5 10.4L15.5 72.7998C15.5 78.5717 20.1279 83.1997 25.8999 83.1997ZM25.8999 10.4L119.5 10.4L119.448 72.7998L25.8999 72.7998L25.8999 10.4Z" fill="#4E5968"/>
								<path d="M122.389 18.4888L115.918 24.9599L127.935 36.9776H91.189V46.7998L127.935 46.222L115.918 58.2398L122.389 64.7108L145.5 41.5998L122.389 18.4888Z" fill="#4E5968"/>
							</svg>
							<span class="mainIconText">여신</span>
						</a>
						<a class="bodyCard" href="${pageContext.request.contextPath}/navigation/deposit">
							<svg class="mainIcon" width="146" height="84" viewBox="0 0 146 84" fill="none" xmlns="http://www.w3.org/2000/svg">
								<path d="M73.3 67.6H83.7V62.4H88.9C91.76 62.4 94.1 60.06 94.1 57.2V41.6C94.1 38.74 91.76 36.4 88.9 36.4H73.3V31.2H94.1V20.8H83.7V15.6H73.3V20.8H68.1C65.24 20.8 62.9 23.14 62.9 26V41.6C62.9 44.46 65.24 46.8 68.1 46.8H83.7V52H62.9V62.4H73.3V67.6ZM120.1 0H36.9C31.128 0 26.552 4.628 26.552 10.4L26.5 72.8C26.5 78.572 31.128 83.2 36.9 83.2H120.1C125.872 83.2 130.5 78.572 130.5 72.8V10.4C130.5 4.628 125.872 0 120.1 0ZM120.1 72.8H26.5L26.552 10.4H120.1V72.8Z" fill="#4E5968"/>
								<path d="M31.7 18.4889L25.2289 24.96L37.2467 36.9778H0.5V46.8L37.2467 46.2222L25.2289 58.24L31.7 64.7111L54.8111 41.6L31.7 18.4889ZM73.3 73.9556H36.3222V83.2H73.3C78.3844 83.2 73.3 77.8844 73.3 72.8L73.3 9.24444C73.3 4.16 78.3844 0 73.3 0H36.3222V9.24444H73.3V73.9556Z" fill="#4E5968"/>
							</svg>
							<span class="mainIconText">수신</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	const headerSessionName = document.querySelector(".headerSessionName");
	const loginName = "<%= request.getSession().getAttribute("loginName") %>";
	headerSessionName.innerHTML = loginName + " <%= request.getSession().getAttribute("loginPosition") %>님";
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
					headerSessionTime.textContent =remainingTime + " 초 뒤 자동 로그아웃";	
				} else {
					headerSessionTime.textContent = parseInt(remainingTime/60) + "분 " + remainingTime%60 + "초 뒤 자동 로그아웃";
				}
				
			}
		}, 1000);
	}

	updateSessionTimer();
</script>
</html>