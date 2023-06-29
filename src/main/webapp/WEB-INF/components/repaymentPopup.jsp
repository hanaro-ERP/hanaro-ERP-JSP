<%@page import="java.text.DecimalFormat"%>
<%@page import="DTO.LoanContractDTO"%>
<%@page import="DTO.LoanRepaymentDTO"%>
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
	<% 
	List<LoanRepaymentDTO> repaymentList = (List<LoanRepaymentDTO>) request.getAttribute("searchedRepaymentList");
	LoanContractDTO loanContractDTO = (LoanContractDTO) request.getAttribute("loanContractDTO");
	PaginationDTO paginationDTO = (PaginationDTO) request.getAttribute("paginationDTO"); 
	
	DecimalFormat wonFormat = new DecimalFormat("#,###원");
	
	int contractId = 0;
	String customerName =  null;
	String loanName = null;
	if (loanContractDTO != null) {
		contractId = loanContractDTO.getLoanContractId();
		customerName = loanContractDTO.getCustomerName();
		loanName = loanContractDTO.getLoanName();
	}
	
	%>
	<div class="popupTitleBox">
		<h1>상환 이력</h1>
		<h2><%= customerName %></h2>
	</div>
	<div id="historyTableBox">
		<table class="searchTable popUpTable repaymentTable" id="historyTable">
			<tr>
				<th>상환 ID</th>			
				<th>이력 ID</th>
				<th>거래일자</th>
				<th>상환 금액</th>
				<th>대출 잔액</th>
				<th>대리인</th>
			</tr>		
				<%
				if (repaymentList != null && !repaymentList.isEmpty()) {
					for (LoanRepaymentDTO dto : repaymentList) {
						%>
						<tr class="searchResultRow" id="<%= dto.getAccountId() %>">
							<td><%= dto.getLoanRepaymentId() %></td>
							<td><%= dto.getLoanContractId() %></td>
							<td><%= dto.getTradeDatetime() %></td>
							<td><%= wonFormat.format(dto.getTradeAmount())%></td>
							<td><%= wonFormat.format(dto.getBalance())%></td>
							<td>
							<% if (dto.isAgent()) { %>
								<%= "O" %>
							<% } else { %>
								<%= "X" %>
							<% } %>
							</td>
						</tr>
						<%
					}
				} else {
					%>
						<tr class="searchResultRow">
							<td colspan="6"> 상환 이력이 없습니다. </td>
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
				<a href=""><<</a>
				<a type="submit" name="page" value="<%= prevPage %>"><</a>
			<% } %>
			
			<% for (int i = startPage; i <= endPage; i++) { %>
				<% if (i == currentPage) { %>
					<a href="?id=<%= contractId %>&customer=<%= customerName %>&loan=<%= loanName %>&page=<%= i %>" class="activePage"><%= i %></a>
				<% } else { %>
					<a href="?id=<%= contractId %>&customer=<%= customerName %>&loan=<%= loanName %>&page=<%= i %>"><%= i %></a>
				<% } %>
			<% } %>
			
			<% if (currentPage < totalPages) { %>
				<a type="submit" name="page" value="<%= nextPage %>">></a>
				<a type="submit" name="page" value="<%= totalPages %>">>></a>
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