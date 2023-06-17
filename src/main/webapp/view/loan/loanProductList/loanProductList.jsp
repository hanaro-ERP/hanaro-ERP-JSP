<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchResultTable/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchLayout/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/components/aside/aside.js "></script>
</head>
<body>
	<%@ include file="../../../components/header/header.jsp" %>
	<main>
		<%@ include file="../../../components/aside/aside.jsp" %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>여신 상품 검색</h1></div>
			<form action="${pageContext.request.contextPath}/loanProductSearch" method="post">
				<div class="innerSubTitle"><h2>상품 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">대출 구분</div>
						<select id="loanType" class="innerSelectBox" name="loanType">
							<option value="신용대출">신용 대출</option>
							<option value="담보대출">담보 대출</option>
						</select>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">직업</div>
						<ul id="loanProductJob">
							<li><input type="checkBox" value="전체" id="jobAll">전체</li>
							<li><input type="checkBox" value="직장인" id="jobEmployee">직장인</li>
							<li><input type="checkBox" value="공무원" id="jobGovernment">공무원</li>
							<li><input type="checkBox" value="군인" id="jobMilitary">군인</li>
							<li><input type="checkBox" value="금융인" id="jobFinance">금융인</li>
							<li><input type="checkBox" value="전문직" id="jobProfessional">전문직</li>
							<li><input type="checkBox" value="의사" id="jobDoctor">의사</li>
							<li><input type="checkBox" value="자영업" id="jobSelfEmployed">자영업</li>
							<li><input type="checkBox" value="무직" id="jobUnemployed">무직</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">기간</div>
						 <ul id="loanProductPeriod" class="loanProductList">
							<li><input type="checkbox" value="전체" id="periodAll">전체</li>
							<li><input type="checkbox" value="1년" id="period1">1년</li>
							<li><input type="checkbox" value="2년" id="period2">2년</li>
							<li><input type="checkbox" value="3년" id="period3">3년</li>
							<li><input type="checkbox" value="5년" id="period5">5년</li>
							<li><input type="checkbox" value="10년" id="period10">10년</li>
							<li><input type="checkbox" value="10년 이상" id="periodOver10">10년 이상</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">연소득</div>
						<ul id="loanProductIncome" class="loanProductList">
							<li><input type="checkbox" value="전체" id="incomeAll">전체</li>
							<li><input type="checkbox" value="~2천만원" id="income2">~2천만원</li>
							<li><input type="checkbox" value="~3천만원" id="income3">~3천만원</li>
							<li><input type="checkbox" value="~5천만원" id="income5">~5천만원</li>
							<li><input type="checkbox" value="~1억원" id="income1">~1억원</li>
							<li><input type="checkbox" value="1억원 이상" id="incomeOver1">1억원 이상</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">대출 한도</div>
						<ul id="loanProductLimit" class="loanProductList">
							<li><input type="checkbox" value="전체" id="limitAll">전체</li>
							<li><input type="checkbox" value="5천만원" id="limit5">5천만원</li>
							<li><input type="checkbox" value="1억원" id="limit1">1억원</li>
							<li><input type="checkbox" value="5억원" id="limit5">5억원</li>
							<li><input type="checkbox" value="10억원" id="limit10">10억원</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">상품 이름</div>
						<input id="loanProductSearchInput" name="loanName"></input>
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
						<th>담보 종류</th>
						<th>연소득</th>
						<th>상품 이름</th>
						<th>대출 금액</th>
						<th>최대 기간</th>
						<th>대출 이율</th>
					</tr>
					<tr>
						<td>001</td>
						<td>신용 대출</td>
						<td>-</td>
						<td>1억 이상</td>
						<td>쉽고빠른상준론</td>
						<td>10억</td>
						<td>5년</td>
						<td>10%</td>
					</tr>
					<tr>
						<td>001</td>
						<td>신용 대출</td>
						<td>-</td>
						<td>1억 이상</td>
						<td>쉽고빠른상준론</td>
						<td>10억</td>
						<td>5년</td>
						<td>10%</td>
					</tr>
					<tr>
						<td>001</td>
						<td>신용 대출</td>
						<td>-</td>
						<td>1억 이상</td>
						<td>쉽고빠른상준론</td>
						<td>10억</td>
						<td>5년</td>
						<td>10%</td>
					</tr>
				</table>
			</div>
		</div>
	</main>
	<script src="${pageContext.request.contextPath}/components/searchLayout/searchLayout.js "></script>
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
	<script src="${pageContext.request.contextPath}/view/loan/loanProductList/loanProductList.js"></script>
</body>
</html>
