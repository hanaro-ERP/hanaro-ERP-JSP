<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchResultTable.css?ver=1">
</head>
<body style="padding: 10px 20px">
	<%@ page import="java.util.List" %>
	<%@ page import="DTO.TransactionDTO" %>
	<%@ page import="DTO.PaginationDTO" %>
	<%@page import="java.text.DecimalFormat"%>
	<%
	List<TransactionDTO> searchedTransactionList = (List<TransactionDTO>) request.getAttribute("searchedTransactionList");
	PaginationDTO paginationDTO = (PaginationDTO) request.getAttribute("paginationDTO");
	
	int id = 0;
	String name = null;
	String account = null;
	if (searchedTransactionList != null && searchedTransactionList.size() > 0) {
		id = searchedTransactionList.get(0).getAccountId();
		name = searchedTransactionList.get(0).getCustomerName();
		account = searchedTransactionList.get(0).getAccountNumber();
	}
	
	DecimalFormat wonFormat = new DecimalFormat("#,###원");
	%>
	<div class="popupTitleBox">
		<h1>입출금 내역</h1>
		<h2><%= searchedTransactionList != null && searchedTransactionList.size() > 0 ? searchedTransactionList.get(0).getCustomerName() : ""%></h2>
		<h2><%= searchedTransactionList != null && searchedTransactionList.size() > 0 ? searchedTransactionList.get(0).getAccountNumber() : ""%></h2>
	</div>
	<div id="historyTableBox">
		<table class="searchTable popUpTable transactionTable" id="historyTable">
			<tr>
				<th>계좌일자</th>
				<th>거래유형</th>
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
						<td><%= transaction.getTransactionLocation() %></td>
						<td><%= wonFormat.format(transaction.getTransactionAmount()) %></td>
						<td><%= wonFormat.format(transaction.getBalance()) %></td>
					</tr>
					<%
				}
			} else {
				%>
				<tr class="searchResultRow">
					<td colspan="6"> 거래 이력이 없습니다. </td>
				</tr>
				<%
			}
			%>		
		</table>
		<%
		// customerSearchDTO에서 page 값과 count 변수 추출
		int count = (paginationDTO != null && paginationDTO.getCount() != 0) ? paginationDTO.getCount() : 0;
		int pages = (paginationDTO != null && paginationDTO.getPage() != 0) ? paginationDTO.getPage() : 1;

		// 페이징 처리 로직
		int pageSize = 10; // 한 페이지에 표시할 레코드 수
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
		<div class="pagination">
			<% if (currentPage > 1) { %>
				<a href="?id=<%= id %>&name=<%= name %>&number=<%= account %>&page=<%= 1 %>"><<</a>
				<a href="?id=<%= id %>&name=<%= name %>&number=<%= account %>&page=<%= prevPage %>"><</a>
			<% } %>
			
			<% for (int i = startPage; i <= endPage; i++) { %>
				<% if (i == currentPage) { %>
					<a href="?id=<%= id %>&name=<%= name %>&number=<%= account %>&page=<%= i %>" class="activePage"><%= i %></a>
				<% } else { %>
					<a href="?id=<%= id %>&name=<%= name %>&number=<%= account %>&page=<%= i %>"><%= i %></a>
				<% } %>
			<% } %>
			
			<% if (currentPage < totalPages) { %>
				<a href="?id=<%= id %>&name=<%= name %>&number=<%= account %>&page=<%= nextPage %>">></a>
				<a href="?id=<%= id %>&name=<%= name %>&number=<%= account %>&page=<%= totalPages %>">>></a>
			<% } %>
		</div>
	</div>
</body>
<script>
	document.addEventListener('keydown', function(event) {
		if (event.key === 'Escape') {
			window.close();
		}
	});
</script>
</html>