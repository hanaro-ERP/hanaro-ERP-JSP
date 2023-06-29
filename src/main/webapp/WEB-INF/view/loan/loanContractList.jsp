<%@page import="DTO.LoanContractDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Contract</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loan/loanContractList.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js "></script>
</head>
<body>
	<%@ include file="../../components/header.jsp"%>
	<%@ page import="java.util.List"%>
	<%@ page import="DTO.LoanContractDTO"%>	
	<%@ page import="DTO.LoanRepaymentDTO" %>
	
	<main>
		<%@ include file="../../components/aside.jsp"%>
		<%
		List<LoanContractDTO> loanContracts = (List<LoanContractDTO>) request.getAttribute("loanContracts");
		LoanContractDTO loanContractDTO = (LoanContractDTO) request.getAttribute("searchInputValue");
		
		String loanName = null;
		String loanType = null;
		String customerName = null;
		String employeeName = null;
		String[] balanceList = null;
		int latePaymentPeriod = -2;
		
		if (loanContractDTO != null) {
			loanName = loanContractDTO.getLoanName();
			loanType = loanContractDTO.getLoanType();
			customerName = loanContractDTO.getCustomerName();
			employeeName = loanContractDTO.getEmployeeName();
			balanceList = loanContractDTO.getBalanceList();
			latePaymentPeriod = loanContractDTO.getLatePaymentPeriod();
		}
		%>
		<div class="innerContainer">
			<div class="innerTitle">
				<h1>여신 이력</h1>
			</div>
			<form action="${pageContext.request.contextPath}/loanContracts/contractList" method="post">
				<div class="innerSubTitle">
					<h2>여신 정보</h2>
				</div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">상품 이름</div>
						<input name="loanName" class="innerSearchInput"
							value="<%= loanName == null ? "" : loanName %>"></input>
						<div class="innerInformationRowTitle">대출 구분</div>
						<select id="loanType" name="loanType" class="innerSelectBox">
							<option value="" <%= loanType != null && loanType.equals("") ? "selected" : "" %>>-</option>
							<option value="신용대출" <%= loanType != null && loanType.equals("신용대출") ? "selected" : "" %>>신용 대출</option>
							<option value="담보대출" <%= loanType != null && loanType.equals("담보대출") ? "selected" : "" %>>담보 대출</option>
						</select>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">고객 이름</div>
						<input name="customerName" class="innerSearchInput3"
							value="<%= customerName == null ? "" : customerName %>"></input>
						<div class="innerInformationRowTitle">담당 직원</div>
						<input name="employeeName" class="innerSearchInput3"
							value="<%= employeeName == null ? "" : employeeName %>"></input>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">대출일</div>
						<ul class="loanIssueDate" id="loanContractStartDate">
							<li><input type="checkbox" value="전체" id="issueDateAll" name="loanContractStartDate">전체</li>
							<li class="directInput">
								<p>직접 입력</p>
								<select name="loanContractStartDate" class="yearSelect" disabled="true"></select> 
								<select name="loanContractStartDate" class="monthSelect" disabled="true"></select>
								<select name="loanContractStartDate" class="daySelect" disabled="true"></select>
							</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">만기일</div>
						<ul class="loanMaturityDate" id="loanContractEndDate">
							<li><input type="checkbox" value="전체" id="maturityDateAll" name="loanContractEndDate">전체</li>
							<li class="directInput">
								<p>직접 입력</p> 
								<select name="loanContractEndDate" class="yearSelect" disabled="true"></select> 
								<select name="loanContractEndDate" class="monthSelect" disabled="true"></select>
								<select name="loanContractEndDate" class="daySelect" disabled="true"></select>
							</li>
						</ul>
					</div>
					<div class="innerInformationRow" id="balance">
						<div class="innerInformationRowTitle">대출 잔액</div>
						<ul id="balanceList">
							<li id="balanceAllLi"><input type="checkbox" value="전체"
								id="balanceAll">전체</li>
							<li class="directInput">
								<p>직접 입력</p> <input id="directBalanceInput1" name="balanceList"
								class="directInputValue" disabled="true"> &nbsp;만원
								이상&nbsp;&nbsp; <input id="directBalanceInput2"
								name="balanceList" class="directInputValue" disabled="true">
								&nbsp;만원 이하&nbsp;
							</li>
							<li id="price2000Li"><input type="checkbox" value="~2천만원" id="price2000">~2천만원</li>
							<li id="price3000Li"><input type="checkbox" value="~3천만원" id="price3000">~3천만원</li>
							<li id="price5000Li"><input type="checkbox" value="~5천만원" id="price5000">~5천만원</li>
							<li id="price10000Li"><input type="checkbox" value="~1억원" id="price10000">~1억원</li>
							<li id="price5000Li"><input type="checkbox" value="~5억원" id="price50000">~5억원</li>
							<li id="price10000Li"><input type="checkbox" value="~10억원" id="price100000">~10억원</li>
							<li id="price10000PlusLi"><input type="checkbox" value="10억원 이상" id="price100000plus">10억원 이상</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">연체</div>
						<ul id="latePayment">
							<li id="periodAllLi"><input type="checkbox" value="전체"
								id="periodAll">전체</li>
							<li id="periodNoneLi"><input type="checkbox" value="없음"
								id="periodNone">없음</li>
							<li id="period6Li"><input type="checkbox" value="~6개월"
								id="period6">~6개월</li>
							<li id="period12Li"><input type="checkbox" value="~1년"
								id="period12">~1년</li>
							<li id="period36Li"><input type="checkbox" value="~3년"
								id="period36">~3년</li>
							<li id="period60Li"><input type="checkbox" value="~5년"
								id="period60">~5년</li>
							<li id="period60plusLi"><input type="checkbox" value="5년 이상"
								id="period60plus">5년이상</li>
						</ul>
					</div>
				</div>
				<div class="innerButtonContainer">
					<button id="loanContractSearchButton" type="submit">검색</button>
				</div>
				<div class="searchTitle">
					<h1>검색 결과</h1>
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
						<th>연체 금액</th>
						<th>이자율</th>
					</tr>
					<%
					if (loanContracts != null && !loanContracts.isEmpty()) {
						for (LoanContractDTO dto : loanContracts) {
						%>
						<tr class="searchResultRow" id="<%=dto.getLoanContractId()%>">
							<td class="loanContractId"><%=dto.getLoanContractId()%></td>
							<td><%=dto.getLoanType()%></td>
							<td><%=dto.getLoanName()%></td>
							<td><%=dto.getEmployeeName()%></td>
							<td><%=dto.getCustomerName()%></td>
							<td><%=dto.getGuarantorName()%></td>
							<td><%=dto.getStartDate()%></td>
							<td><%=dto.getMaturityDateString()%></td>
							<td><%=dto.getBalanceString()%></td>
							<td><%=dto.getPaymentMethod()%></td>
							<td><%=dto.getDelinquentAmountString()%></td>
							<td><%=dto.getInterestRate()%></td>
						</tr>
						<%
						}
					}
					%>
				</table>
				<%
				// customerSearchDTO에서 page 값과 count 변수 추출
				int count = (loanContractDTO != null && loanContractDTO.getCount() != 0) ? loanContractDTO.getCount() : 0;
				int pages = (loanContractDTO != null && loanContractDTO.getPage() != 0) ? loanContractDTO.getPage() : 1;

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
				<div class="popupBox display">
					<svg class="popupExitButton" width="14" height="15"
						viewBox="0 0 14 15" fill="none" xmlns="http://www.w3.org/2000/svg">
						<path
							d="M14 1.91L12.59 0.5L7 6.09L1.41 0.5L0 1.91L5.59 7.5L0 13.09L1.41 14.5L7 8.91L12.59 14.5L14 13.09L8.41 7.5L14 1.91Z"
							fill="#323232" />
						</svg>
					<div class="popupTitleBox">
						<h1>상환 이력</h1>
					</div>
					<div id="historyTableBox">
						<table class="searchTable" id="historyTable">
							<tr>						
								<th>여신 ID</th>
								<th>거래일자</th>
								<th>계좌번호</th>
								<th>고객 이름</th>
								<th>담당자</th>
								<th>상환 금액</th>
								<th>대출 잔액</th>
								<th>대리인</th>
							</tr>				
								<%
								List<LoanRepaymentDTO> searchedRepaymentList = (List<LoanRepaymentDTO>) request.getAttribute("searchedRepaymentList");
				
								if (searchedRepaymentList != null && !searchedRepaymentList.isEmpty()) {
									for (LoanRepaymentDTO dto : searchedRepaymentList) {
										%>
										<tr class="searchResultRow" id="<%= dto.getAccountId() %>">
											<td><%= dto.getLoanContractId() %></td>
											<td><%= dto.getTradeDatetime() %></td>
											<td><%= dto.getAccountNumber() %></td>
											<td><%= dto.getCustomerName() %></td>
											<td><%= dto.getEmployeeName() %></td>
											<td><%= dto.getTradeAmountString()%></td>
											<td><%= dto.getBalanceString()%></td>
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
								}
								%>				
						</table>
					</div>
				</div>
			</form>
		</div>
	</main>
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js"></script>
	<script src="${pageContext.request.contextPath}/js/loan/loanContractList.js"></script>
	<script>
		generateMenu('loan', 'loanContract');
		let showRepaymentList = "<%= request.getAttribute("showRepaymentList") %>";
		if (showRepaymentList == "showRepaymentList") {
			const popupBox = document.querySelector(".popupBox");
			popupBox.classList.remove("display");
		}
		
		const balanceList = document.getElementById('balanceList');
		const balanceListLis = balanceList.querySelectorAll('li');
		selectItem(balanceListLis[0], "balanceList");
		
		<%
		if (balanceList != null) { %>
			console.log("<%= balanceList[0] %>");
			unselectItem(balanceListLis[0]);
			if ("<%= balanceList[0] %>" === "전체") {
				selectItem(balanceListLis[0], "balanceList");
			} else if ("<%= balanceList[0] %>" === "~2천만원") {
				selectItem(balanceListLis[2], "balanceList");
			} else if ("<%= balanceList[0] %>" === "~3천만원") {
				selectItem(balanceListLis[3], "balanceList");
			} else if ("<%= balanceList[0] %>" === "~5천만원") {
				selectItem(balanceListLis[4], "balanceList");
			} else if ("<%= balanceList[0] %>" === "~1억원") {
				selectItem(balanceListLis[5], "balanceList");
			} else if ("<%= balanceList[0] %>" === "~5억원") {
				selectItem(balanceListLis[6], "balanceList");
			} else if ("<%= balanceList[0] %>" === "~10억원") {
				selectItem(balanceListLis[7], "balanceList");
			} else if ("<%= balanceList[0] %>" === "10억원 이상") {
				selectItem(balanceListLis[8], "balanceList");
			} else {
				const inputs = balanceListLis[1].querySelectorAll('input');
				inputs[0].value = "<%= balanceList[0] %>"; 
				<% if (balanceList.length >= 2) { %>
				inputs[1].value = "<%= balanceList[1] %>";
				<% } %>
				
				selectItem(balanceListLis[1], "balanceList");
				toggleDirectInput(balanceListLis[1], true);
			}
			<%
		}
		%>
		
		const latePayment = document.getElementById('latePayment');
		const latePaymentLis = latePayment.querySelectorAll('li');
		selectItem(latePaymentLis[0], "latePayment");
		console.log(<%= latePaymentPeriod %>);
		<%
		if (latePaymentPeriod != -2) {
			%>
			unselectItem(latePaymentLis[0]);
			if ("<%= latePaymentPeriod %>" == -1) {
				selectItem(latePaymentLis[0], "latePayment");
			} else if ("<%= latePaymentPeriod %>" == 0) {
				selectItem(latePaymentLis[1], "latePayment");
			} else if ("<%= latePaymentPeriod %>" == 180) {
				selectItem(latePaymentLis[2], "latePayment");
			} else if ("<%= latePaymentPeriod %>" == 365) {
				selectItem(latePaymentLis[3], "latePayment");
			} else if ("<%= latePaymentPeriod %>" == 365 * 3) {
				selectItem(latePaymentLis[4], "latePayment");
			} else if ("<%= latePaymentPeriod %>" == 365 * 5) {
				selectItem(latePaymentLis[5], "latePayment");
			} else if ("<%= latePaymentPeriod %>" == 365 * 5 + 1) {
				selectItem(latePaymentLis[6], "latePayment");
			}
			<%
		}
		%>
		
	</script>

</body>
</html>
