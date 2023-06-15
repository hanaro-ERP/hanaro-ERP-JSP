<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchResultTable/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchLayout/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/components/aside/aside.js "></script>
</head>
<body>
	<%@ include file="../../../components/header/header.jsp" %>
	<main>
		<%@ include file="../../../components/aside/aside.jsp" %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>여신 상품 검색</h1></div>
			<form>
				<div class="innerSubTitle"><h2>상품 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">대출 구분</div>
						<select id="loanType" class="innerSelectBox">
							<option value="creditLoan">신용 대출</option>
							<option value="mortgageLoan">담보 대출</option>
						</select>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">직업</div>
						<ul id="loanProductJob">
							<li>전체</li>
							<li>직장인</li>
							<li>공무원</li>
							<li>군인</li>
							<li>금융인</li>
							<li>전문직</li>
							<li>의사</li>
							<li>자영업</li>
							<li>무직</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">기간</div>
						<ul id="loanProductPeriod">
							<li>전체</li>
							<li>1년</li>
							<li>2년</li>
							<li>3년</li>
							<li>5년</li>
							<li>10년</li>
							<li>10년 이상</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">연소득</div>
						<ul id="loanProductIncome">
							<li>전체</li>
							<li>~2천만원</li>
							<li>~3천만원</li>
							<li>~5천만원</li>
							<li>~1억원</li>
							<li>1억원 이상</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">대출 한도</div>
						<ul id="loanProductLimit">
							<li>전체</li>
							<li>5천만원</li>
							<li>1억원</li>
							<li>5억원</li>
							<li>10억원</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">상품 이름</div>
						<input id="loanProductSearchInput"></input>
					</div>
				</div>
				<div class="innerButtonContainer">
					<button type="button">검색</button>
				</div>
			</form>
		</div>
	</main>
	<script>
		generateMenu('loan');		
	</script>
	<script src="${pageContext.request.contextPath}/view/loanProductList/loanProductList.js "></script>
</body>
</html>
