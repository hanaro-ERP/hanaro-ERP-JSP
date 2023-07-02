<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DTO.LoanContractDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하나로여신관리시스템 - 여신 이력</title>
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
		String[] startDates = null;
		String[] endDates = null;
		
		if (loanContractDTO != null) {
			loanName = loanContractDTO.getLoanName();
			loanType = loanContractDTO.getLoanType();
			customerName = loanContractDTO.getCustomerName();
			employeeName = loanContractDTO.getEmployeeName();
			balanceList = loanContractDTO.getBalanceList();
			latePaymentPeriod = loanContractDTO.getLatePaymentPeriod();
			startDates = loanContractDTO.getStartDateString();
			endDates = loanContractDTO.getMuturityDateList();
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
							<li id="startDateAllLi">
								<input type="checkbox" value="전체" id="issueDateAll" name="loanContractStartDate">전체
							</li>
							<li id="startDateInputLi" class="directInput">
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
							<li id="endDateAllLi"><input type="checkbox" value="전체" id="maturityDateAll" name="loanContractEndDate">전체</li>
							<li id="endDateInputLi" class="directInput">
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
				</div>
				<div class="scrollTo"></div>
				<div class="innerButtonContainer">
					<button id="loanContractSearchButton" type="submit">검색</button>
				</div>
			<div class="searchTitle"><h1>검색 결과</h1><p><%= (loanContractDTO != null && loanContractDTO.getCount() != 0) ? "총 " + loanContractDTO.getCount() + "개의 검색결과가 있습니다." : "" %></p></div>
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
							Date date = new Date(dto.getStartDate().getTime());
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							String dateString = dateFormat.format(date);
						%>
							<tr class="searchResultRow" id="<%=dto.getLoanContractId()%>">
								<td class="loanContractId"><%=dto.getLoanContractId()%></td>
								<td><%=dto.getLoanType()%></td>
								<td class="loanName"><%=dto.getLoanName()%></td>
								<td><%=dto.getEmployeeName()%></td>
								<td class="customerName"><%=dto.getCustomerName()%></td>
								<td><%= dto.getGuarantorName() != null ? dto.getGuarantorName() : "없음" %></td>
								<td><%= dateString %></td>
								<td><%=dto.getMaturityDateString()%></td>
								<td><%=dto.getBalanceString()%></td>
								<td><%=dto.getPaymentMethod()%></td>
								<td><%=dto.getDelinquentAmountString()%></td>
								<td><%=dto.getInterestRate()%>%</td>
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
			</form>
		</div>
		<button class="upToButton" onclick="scrollToTop()">
			<svg width="30" height="20" viewBox="0 0 12 10" fill="none" xmlns="http://www.w3.org/2000/svg">
				<path d="M6 0L0 6L1.41 7.41L6 2.83L10.59 7.41L12 6L6 0Z" fill="#323232"/>
			</svg>
		</button>
	</main>
	<script src="${pageContext.request.contextPath}/js/components/searchResultTable.js"></script>
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js"></script>
	<script src="${pageContext.request.contextPath}/js/loan/loanContractList.js"></script>
	<script>
		<% 
		String isCreated = request.getQueryString();
	
		if (isCreated != null) {
			if (isCreated.equals("mod=1")) {
				%>
				alert("상품 가입에 성공했습니다.")
				window.location.href = "/navigation/loanContract";
				<%
			} else if (isCreated.equals("mod=-1")) {
				%>
				alert("상품 가입에 실패했습니다.\n해당 고객에 대한 계좌가 있는지 확인해주세요.")
				window.location.href = "/navigation/loanContract";
				<%
			}
		}
		%>
	
		generateMenu('loan', 'loanContract');
		
		<%
		if (loanContractDTO != null) {
			%>
			scrollToBottom();
			<%
		}
		%>
		
		let showRepaymentList = "<%= request.getAttribute("showRepaymentList") %>";
		if (showRepaymentList == "showRepaymentList") {
			const popupBox = document.querySelector(".popupBox");
			popupBox.classList.remove("display");
		}
		
		<%
		if (startDates != null) {
			if (!startDates[0].equals("전체")) {
				%>
				const startDateAllLi = document.getElementById('startDateAllLi')
		    	const startDateInputLi = document.getElementById('startDateInputLi');
				const inputs = startDateInputLi.querySelectorAll('select');
				
				inputs[0].value = "<%= startDates[0] %>"; 
				inputs[1].value = "<%= startDates.length > 1 && startDates[1] != null ? startDates[1] : "" %>" ;
				setDaySelect(false, inputs[0].value, inputs[1].value)
				inputs[2].value = "<%= startDates.length > 2 && startDates[2] != null ? startDates[2] : "" %>" ;
				
				unselectItem(startDateAllLi);
				selectItem(startDateInputLi, "loanIssueDate");
				toggleDirectInput(startDateInputLi, true);
		
				<%
			} else {
				%>
				selectItem(startDateAllLi, "loanIssueDate");
				<%
			}
		}
		%>

		<%
		if (endDates != null) {
			if (!endDates[0].equals("전체")) {
				%>
				const endDateAllLi = document.getElementById('endDateAllLi')
		    	const endDateInputLi = document.getElementById('endDateInputLi');
				const inputs = endDateInputLi.querySelectorAll('select');
				
				inputs[0].value = "<%= endDates[0] %>"; 
				inputs[1].value = "<%= endDates.length > 1 && endDates[1] != null ? endDates[1] : "" %>" ;
				setDaySelect(false, inputs[0].value, inputs[1].value)
				inputs[2].value = "<%= endDates.length > 2 && endDates[2] != null ? endDates[2] : "" %>" ;
				
				unselectItem(endDateAllLi);
				selectItem(endDateInputLi, "loanMaturityDate");
				toggleDirectInput(endDateInputLi, true);
		
				<%
			} else {
				%>
				selectItem(endDateAllLi, "loanMaturityDate");
				<%
			}
		}
		%>
		
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
		
	</script>
</body>
</html>
