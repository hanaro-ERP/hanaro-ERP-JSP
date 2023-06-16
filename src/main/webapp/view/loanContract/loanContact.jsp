<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/loanContract/loanContract.css?ver=1">
<script src="${pageContext.request.contextPath}/components/aside/aside.js "></script>
</head>
<body><%@ include file="../../components/header/header.jsp" %>
	<main>
		<%@ include file="../../components/aside/aside.jsp" %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>여신 이력</h1></div>
			<div class="loanContractInformationContainer">
				<div class="innerSubTitle"><h2>여신 정보</h2></div>
				<div class="loanContractInformation">
					<div class="loanContractInformationRow">
						<div class="loanContractInformationRowTitle">상품 이름</div>
						<input class="loanContractSearchInput" ></input>
						<div class="loanContractInformationRowTitle">대출 구분</div>
						<select id="loanType">
							<option value="creditLoan">신용 대출</option>
							<option value="mortgageLoan">담보 대출</option>
						</select>
					</div>	
					<div class="loanContractInformationRow">
						<div class="loanContractInformationRowTitle">고객 이름</div>
						<input class="loanContractSearchInput" ></input>
						<div class="loanContractInformationRowTitle">담당 직원</div>
						<input class="loanContractSearchInput" ></input>
					</div>					
					<div class="loanContractInformationRow">
						<div class="loanContractInformationRowTitle">대출일</div>
						<ul class="loanContractDate" id="loanContractStartDate">
							<li>전체</li>
							<li class ="directInput">직접입력</li>
							<select class="yearSelect"> </select>
							<select class="monthSelect"> </select>
							<select class="daySelect"> </select>
						</ul>
					</div>
					<div class="loanContractInformationRow">
						<div class="loanContractInformationRowTitle">만기일</div>
						<ul class="loanContractDate" id="loanContractEndDate">
							<li>전체</li>
							<li class="directInput">직접입력</li>
							<select class="yearSelect"> </select>
							<select class="monthSelect"> </select>
							<select class="daySelect"> </select>
						</ul>
					</div>
					<div class="loanContractInformationRow" id="balance">
						<div class="loanContractInformationRowTitle">대출 잔액</div>
						<ul id="balanceList">
							<li>전체</li>		
							<li id="directInput">직접 입력&nbsp;&nbsp;
								<input>&nbsp;만원 이상&nbsp;&nbsp;
								<input>&nbsp;만원 이상
							</li>
							<li>~2천만원</li>
							<li>~3천만원</li>
							<li>~5천만원</li>
							<li>~1억원</li>
							<li>1억원 이상</li>
						</ul>
					</div>
					<div class="loanContractInformationRow">
						<div class="loanContractInformationRowTitle">연체</div>
						<ul id="loanContractLimit">
							<li>없음</li>
							<li>~6개월</li>
							<li>~1년</li>
							<li>~3년</li>
							<li>~5년</li>
							<li>5년 이상</li>
						</ul>
					</div>
				</div>				
			</div>
		</div>
	</main>
	<script>
		generateMenu('loan');		
	</script>
	<script src="${pageContext.request.contextPath}/view/loanContract/loanContract.js "></script>
</body>
</html>
