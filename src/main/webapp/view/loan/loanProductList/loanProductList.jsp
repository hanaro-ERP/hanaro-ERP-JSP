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
			<form action="${pageContext.request.contextPath}/loanProductSearch" method="post">
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
							<li><input type="checkBox" value="" id="jobAll">전체</li>
							<li><input type="checkBox" value="직장인" id="jobEmployee">직장인</li>
							<li><input type="checkBox" value="공무원" id="jobGovernment">공무원</li>
							<li><input type="checkBox" value="군인" id="jobMilitary">군인</li>
							<li><input type="checkBox" value="금융인" id="jobFinance">금융인</li>
							<li><input type="checkBox" value="전문직" id="jobProfessional">전문직</li>
							<li><input type="checkBox" value="의사" id="jobDoctor">의사</li>
							<li><input type="checkBox" value="자영업" id="jobSelfEmployed">자영업</li>
							<li><input type="checkBox" name="dynamicName" value="무직" id="jobUnemployed">무직</li>
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
					<button type="submit">검색</button>
				</div>
			</form>
			<% String[] selectedJobs = (String[]) request.getAttribute("searchResults");
				if (selectedJobs != null) {
					for (String job : selectedJobs) {
						out.println(job);
						out.println("<br>");
					}
				}
			%>
			<div>
				<div class="innerSubTitle"><h2>검색 결과</h2></div>
				<table class="searchTable" id="loanSearchTable">
					<tr>
						<th>상품 ID</th>
						<th>대출 구분</th>
						<th>담보 종류</th>
						<th>연소득</th>
						<th>상품 이름</th>
						<th>대출 금액</th>
						<th>최대 기간</th>
						<th>대출 이율</th>
					</tr>
					<tr>
						<td>001</td>
						<td>신용 대출</td>
						<td>-</td>
						<td>1억 이상</td>
						<td>쉽고빠른상준론</td>
						<td>10억</td>
						<td>5년</td>
						<td>10%</td>
					</tr>
					<tr>
						<td>001</td>
						<td>신용 대출</td>
						<td>-</td>
						<td>1억 이상</td>
						<td>쉽고빠른상준론</td>
						<td>10억</td>
						<td>5년</td>
						<td>10%</td>
					</tr>
					<tr>
						<td>001</td>
						<td>신용 대출</td>
						<td>-</td>
						<td>1억 이상</td>
						<td>쉽고빠른상준론</td>
						<td>10억</td>
						<td>5년</td>
						<td>10%</td>
					</tr>
				</table>
			</div>
		</div>
	</main>
	<script>
		generateMenu('loan', 'loanProductList');		
	</script>
	<script src="${pageContext.request.contextPath}/view/loan/loanProductList/loanProductList.js "></script>
</body>
</html>
