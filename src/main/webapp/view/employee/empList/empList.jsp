<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/employee/empList/empList.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchResultTable/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchLayout/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/components/aside/aside.js "></script>
</head>
<body>
	<%@ include file="../../../components/header/header.jsp" %>
	<main>
		<%@ include file="../../../components/aside/aside.jsp" %>
		<div class="innerContainer" id="container1">
			<div class="innerTitle"><h1>직원 목록</h1></div>
			<div class="innerInformationContainer">
				<div class="innerSubTitle"><h2>기본 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">이름(ID)</div>
						<input id="employeeNameSearchInput"></input>
						<div class="innerInformationRowTitle">소속 지점</div>
						<select id="bankLocation" class="innerSelectBox">
							<option value="location1">성수역점</option>
							<option value="location2">서울숲점</option>
							<option value="location3">신자양점</option>
						</select>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">부서</div>
						<select id="department" class="innerSelectBox">
							<option value="deptLoan1">대출1팀</option>
							<option value="deptLoan2">대출2팀</option>
							<option value="deptLoan3">대출3팀</option>
						</select>
						<div class="innerInformationRowTitle">직책</div>
						<select id="empPosition" class="innerSelectBox">
							<option value="manager">차장</option>
							<option value="assi_manager">과장</option>
							<option value="supervisor">주임</option>
						</select>
					</div>
					
				</div>
			</div>
		</div>
	</main>
	<script>
		generateMenu('employee');		
	</script>
	<script src="${pageContext.request.contextPath}/view/employee/empList/empList.js "></script>
</body>
</html>