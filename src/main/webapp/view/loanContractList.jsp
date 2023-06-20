<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Contract</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loanContractList.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchResultTable/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchLayout/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/components/aside/aside.js "></script>
</head>
<body><%@ include file="../components/header/header.jsp" %>
	<main>
		<%@ include file="../components/aside/aside.jsp"%>
		<div class="innerContainer">
			<div class="innerTitle">
				<h1>여신 이력</h1>
			</div>
			<form action="${pageContext.request.contextPath}/loanContract"
				method="post">
				<div class="innerSubTitle">
					<h2>여신 정보</h2>
				</div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">상품 이름</div>
						<input name="productName" class="loanContractSearchInput"></input>
						<div class="innerInformationRowTitle">대출 구분</div>
						<select id="loanType" name="loanType">
							<option value="신용대출">신용 대출</option>
							<option value="담보대출">담보 대출</option>
						</select>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">고객 이름</div>
						<input name="customerName" class="loanContractSearchInput"></input>
						<div class="innerInformationRowTitle">담당 직원</div>
						<input name="employeeName" class="loanContractSearchInput"></input>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">대출일</div>
						<ul class="loanIssueDate" id="loanContractStartDate">
							<li>
								<input type="checkbox" value="전체" id="issueDateAll"
									name="loanContractStartDate">전체
							</li>
							<li id="directInput">
								<p>직접 입력</p> 
								<select name="loanContractStartDate"
								class="yearSelect" disabled="true"></select> 
								<select name="loanContractStartDate" class="monthSelect"
								disabled="true"></select> 
								<select name="loanContractStartDate" class="daySelect"
								disabled="true"></select>
							</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">만기일</div>
						<ul class="loanMuturityDate" id="loanContractEndDate">
							<li><input type="checkbox" value="전체" id="muturityDateAll"
								name="loanContractEndDate">전체</li>
							<li id="directInput">
								<p>직접 입력</p> 
								<select name="loanContractEndDate"
								class="yearSelect" disabled="true"></select> 
								<select name="loanContractEndDate" class="monthSelect"
								disabled="true"></select> 
								<select name="loanContractEndDate" class="daySelect"
								disabled="true"></select>
							</li>
						</ul>
					</div>
					<div class="innerInformationRow" id="balance">
						<div class="innerInformationRowTitle">대출 잔액</div>
						<ul id="balanceList">
							<li><input type="checkbox" value="전체" id="priceAll">전체</li>
							<li id="directInput">
								<p>직접 입력</p> 
								<input name="balanceList" class="directInputValue" disabled="true">&nbsp;만원 이상&nbsp;&nbsp; 
								<input name="balanceList" class="directInputValue" disabled="true">&nbsp;만원 이하&nbsp;
							</li>
							<li><input type="checkbox" value="~2천만원" id="price2000">~2천만원</li>
							<li><input type="checkbox" value="~3천만원" id="price3000">~3천만원</li>
							<li><input type="checkbox" value="~5천만원" id="price5000">~5천만원</li>
							<li><input type="checkbox" value="~1억원" id="price10000">~1억원</li>
							<li><input type="checkbox" value="1억원 이상"
								id="price10000plus">1억원 이상</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">연체</div>
						<ul id="loanContractLimit">
							<li><input type="checkbox" value="전체" id="periodAll">전체</li>
							<li><input type="checkbox" value="없음" id="periodNone">없음</li>
							<li><input type="checkbox" value="~6개월" id="period6">~6개월</li>
							<li><input type="checkbox" value="~1년" id="period12">~1년</li>
							<li><input type="checkbox" value="~3년" id="period36">~3년</li>
							<li><input type="checkbox" value="~5년" id="period60">~5년</li>
							<li><input type="checkbox" value="5년 이상" id="period60plus">5년이상</li>
						</ul>
					</div>
				</div>
				<div class="innerButtonContainer">
					<button type="submit">검색</button>
				</div>
			</form>
			<div>
				<%
				String[] searchResults = (String[]) session.getAttribute("searchResults");
				if (searchResults != null) {
					for (String result : searchResults) {
						out.println(result); // 또는 원하는 출력 방식으로 처리
					}
				}
				%>
			</div>
			<div>
				<div class="innerSubTitle">
					<h2>검색 결과</h2>
				</div>
				<table class="searchTable" id="loanSearchTable">
					<tr>
						<th>이력 ID</th>
						<th>대출 구분</th>
						<th>상품 이름</th>
						<th>담당 직원</th>
						<th>고객 이름</th>
						<th>보증인</th>
						<th>대출일</th>
						<th>만기일</th>
						<th>대출 잔액</th>
						<th>상환 방법</th>
						<th>연체</th>
						<th>담보 가치</th>
					</tr>
					<tr>
						<td>001</td>
						<td>신용 대출</td>
						<td>징검다리론</td>
						<td>김철수</td>
						<td>이영수</td>
						<td>최영희</td>
						<td>2023-06-14</td>
						<td>2043-06-13</td>
						<td>10,000,000원</td>
						<td>원금 균등 분할 상환</td>
						<td>N</td>
						<td>4,000,000원</td>
					</tr>
					<tr>
						<td>001</td>
						<td>신용 대출</td>
						<td>징검다리론</td>
						<td>김철수</td>
						<td>이영수</td>
						<td>최영희</td>
						<td>2023-06-14</td>
						<td>2043-06-13</td>
						<td>10,000,000원</td>
						<td>원금 균등 분할 상환</td>
						<td>N</td>
						<td>4,000,000원</td>
					</tr>
				</table>
			</div>
		</div>
	</main>
	<script src="${pageContext.request.contextPath}/components/searchLayout/searchLayout.js"></script>
	<script>
		generateMenu('loan');		
	</script>
	<script src="${pageContext.request.contextPath}/js/loanContractList.js"></script>
</body>
</html>
