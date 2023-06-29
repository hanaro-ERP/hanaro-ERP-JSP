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
		<% AccountSearchDTO accountSearchDTO = (AccountSearchDTO) request.getAttribute("accountDTO"); %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>계좌 검색</h1></div>
			<form onsubmit="return validateIdentification()" action="${pageContext.request.contextPath}/depositList/searchAccounts" method="post">
				<div class="innerSubTitle"><h2>상품 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">고객 이름</div>
						<input name="customerName" id="depositCustomerSearchInput" 
						class="innerSearchInput3" value="<%= accountSearchDTO == null || accountSearchDTO.getCustomerName() == null ? "" : accountSearchDTO.getCustomerName() %>"></input>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">주민번호</div>
						<input id="identification1" name="identification1" 
						class="innerMiddleInput2" value="<%= accountSearchDTO == null || accountSearchDTO.getIdentification1() == null ? "" : accountSearchDTO.getIdentification1() %>"></input>&nbsp;-&nbsp;
						<input id="identification2" name="identification2" 
						class="innerMiddleInput2" value="<%= accountSearchDTO == null || accountSearchDTO.getIdentification2() == null ? "" : accountSearchDTO.getIdentification2() %>"></input>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">계좌 번호</div>
						<input name="accountNumber" id="depositAccountNumberSearchInput" 
						class="innerSearchInput" value="<%= accountSearchDTO == null || accountSearchDTO.getAccountNumber() == null ? "" : accountSearchDTO.getAccountNumber() %>"></input>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">계좌 유형</div>
						<ul id="depositType">
							<li id="depositTypeAllLi"><input type="checkbox" value="전체" />전체</li>
							<li id="depositType1Li"><input type="checkbox" value="예금" />예금</li>
							<li id="depositType2Li"><input type="checkbox" value="적금" />적금</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">계좌 개설일</div>
						<ul class="loanIssueDate" id="depositStartDate">
							<li id="depositDateAllLi">
								<input type="checkbox" value="전체" id="issueDateAll" name="depositStartDate">전체
							</li>
							<li id="depositDateInputLi" class="directInput">
								<p>직접 입력</p> 
								<select name="depositStartDate" class="yearSelect" disabled="true"></select> 
								<select name="depositStartDate" class="monthSelect" disabled="true"></select> 
								<select name="depositStartDate" class="daySelect" disabled="true"></select>
							</li>
						</ul>
					</div>
					<div class="innerInformationRow" id="balanceRow">
						<div class="innerInformationRowTitle">계좌 잔액</div>
						<ul id="depositBalance" class="directInputUl">
							<li><input type="checkbox" value="전체" />전체</li>
							<li class="directInput">
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
			<div class="searchTitle"><h1>검색 결과</h1><p><%= (accountSearchDTO != null && accountSearchDTO.getCount() != 0) ? "총 " + accountSearchDTO.getCount() + "개의 검색 결과가 있습니다." : "" %></p></div>
			<table class="searchTable" id="depositSearchTable">
				<tr>
					<th>계좌 ID</th>
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
							<td class="accountId"><%= account.getAccountId() %></td>
							<td><%= account.getAccountType() %></td>
							<td class="accountNumber"><%= account.getAccountNumber() %></td>
							<td><%= account.getStringAccountOpenDate() %></td>
							<td class="accountName"><%= account.getCustomerName() %>
							<td><%= account.getEmployeeName() %></td>
							<td><%= account.getStringAccountBalance() %></td>
						</tr>
						<%
					}
				} else {
					%>
					<tr class="searchResultRow noResultRow">
						<td colspan="20"> 검색 결과가 없습니다. </td>
					</tr>
					<%
				}
				%>
			</table>
				<%
				// customerSearchDTO에서 page 값과 count 변수 추출
				int count = (accountSearchDTO != null && accountSearchDTO.getCount() != 0) ? accountSearchDTO.getCount() : 0;
				int pages = (accountSearchDTO != null && accountSearchDTO.getPage() != 0) ? accountSearchDTO.getPage() : 1;

				// 페이징 처리 로직
				int pageSize = 20; // 한 페이지에 표시할 레코드 수
				int totalPages = (int) Math.ceil((double) count / pageSize); // 전체 페이지 수
				int currentPage = pages; // 현재 페이지
				int startPage = Math.max(1, currentPage - ((currentPage-1) % 10)) ; // 시작 페이지
				int endPage = Math.min(startPage + 9, totalPages); // 끝 페이지

				// 이전 페이지와 다음 페이지 계산
				int prevPage = startPage - 1;
				int nextPage = endPage + 1;
				
				// 이전 페이지와 다음 페이지 범위 검사
				prevPage = Math.max(1, prevPage);
				nextPage = Math.min(totalPages, nextPage);
				%>
				
				<!-- 페이지 번호 표시 -->
				<div class="pagination">
					<% if (currentPage > 1) { %>
						<button type="submit" name="page" value="1"><<</button>
						<button type="submit" name="page" value="<%= prevPage %>"><</button>
					<% } %>
				
					<% for (int i = startPage; i <= endPage; i++) { %>
						<% if (i == currentPage) { %>
							<button type="submit" class="activePage" name="page" value="<%= i %>"><%= i %></button>
						<% } else { %>
							<button type="submit" name="page" value="<%= i %>"><%= i %></button>
						<% } %>
					<% } %>
					
					<% if (currentPage < totalPages) { %>
						<button type="submit" name="page" value="<%= nextPage %>">></button>
						<button type="submit" name="page" value="<%= totalPages %>">>></button>
					<% } %>
				</div>
			</form>
		</div>
	</main>
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js"></script>
	<script src="${pageContext.request.contextPath}/js/deposit/depositProductList.js"></script>
	<script>
		generateMenu('deposit', 'depositProductList');
	</script>
	<%
	String type = null;
	String[] depositStartDate = null;
	String[] depositBalances = null;
	if (accountSearchDTO != null) {
		type = accountSearchDTO.getDepositType();
		depositStartDate = accountSearchDTO.getAccountOpenDate();
		depositBalances = accountSearchDTO.getDepositBalance();
	}
	 %>
	<script>
	<%
	if (type != null) {
		%>
    	const depositTypeAllLi = document.getElementById('depositTypeAllLi');

		if ("<%= type %>" === "예금") {
			const depositType1Li = document.getElementById('depositType1Li');
			selectItem(depositType1Li, "depositType");
			unselectItem(depositTypeAllLi);
		} else if ("<%= type %>" === "적금") {
			const depositType2Li = document.getElementById('depositType2Li');
			selectItem(depositType2Li, "depositType");
			unselectItem(depositTypeAllLi);
		}
		<%
	}
	%>
	<%
	if (depositStartDate != null) {
		if (!depositStartDate[0].equals("전체")) {
			%>
			const depositDateAllLi = document.getElementById('depositDateAllLi')
	    	const depositDateInputLi = document.getElementById('depositDateInputLi');
			const inputs = depositDateInputLi.querySelectorAll('select');
			
			inputs[0].value = "<%= depositStartDate[0] %>"; 
			inputs[1].value = "<%= depositStartDate.length > 1 && depositStartDate[1] != null ? depositStartDate[1] : "" %>" ;
			setDaySelect(false, inputs[0].value, inputs[1].value)
			inputs[2].value = "<%= depositStartDate.length > 2 && depositStartDate[2] != null ? depositStartDate[2] : "" %>" ;
			
			unselectItem(depositDateAllLi);
			selectItem(depositDateInputLi, "depositStartDate");
			toggleDirectInput(depositDateInputLi, true);
	
			<%
		} else {
			%>
			selectItem(depositDateAllLi, "depositStartDate");
			<%
		}
	}
	%>
	const depositBalance = document.getElementById('depositBalance');
	const depositBalanceLi = depositBalance.querySelectorAll('li');
	selectItem(depositBalanceLi[0], "depositBalance");
	<%
	if (depositBalances != null) {
		if (!depositBalances[0].equals("전체")) {
			for (int i = 0; i < depositBalances.length; i++) {
				if (!depositBalances[i].isEmpty()) {
					%>
					unselectItem(depositBalanceLi[0]);
					if ("<%= depositBalances[i] %>" === "~2천만원") {
						selectItem(depositBalanceLi[2], "depositBalance");
					} else if ("<%= depositBalances[i] %>" === "~3천만원") {
						selectItem(depositBalanceLi[3], "depositBalance");
					} else if ("<%= depositBalances[i] %>" === "~5천만원") {
						selectItem(depositBalanceLi[4], "depositBalance");
					} else if ("<%= depositBalances[i] %>" === "~1억원") {
						selectItem(depositBalanceLi[5], "depositBalance");
					} else if ("<%= depositBalances[i] %>" === "1억원 이상") {
						selectItem(depositBalanceLi[6], "depositBalance");
					} else {
						const inputs = depositBalanceLi[1].querySelectorAll('input');
						
						inputs[0].value = "<%= depositBalances[0] %>"; 
						<% if (depositBalances.length >= 2) { %>
						inputs[1].value = "<%= depositBalances[1] %>";
						<% } %>
	
						selectItem(depositBalanceLi[1], "depositBalance");
						toggleDirectInput(depositBalanceLi[1], true);
					}
				<%
				}
			}
		}
	}
	%>
	</script>
</body>
</html>
