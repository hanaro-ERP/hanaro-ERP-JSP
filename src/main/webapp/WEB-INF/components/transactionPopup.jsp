<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchResultTable.css?ver=1">
</head>
<body>
	<%@ page import="java.util.List" %>
	<%@ page import="DTO.TransactionDTO" %>
	<%
		List<TransactionDTO> searchedTransactionList = (List<TransactionDTO>) request.getAttribute("searchedTransactionList");
	%>
	<div class="popupTitleBox">
		<h1>입출금 내역</h1>
		<h2><%= searchedTransactionList.get(0).getCustomerName() %></h2>
		<h2><%= searchedTransactionList.get(0).getAccountNumber() %></h2>
		</div>
	<div id="historyTableBox">
		<table class="searchTable popUpTable" id="historyTable">
			<tr>
				<th>계좌일자</th>
				<th>거래유형</th>
				<th>입금자명</th>
				<th>거래처</th>
				<th>거래 금액</th>
				<th>계좌 잔액</th>
			</tr>
			<!-- 받아온 계좌 정보를 테이블로 나타내는 코드 -->
			<%
			if (searchedTransactionList != null && !searchedTransactionList.isEmpty()) {
				for (TransactionDTO transaction : searchedTransactionList) {
					%>
					<tr class="searchResultRow" id="<%= transaction.getAccountId() %>">
						<td><%= transaction.getStringTransactionDate() %></td>
						<td><%= transaction.getTransactionType() %></td>
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
</body>
</html>