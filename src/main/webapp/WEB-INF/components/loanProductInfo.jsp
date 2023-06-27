<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchLayout.css?ver=1">
</head>
<body>
<%@ page import="DTO.LoanProductDTO"%>
<%@ page import="util.LoanUtil"%>
	<% LoanProductDTO loanProduct =(LoanProductDTO) request.getAttribute("loanProduct"); %>
	<% LoanUtil loanUtil = new LoanUtil(); %>
	<% 	
	String mod = loanProduct.getMod();
	long minAmount = loanProduct.getMinAmount();
	long maxAmount = loanProduct.getMaxAmount(); 
	String minUnit = minAmount >= 100000000 ? (minAmount/100000000) + "억원" : (minAmount/10000) + "만원";
	String maxUnit = maxAmount >= 100000000 ? (maxAmount/100000000) + "억원" : (maxAmount/10000) + "만원";
	
	String job = loanUtil.convertJobCode(loanProduct.getJob());
	%>
<div class="innerSubTitle"><h2>상품 정보</h2></div>
<div class="innerInformation">
	<div class="innerInformationRow">
		<div class="innerInformationRowTitle">상품 ID</div>
		<p>${loanProduct.loanName}</p>
		<div class="innerInformationRowTitle">상품 이름</div>
		<p>${loanProduct.loanId}</p>
	</div>
	<div class="innerInformationRow">
		<div class="innerInformationRowTitle">대출 구분</div>
		<p>${loanProduct.loanType}</p>
		<div class="innerInformationRowTitle">담보 종류</div>
		<p>${loanProduct.collateral}</p>
	</div>
	<div class="innerInformationRow">
		<div class="innerInformationRowTitle">직업</div>
		<p><%= job %></p>
		<div class="innerInformationRowTitle">연소득</div>
		<p>${loanProduct.income}</p>					
	</div>
	<div class="innerInformationRow">
		<div class="innerInformationRowTitle">대출 금액</div>
		<p>최소 <%= minUnit %> 최대 <%= maxUnit %></p>
		<div class="innerInformationRowTitle">대출 기간</div>
		<p>최소 ${loanProduct.minDuration}년 최대 ${loanProduct.maxDuration}년</p>
	</div>
	<div class="innerInformationRow">
		<div class="innerInformationRowTitle">대출 이율</div>
		<p>최소 ${loanProduct.minRate}% 최대 ${loanProduct.maxRate}%</p>
		<div class="innerInformationRowTitle">가입자 수</div>
		<p>${loanProduct.subscriberCount}명</p>
	</div>
	<div class="modifyButtonBox"><a href = "/hanaro-ERP-JSP/loan/modification?id=${loanProduct.loanId}">수정하기</a></div>
</div>
<script>
	if ("<%= mod %>" !== null) {
		if ("<%= mod %>" === "1") {
			alert("여신 상품이 성공적으로 수정되었습니다.");
		} else if ("<%= mod %>" === "-1") {
			alert("여신 상품이 수정에 실패하였습니다.");
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