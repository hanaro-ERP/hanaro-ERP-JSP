<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Contract</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/loan/loanContract/loanContractList.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchResultTable/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchLayout/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/components/aside/aside.js "></script>
</head>
<body>
	<%@ include file="../../../components/header/header.jsp" %>
	<main>
		<%@ include file="../../../components/aside/aside.jsp" %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>여신 이력</h1></div>
			<form action="${pageContext.request.contextPath}/loanContractList" method="post">
				<div class="innerSubTitle"><h2>여신 정보</h2></div>
				<div class="loanContractInformation">
					<div class="loanContractInformationRow">
						<div class="loanContractInformationRowTitle">상품 이름</div>
						<input class="loanContractSearchInput" ></input>
						<div class="loanContractInformationRowTitle">대출 구분</div>
						<select id="loanType">
							<option value="creditLoan">신용 대출</option>
							<option value="mortgageLoan">담보 대출</option>
						</select>
					</div>	
					<div class="loanContractInformationRow">
						<div class="loanContractInformationRowTitle">고객 이름</div>
						<input class="loanContractSearchInput" ></input>
						<div class="loanContractInformationRowTitle">담당 직원</div>
						<input class="loanContractSearchInput" ></input>
					</div>					
					<div class="loanContractInformationRow">
						<div class="loanContractInformationRowTitle">대출일</div>
						<ul class="loanContractDate" id="loanContractStartDate">
							<li>전체</li>
							<li class ="directInput">직접입력</li>
							<select class="yearSelect"> </select>
							<select class="monthSelect"> </select>
							<select class="daySelect"> </select>
						</ul>
					</div>
					<div class="loanContractInformationRow">
						<div class="loanContractInformationRowTitle">만기일</div>
						<ul class="loanContractDate" id="loanContractEndDate">
							<li>전체</li>
							<li class="directInput">직접입력
								
							</li>
							<select class="yearSelect"> </select>
							<select class="monthSelect"> </select>
							<select class="daySelect"> </select>
							
						</ul>
					</div>
					<div class="loanContractInformationRow" id="balance">
						<div class="loanContractInformationRowTitle">대출 잔액</div>
						<ul id="balanceList">
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
					<div class="loanContractInformationRow">
						<div class="loanContractInformationRowTitle">연체</div>
						<ul id="loanContractLimit">						
							<li><input type="checkbox" value="없음" />없음</li>
							<li><input type="checkbox" value="~6개월" />~6개월</li>
							<li><input type="checkbox" value="~1년" />~1년</li>
							<li><input type="checkbox" value="~1년" />~1년</li>
							<li><input type="checkbox" value="~3년" />~3년</li>
							<li><input type="checkbox" value="~5년" />~5년</li>
							<li><input type="checkbox" value="5년 이상" />5년 이상</li>
						</ul>
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
						<th>연체</th>
						<th>담보 가치</th>
					</tr>
					<tr>
						<td>001</td>
						<td>신용 대출</td>
						<td>징검다리론</td>
						<td>김철수</td>
						<td>이영수</td>
						<td>최영희</td>
						<td>2023-06-14</td>
						<td>2043-06-13</td>
						<td>10,000,000원</td>
						<td>원금 균등 분할 상환</td>
						<td>N</td>
						<td>4,000,000원</td>
					</tr>
					<tr>
						<td>001</td>
						<td>신용 대출</td>
						<td>징검다리론</td>
						<td>김철수</td>
						<td>이영수</td>
						<td>최영희</td>
						<td>2023-06-14</td>
						<td>2043-06-13</td>
						<td>10,000,000원</td>
						<td>원금 균등 분할 상환</td>
						<td>N</td>
						<td>4,000,000원</td>
					</tr>
				</table>
			</div>
		</div>
	</main>
	<script src="${pageContext.request.contextPath}/components/searchLayout/searchLayout.js "></script>
	<script>
		generateMenu('loan', 'loanContractList');
	</script>
	<script src="${pageContext.request.contextPath}/view/loan/loanContract/loanContractList.js "></script>
</body>
</html>
