<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>상품 가입</title>
<script src="../components/aside/aside.js "></script>
<script src="${pageContext.request.contextPath}/components/aside/aside.js "></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/inputTable/inputTable.css?ver=1">
<link rel="stylesheet" href="productRegistration.css" />
</head>
<body>
	<%@ include file="../../../components/header/header.jsp" %>
	<main>
		<%@ include file="../../../components/aside/aside.jsp" %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>상품 등록</h1></div>
			<form>
				<div class="innerSubTitle"><h2>상품 정보</h2></div>
				<table class="inputTable">
 					<tr>
						<th>상품명</th>
						<td><input id="productName" class="longInput"/></td>
						<th>대출 기간</th>
						<td><input id="loanPeriod" class="shortInput"/></td>
					</tr>
					<tr>
						<th>대출 구분</th>
						<td>
							<select id="loanType">
								<option value="creditLoan">신용 대출</option>
								<option value="collateralLoan">담보 대출</option>
							</select>
						</td>
						<th>담보 종류</th>
						<td>
							<select id="collateralType">
								<option value="savingLoan">예적금</option>
								<option value="houseLoan">주택</option>
								<option value="jeonseLoan">전세</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>최대 한도</th>
						<td>
							&nbsp;최대
							<input id="loanLimit" class="shortInput" type="number" min="1" max="999" step="1" />
							천만 원
						</td>
						<th>이자</th>
						<td>
							<select id="interestRate">
								<option value="compound">복리</option>
								<option value="interest">단리</option>
							</select>
							&nbsp;&nbsp;최대
							<input id="interestRateLimit" class="shortInput" type="number" min="0" max="10" step="0.1" />
							%
						</td>
					</tr>
					<tr>
						<th>대출 목적</th>
						<td>
							<select id="loanPerpose">
								<option>??</option>
								<option>??</option>
							</select>
						</td>
						<th>적정 위험도</th>
						<td>
							<input id="adequateRisk" class="shortInput" type="number" min="0" max="100" step="1" />
							점
						</td>
					</tr>
				</table>
				<div class="innerButtonContainer">
					<button type="button">검색</button>
				</div>
  			</form>
        </div>
    </main>
    <script>
		generateMenu('loan', 'productRegistration');		
	</script>
</body>
</html>
