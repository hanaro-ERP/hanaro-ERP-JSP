<%@page import="DTO.CustomerDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchLayout.css?ver=1">
</head>
<body style="padding: 0 20px">
<div class="innerSubTitle"><h2>고객 정보</h2></div>
<div class="innerInformation">
	<div class="innerInformationRow">
		<div class="innerInformationRowTitle">고객 이름</div>
		<p>${customer.customerName}</p>
		<div class="innerInformationRowTitle">고객 ID</div>
		<p>${customer.customerId}</p>
	</div>
	<div class="innerInformationRow">
		<div class="innerInformationRowTitle">주민번호</div>
		<p>${customer.identification}</p>
		<div class="innerInformationRowTitle">전화번호</div>
		<p>${customer.phoneNumber}</p>
	</div>
	<div class="innerInformationRow">
		<div class="innerInformationRowTitle">나이</div>
		<p>${customer.age}</p>
		<div class="innerInformationRowTitle">성별</div>
		<p>${customer.strGender}</p>					
	</div>
	<div class="innerInformationRow">
		<div class="innerInformationRowTitle">담당 직원</div>
		<p>${customer.employeeName}</p>
		<div class="innerInformationRowTitle">담당 지점</div>
		<p>${customer.bankName}</p>
	</div>
	<div class="innerInformationRow">
		<div class="innerInformationRowTitle">고객 분류</div>
		<p>${customer.grade}</p>
		<div class="innerInformationRowTitle">신용 등급</div>
		<p>${customer.credit}</p>
	</div>
	<div class="innerInformationRow">
		<div class="innerInformationRowTitle">소속 국가</div>
		<p>${customer.country}</p>
		<div class="innerInformationRowTitle">거주지</div>
		<p>${customer.address}</p>
	</div>
	<div class="innerInformationRow">
		<div class="innerInformationRowTitle">직업 코드</div>
		<p>${customer.jobCode}</p>
		<div class="innerInformationRowTitle">보증인</div>
		<p>${customer.guarantor}</p>
	</div>
	<div class="modifyButtonBox">
		<a class="modifyLoanButton" href="/customer/modification?id=${customer.customerId}" onclick="return confirmModify()">수정하기</a>
	</div>
</div>
<script>
	const isAdmin = "<%= request.getSession().getAttribute("isAdmin") %>";
	
	var btnBox = document.querySelector(".modifyButtonBox");
	btnBox.style.display = 'none';
	
	if (isAdmin === "true") {
		btnBox.style.display = 'flex';
	} else {
		btnBox.style.display = 'none';
	}

	function confirmModify() {
		if (confirm("고객 정보를 수정하시겠습니까?")) {
			return true; 
		} else {
			return false; 
		}
	}
	
	<% CustomerDTO customer = (CustomerDTO) request.getAttribute("customer"); %>
	<%	String mod = customer.getMod(); %>
	if ("<%= mod %>" !== null) {
		if ("<%= mod %>" === "1") {
			alert("고객 정보가 성공적으로 수정되었습니다.");
			window.opener.location.href = "/navigation/customer";
		} else if ("<%= mod %>" === "-1") {
			alert("고객 정보 수정에 실패하였습니다.");
			window.opener.location.href = "/navigation/customer";
		}
	}
	
	document.addEventListener('keydown', function(event) {
		if (event.key === 'Escape') {
			window.close();
		}
	});
	
	window.history.pushState(null, null, window.location.href);
	window.addEventListener('popstate', function(event) {
		window.history.pushState(null, null, window.location.href);
	});
</script>
</body>
</html>