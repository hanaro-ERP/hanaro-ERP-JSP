<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/deposit/depositProductList.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js "></script>
</head>
<body>
	<%@ page import="java.util.Arrays"%>
	<%@ page import="java.util.List" %>
	<%@ page import="DTO.AccountDTO" %>
	<%@ page import="DTO.AccountSearchDTO" %>
	<%@ page import="DTO.TransactionDTO" %>
	<%@ include file="../../components/header.jsp" %>
	<main>
		<%@ include file="../../components/aside.jsp" %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>계좌 검색</h1></div>
			<form action="${pageContext.request.contextPath}/depositList/searchAccounts" method="post">
				<div class="innerSubTitle"><h2>상품 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">고객 이름</div>
						<input name="customerName" id="depositCustomerSearchInput" class="innerSearchInput3"></input>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">주민번호</div>
						<input name="identification" class="innerMiddleInput2"></input>&nbsp;-&nbsp;
						<input name="identification" class="innerMiddleInput2"></input>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">계좌 번호</div>
						<input name="accountNumber" id="depositAccountNumberSearchInput" class="innerSearchInput"></input>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">계좌 유형</div>
						<ul id="depositType">
							<li><input type="checkbox" value="전체" />전체</li>
							<li><input type="checkbox" value="예금" />예금</li>
							<li><input type="checkbox" value="적금" />적금</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">계좌 개설일</div>
						<ul class="loanIssueDate" id="depositStartDate">
							<li>
								<input type="checkbox" value="전체" id="issueDateAll"
									name="depositStartDate">전체
							</li>
							<li id="directInput">
								<p>직접 입력</p> 
								<select name="depositStartDate"
								class="yearSelect" disabled="true"></select> 
								<select name="depositStartDate" class="monthSelect"
								disabled="true"></select> 
								<select name="depositStartDate" class="daySelect"
								disabled="true"></select>
							</li>
						</ul>
					</div>
					<div class="innerInformationRow" id="balanceRow">
						<div class="innerInformationRowTitle">계좌 잔액</div>
						<ul id="depositBalance" class="directInputUl">
							<li><input type="checkbox" value="전체" />전체</li>
							<li id="directInput">
								<p>직접 입력</p>
								<input id="startBalance" name="depositBalance" class="directInputValue" disabled="true"><p class="directInputText"> 만원 이상</p>
								<input id="endBalance" name="depositBalance" class="directInputValue" disabled="true"><p class="directInputText"> 만원 이하</p>
							</li>
							<li><input type="checkbox" value="~2천만원" />~2천만원</li>
							<li><input type="checkbox" value="~3천만원" />~3천만원</li>
							<li><input type="checkbox" value="~5천만원" />~5천만원</li>
							<li><input type="checkbox" value="~1억원" />~1억원</li>
							<li><input type="checkbox" value="1억원 이상" />1억원 이상</li>
						</ul>
					</div>
				</div>				
				<div class="innerButtonContainer">
					<button class="searchButton" type="submit">검색</button>
				</div>
			</form>
			<div class="searchTitle"><h1>검색 결과</h1></div>
			<table class="searchTable" id="depositSearchTable">
				<tr>
					<th>계좌 유형</th>
					<th>계좌 번호</th>
					<th>계좌 개설일</th>
					<th>고객 이름</th>
					<th>담당 직원</th>
					<th>계좌 잔액</th>
				</tr>
				<!-- 받아온 계좌 정보를 테이블로 나타내는 코드 -->
				<%
				List<AccountDTO> searchedAccountList = (List<AccountDTO>) request.getAttribute("searchedAccountList");

				if (searchedAccountList != null && !searchedAccountList.isEmpty()) {
					for (AccountDTO account : searchedAccountList) {
						%>
						<tr class="searchResultRow" id="<%= account.getAccountId() %>">
							<td style="display: none">
								<form id="<%= account.getAccountId() %>" action="${pageContext.request.contextPath}/depositList/searchTransactions" method="post">
									<input name="selectedAccountId" value="<%= account.getAccountId() %>">
									<input name="selectedAccountNumber" value="<%= account.getAccountNumber() %>">
									<input name="selectedCustomerName" value="<%= account.getCustomerName() %>">
								</form>
							</td>
							<td><%= account.getAccountType() %></td>
							<td><%= account.getAccountNumber() %></td>
							<td><%= account.getStringAccountOpenDate() %></td>
							<td><%= account.getCustomerName() %>
							<td><%= account.getEmployeeName() %></td>
							<td><%= account.getStringAccountBalance() %></td>
						</tr>
						<%
					}
				}
				%>
			</table>
			<div class="popupBox display">
				<svg class="popupExitButton" width="14" height="15" viewBox="0 0 14 15" fill="none" xmlns="http://www.w3.org/2000/svg">
					<path d="M14 1.91L12.59 0.5L7 6.09L1.41 0.5L0 1.91L5.59 7.5L0 13.09L1.41 14.5L7 8.91L12.59 14.5L14 13.09L8.41 7.5L14 1.91Z" fill="#323232"/>
				</svg>
				<div class="popupTitleBox"><h1>입출금 내역</h1></div>
				<div id="historyTableBox">
					<table class="searchTable" id="historyTable">
						<tr>
							<th>계좌일자</th>
							<th>거래유형</th>
							<th>계좌번호</th>
							<th>고객 이름</th>
							<th>입금자명</th>
							<th>거래처</th>
							<th>거래 금액</th>
							<th>계좌 잔액</th>
						</tr>
						<!-- 받아온 계좌 정보를 테이블로 나타내는 코드 -->
						<%
						List<TransactionDTO> searchedTransactionList = (List<TransactionDTO>) request.getAttribute("searchedTransactionList");
		
						if (searchedTransactionList != null && !searchedTransactionList.isEmpty()) {
							for (TransactionDTO transaction : searchedTransactionList) {
								%>
								<tr class="searchResultRow" id="<%= transaction.getAccountId() %>">
									<td><%= transaction.getStringTransactionDate() %></td>
									<td><%= transaction.getTransactionType() %></td>
									<td><%= transaction.getAccountNumber() %></td>
									<td><%= transaction.getCustomerName() %></td>
									<td><%= transaction.getDepositor() %></td>
									<td><%= transaction.getTransactionLocation() %></td>
									<td><%= transaction.getTransactionAmount() %></td>
									<td>procedure 필요</td>
								</tr>
								<%
							}
						}
						%>
					</table>
				</div>
			</div>
		</div>
	</main>
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js"></script>
	<script>
		generateMenu('deposit', 'depositProductList');	
		let showTransactions = "<%= request.getAttribute("showTransactions") %>";
		if (showTransactions == "showTransactions") {
			const popupBox = document.querySelector(".popupBox");
			popupBox.classList.remove("display");
		}

	</script>
	<script src="${pageContext.request.contextPath}/js/deposit/depositProductList.js"></script>
</body>
</html>
