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
							<option value="신용대출">신용 대출</option>
							<option value="담보대출">담보 대출</option>
						</select>
					</div>
					<div id="loanProductJobRow" class="innerInformationRow">
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
					<div id="loanProductCollateralRow" class="innerInformationRow display">
						<div class="innerInformationRowTitle">담보</div>
						<ul id="loanProductCollateral">
							<li><input type="checkBox" value="" id="jobAll">전체</li>
							<li><input type="checkBox" value="예적금" id="jobEmployee">예적금</li>
							<li><input type="checkBox" value="주택" id="jobGovernment">주택</li>
							<li><input type="checkBox" value="전세자금" id="jobMilitary">전세자금</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">기간</div>
						 <ul id="loanProductPeriod" class="directInputUl">
							<li><input type="checkbox" value="" id="periodAll">전체</li>
							<li id="directInput">
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
							<li id="directInput">
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
							<li id="directInput">
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
						<input id="loanProductSearchInput" name="loanName" class="innerSearchInput"></input>
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
				<div class="innerSubTitle"><h2>검색 결과</h2></div>
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
					<!-- 받아온 유저 정보를 테이블로 나타내는 코드 -->
					<%
					LoanUtil loanUtil =  new LoanUtil();
					List<LoanProductDTO> findLoanList = (List<LoanProductDTO>) request.getAttribute("loanProductList");
	
					if (findLoanList != null && !findLoanList.isEmpty()) {
					    for (LoanProductDTO loanProduct : findLoanList) {
					      %>
					      <tr>
					        <td class="loanProductId"><%= loanProduct.getLoanId() %></td>
					        <td><%= loanProduct.getLoanType() %></td>
					        <td><%= loanProduct.getLoanName() %></td>
					        <td><%= loanProduct.getCollateral() %>
					        <td><%= loanProduct.getJob() %></td>
					        <td><%= loanProduct.getIncome() %></td>
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
			</div>
		</div>
	</main>
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js "></script>
	<script>
		generateMenu('loan', 'loanProductList');
		function applySelectedData() {
		    var inputElement = document.getElementById("loanProductSearchInput");
		    var inputValue = inputElement.value;
		    var sessionValue = '<%= searchResults %>';
		    
		    console.log(sessionValue);
		    // 여기서 세션 값을 사용하여 원하는 동작 수행
		}
	</script>
	<script src="${pageContext.request.contextPath}/js/loan/loanProductList.js"></script>
</body>
</html>
