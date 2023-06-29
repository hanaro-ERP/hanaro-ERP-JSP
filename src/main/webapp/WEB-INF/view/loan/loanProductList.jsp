<%@page import="util.LoanUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js "></script>
</head>
<body>
	<%@page import="java.util.Arrays"%>
	<%@ page import="java.util.List" %>
	<%@ page import="util.LoanUtil" %>
	<%@ page import="DTO.LoanProductDTO" %>
	<%@ page import="DTO.LoanSearchDTO" %>
	<%
		LoanUtil loanUtil =  new LoanUtil();
		List<LoanProductDTO> findLoanList = (List<LoanProductDTO>) request.getAttribute("loanProductList");
		LoanSearchDTO loanSearchDTO = (LoanSearchDTO) request.getAttribute("loanProductInput");
	%>
	<%@ include file="../../components/header.jsp" %>
	<main>
		<%@ include file="../../components/aside.jsp" %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>여신 상품 검색</h1></div>
			<form action="${pageContext.request.contextPath}/loanProduct/list" method="post">
				<div class="innerSubTitle"><h2>상품 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">대출 구분</div>
						<select id="loanType" class="innerSelectBox" name="loanType">
							<option value="신용대출" <% if (loanSearchDTO != null && "신용대출".equals(loanSearchDTO.getType())) { %>selected<% } %>>신용 대출</option>
							<option value="담보대출" <% if (loanSearchDTO != null && "담보대출".equals(loanSearchDTO.getType())) { %>selected<% } %>>담보 대출</option>
						</select>
					</div>
					<div id="loanProductJobRow" class="innerInformationRow <% if (loanSearchDTO != null && "담보대출".equals(loanSearchDTO.getType())) { %> display<% } %>">
						<div class="innerInformationRowTitle">직업</div>
						<ul id="loanProductJob">
							<li><input type="checkBox" value="" id="jobAll">전체</li>
							<li><input type="checkBox" value="000" id="jobAnything">무관</li>
							<li><input type="checkBox" value="001" id="jobEmployee">직장인</li>
							<li><input type="checkBox" value="002" id="jobGovernment">공무원</li>
							<li><input type="checkBox" value="003" id="jobMilitary">군인</li>
							<li><input type="checkBox" value="004" id="jobFinance">금융인</li>
							<li><input type="checkBox" value="005" id="jobProfessional">전문직</li>
							<li><input type="checkBox" value="006" id="jobDoctor">의사</li>
							<li><input type="checkBox" value="007" id="jobSelfEmployed">자영업</li>
							<li><input type="checkBox" value="100" id="jobUnemployed">무직</li>
						</ul>
					</div>
					<div id="loanProductCollateralRow" class="innerInformationRow <% if (loanSearchDTO == null || "신용대출".equals(loanSearchDTO.getType())) { %> display<% } %>">
						<div class="innerInformationRowTitle">담보</div>
						<ul id="loanProductCollateral">
							<li><input type="checkBox" value="" id="collateralAll">전체</li>
							<li><input type="checkBox" value="예금" id="collateralDeposit">예금</li>
							<li><input type="checkBox" value="적금" id="collateralDeposit">적금</li>
							<li><input type="checkBox" value="주택" id="collateralMortgage">주택</li>
							<li><input type="checkBox" value="전세자금" id="collateralJeonse">전세자금</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">기간</div>
						 <ul id="loanProductPeriod" class="directInputUl">
							<li><input type="checkbox" value="" id="periodAll">전체</li>
							<li id="directPeriodInputLi" class="directInput">
							    <p>직접 입력</p>
							    <input id="directPeriodInput" name="loanProductPeriod" class="directInputValue" disabled="true">&nbsp;년&nbsp;&nbsp;
							</li>
							<li><input type="checkbox" value="1" id="period1">1년</li>
							<li><input type="checkbox" value="2" id="period2">2년</li>
							<li><input type="checkbox" value="3" id="period3">3년</li>
							<li><input type="checkbox" value="5" id="period5">5년</li>
							<li><input type="checkbox" value="10" id="period10">10년</li>
							<li><input type="checkbox" value="over10" id="periodOver10">10년 이상</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">연소득</div>
						<ul id="loanProductIncome" class="directInputUl">
							<li><input type="checkbox" value="" id="incomeAll">전체</li>
							<li id="directPeriodInputLi" class="directInput">
							    <p>직접 입력</p>
							    <input id="directPeriodInput" name="loanProductIncome" class="directInputValue" disabled="true">&nbsp;천만원&nbsp;&nbsp;
							</li>
							<li><input type="checkbox" value="2" id="income2">~2천만원</li>
							<li><input type="checkbox" value="3" id="income3">~3천만원</li>
							<li><input type="checkbox" value="5" id="income5">~5천만원</li>
							<li><input type="checkbox" value="10" id="income10">~1억원</li>
							<li><input type="checkbox" value="50" id="income50">~5억원</li>
							<li><input type="checkbox" value="100" id="income100">~10억원</li>
							<li><input type="checkbox" value="over100" id="incomeOver100">10억원 이상</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">대출 한도</div>
						<ul id="loanProductLimit" class="directInputUl">
							<li><input type="checkbox" value="" id="limitAll">전체</li>
							<li id="directPeriodInputLi" class="directInput">
							    <p>직접 입력</p>
							    <input id="directPeriodInput" name="loanProductLimit" class="directInputValue" disabled="true">&nbsp;천만원&nbsp;&nbsp;
							</li>
							<li><input type="checkbox" value="2" id="limit2">~2천만원</li>
							<li><input type="checkbox" value="3" id="limit3">~3천만원</li>
							<li><input type="checkbox" value="5" id="limit5">~5천만원</li>
							<li><input type="checkbox" value="10" id="limit10">~1억원</li>
							<li><input type="checkbox" value="50" id="limit50">~5억원</li>
							<li><input type="checkbox" value="100" id="limit100">~10억원</li>
							<li><input type="checkbox" value="over100" id="limitOver100">10억원 이상</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">상품 이름</div>
						<input id="loanProductSearchInput" name="loanName" class="innerSearchInput" value="<%= loanSearchDTO == null || loanSearchDTO.getName() == null ? "" : loanSearchDTO.getName() %>"></input>
					</div>
				</div>
				<div class="innerButtonContainer">
					<button type="submit">검색</button>
				</div>
				<div class="searchTitle"><h1>검색 결과</h1><p><%= (loanSearchDTO != null && loanSearchDTO != null) ? "총 " + loanSearchDTO.getCount() + "개의 검색 결과가 있습니다." : "" %></p></div>
				<table class="searchTable" id="loanSearchTable">
					<tr>
						<th>상품 ID</th>
						<th>대출 구분</th>
						<th>상품 이름</th>
						<th>담보 종류</th>						
						<th>직업</th>
						<th>연소득</th>
						<th>최소 금액</th>
						<th>최대 금액</th>
						<th>최소 기간</th>
						<th>최대 기간</th>
						<th>최소 이율</th>
						<th>최대 이율</th>
					</tr>
					<%
						if (findLoanList != null && !findLoanList.isEmpty()) {
							for (LoanProductDTO loanProduct : findLoanList) {
								%>
								<tr>
									<td class="loanProductId"><%= loanProduct.getLoanId() %></td>
									<td><%= loanProduct.getLoanType() %></td>
									<td><%= loanProduct.getLoanName() %></td>
									<td><%= loanProduct.getCollateral() %>
									<td><%= loanUtil.convertJobCode(loanProduct.getJob()) %></td>
									<td><%= loanUtil.convertMoneyUnit(loanProduct.getIncome()) %></td>
									<td><%= loanUtil.convertMoneyUnit(loanProduct.getMinAmount()) %></td>
									<td><%= loanUtil.convertMoneyUnit(loanProduct.getMaxAmount()) %></td>
									<td><%= loanProduct.getMinDuration() %>년</td>
									<td><%= loanProduct.getMaxDuration() %>년</td>
									<td><%= loanProduct.getMinRate() %>%</td>
									<td><%= loanProduct.getMaxRate() %>%</td>
								</tr>
								<%
							}
						}
					%>
				</table>
				<%
				// customerSearchDTO에서 page 값과 count 변수 추출
				int count = (loanSearchDTO != null && loanSearchDTO.getCount() != 0) ? loanSearchDTO.getCount() : 0;
				int pages = (loanSearchDTO != null && loanSearchDTO != null) ? loanSearchDTO.getPage() : 1;

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
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js "></script>
	<script src="${pageContext.request.contextPath}/js/loan/loanProductList.js"></script>
	<%
	String type = null;
	String name = null;
	String[] jobs = null;
	String[] collaterals = null;
	String[] periods = null;
	String[] incomes = null;
	String[] limits = null;

	if (loanSearchDTO != null) {
		type = loanSearchDTO.getType();
		name = loanSearchDTO.getName();
		jobs = loanSearchDTO.getJobs();
		collaterals = loanSearchDTO.getCollaterals();
		periods = loanSearchDTO.getPeriods();
		incomes = loanSearchDTO.getIncomes();
		limits = loanSearchDTO.getLimits();
	}
	%>
	<script>
		generateMenu('loan', 'loanProductList');
		
		selectMultiItems("loanProductJob");
		selectMultiItems("loanProductCollateral");
		selectMultiItemsWithDirectInput("loanProductPeriod");
		selectOneItemWithDirectInput("loanProductIncome");
		selectOneItemWithDirectInput("loanProductLimit");
		
		const loanProductJob = document.getElementById('loanProductJob');
		const loanProductJobLis = loanProductJob.querySelectorAll('li');
		selectItem(loanProductJobLis[0], "loanProductJob");
		<%
		if (jobs != null) {
			for (int i = 0; i < jobs.length; i++) {
				if (!jobs[i].isEmpty()) { %>
					unselectItem(loanProductJobLis[0]);
					if ("<%= jobs[i] %>" === "000") {
						selectItem(loanProductJobLis[1], "loanProductJob");
					} else if ("<%= jobs[i] %>" === "001") {
						selectItem(loanProductJobLis[2], "loanProductJob");
					} else if ("<%= jobs[i] %>" === "002") {
						selectItem(loanProductJobLis[3], "loanProductJob");
					} else if ("<%= jobs[i] %>" === "003") {
						selectItem(loanProductJobLis[4], "loanProductJob");
					} else if ("<%= jobs[i] %>" === "004") {
						selectItem(loanProductJobLis[5], "loanProductJob");
					} else if ("<%= jobs[i] %>" === "005") {
						selectItem(loanProductJobLis[6], "loanProductJob");
					} else if ("<%= jobs[i] %>" === "006") {
						selectItem(loanProductJobLis[7], "loanProductJob");
					} else if ("<%= jobs[i] %>" === "007") {
						selectItem(loanProductJobLis[8], "loanProductJob");
					} else if ("<%= jobs[i] %>" === "100") {
						selectItem(loanProductJobLis[9], "loanProductJob");
					} 
				<%
				}
			}
		}
		%>

		const loanProductCollateral = document.getElementById('loanProductCollateral');
		const loanProductCollateralLis = loanProductCollateral.querySelectorAll('li');
		selectItem(loanProductCollateralLis[0], "loanProductCollateral");
		<%
		if (collaterals != null) {
			for (int i = 0; i < collaterals.length; i++) {
				if (!collaterals[i].isEmpty()) { %>
					unselectItem(loanProductCollateralLis[0]);
					if ("<%= collaterals[i] %>" === "예금") {
						selectItem(loanProductCollateralLis[1], "loanProductCollateral");
					} else if ("<%= collaterals[i] %>" === "적금") {
						selectItem(loanProductCollateralLis[2], "loanProductCollateral");
					} else if ("<%= collaterals[i] %>" === "주택") {
						selectItem(loanProductCollateralLis[3], "loanProductCollateral");
					} else if ("<%= collaterals[i] %>" === "전세자금") {
						selectItem(loanProductCollateralLis[4], "loanProductCollateral");
					}
				<%
				}
			}
		}
		%>
		
		const loanProductPeriod = document.getElementById('loanProductPeriod');
		const loanProductPeriodLis = loanProductPeriod.querySelectorAll('li');
		selectItem(loanProductPeriodLis[0], "loanProductPeriod");
		<%
		if (periods != null) {
			for (int i = 0; i < periods.length; i++) {
				if (!periods[i].isEmpty()) { %>
					unselectItem(loanProductPeriodLis[0]);
					if ("<%= periods[i] %>" === "1") {
						selectItem(loanProductPeriodLis[2], "loanProductPeriod");
					} else if ("<%= periods[i] %>" === "2") {
						selectItem(loanProductPeriodLis[3], "loanProductPeriod");
					} else if ("<%= periods[i] %>" === "3") {
						selectItem(loanProductPeriodLis[4], "loanProductPeriod");
					} else if ("<%= periods[i] %>" === "5") {
						selectItem(loanProductPeriodLis[5], "loanProductPeriod");
					} else if ("<%= periods[i] %>" === "10") {
						selectItem(loanProductPeriodLis[6], "loanProductPeriod");
					} else if ("<%= periods[i] %>" === "over") {
						selectItem(loanProductPeriodLis[7], "loanProductPeriod");
					} else {
						const inputs = loanProductPeriodLis[1].querySelector('input');
						inputs.value = "<%= periods[0] %>"; 
						
						selectItem(loanProductPeriodLis[1], "loanProductPeriod");
						toggleDirectInput(loanProductPeriodLis[1], true);
					}
				<%
				}
			}
		}
		%>
		
		const loanProductIncome = document.getElementById('loanProductIncome');
		const loanProductIncomeLis = loanProductIncome.querySelectorAll('li');
		selectItem(loanProductIncomeLis[0], "loanProductIncome");
		<%
		if (incomes != null) {
			for (int i = 0; i < incomes.length; i++) {
				if (!incomes[i].isEmpty()) { %>
					unselectItem(loanProductIncomeLis[0]);
					if ("<%= incomes[i] %>" === "2") {
						selectItem(loanProductIncomeLis[2], "loanProductIncome");
					} else if ("<%= incomes[i] %>" === "3") {
						selectItem(loanProductIncomeLis[3], "loanProductIncome");
					} else if ("<%= incomes[i] %>" === "5") {
						selectItem(loanProductIncomeLis[4], "loanProductIncome");
					} else if ("<%= incomes[i] %>" === "10") {
						selectItem(loanProductIncomeLis[5], "loanProductIncome");
					} else if ("<%= incomes[i] %>" === "50") {
						selectItem(loanProductIncomeLis[6], "loanProductIncome");
					} else if ("<%= incomes[i] %>" === "100") {
						selectItem(loanProductIncomeLis[7], "loanProductIncome");
					} else if ("<%= incomes[i] %>" === "over100") {
						selectItem(loanProductIncomeLis[8], "loanProductIncome");
					} else {
						const inputs = loanProductIncomeLis[1].querySelector('input');
						inputs.value = "<%= incomes[0] %>"; 
						
						selectItem(loanProductIncomeLis[1], "loanProductPeriod");
						toggleDirectInput(loanProductIncomeLis[1], true);
					}
				<%
				}
			}
		}
		%>
		
		const loanProductLimit = document.getElementById('loanProductLimit');
		const loanProductLimitLis = loanProductLimit.querySelectorAll('li');
		selectItem(loanProductLimitLis[0], "loanProductLimit");
		<%
		if (limits != null) {
			for (int i = 0; i < limits.length; i++) {
				if (!limits[i].isEmpty()) { %>
					unselectItem(loanProductLimitLis[0]);
					if ("<%= limits[i] %>" === "2") {
						selectItem(loanProductLimitLis[2], "loanProductLimit");
					} else if ("<%= limits[i] %>" === "3") {
						selectItem(loanProductLimitLis[3], "loanProductLimit");
					} else if ("<%= limits[i] %>" === "5") {
						selectItem(loanProductLimitLis[4], "loanProductLimit");
					} else if ("<%= limits[i] %>" === "10") {
						selectItem(loanProductLimitLis[5], "loanProductLimit");
					} else if ("<%= limits[i] %>" === "50") {
						selectItem(loanProductLimitLis[6], "loanProductLimit");
					} else if ("<%= limits[i] %>" === "100") {
						selectItem(loanProductLimitLis[7], "loanProductLimit");
					} else if ("<%= limits[i] %>" === "over100") {
						selectItem(loanProductLimitLis[8], "loanProductLimit");
					} else {
						const inputs = loanProductLimitLis[1].querySelector('input');
						inputs.value = "<%= limits[0] %>"; 
						
						selectItem(loanProductLimitLis[1], "loanProductLimit");
						toggleDirectInput(loanProductLimitLis[1], true);
					}
				<%
				}
			}
		}
		%>
	</script>
</body>
</html>
