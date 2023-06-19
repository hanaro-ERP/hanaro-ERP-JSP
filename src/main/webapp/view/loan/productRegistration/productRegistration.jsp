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
			<form action="${pageContext.request.contextPath}/loanRegistration" method="post" onsubmit="return validateForm()">
				<div class="innerSubTitle"><h2>상품 정보</h2></div>
				<table class="inputTable">
 					<tr>
						<th>상품명</th>
						<td>
							<input name="productName" class="longInput"/>
						</td>
						<th>대출 기간</th>
						<td>
							<input type="number" name="loanPeriod" class="shortInput"/>
							개월
						</td>
					</tr>
					<tr>
						<th>대출 구분</th>
						<td>
							<select name="loanType" class="shortSelect">
								<option value="신용대출">신용 대출</option>
								<option value="담보대출">담보 대출</option>
							</select>
						</td>
						<th>담보 종류</th>
						<td>
							<select name="collateralType" class="shortSelect">
								<option value="예적금">예적금</option>
								<option value="주택">주택</option>
								<option value="전세">전세</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>최대 한도</th>
						<td>
							&nbsp;최대
							<input name="loanLimit" class="shortInput" type="number" min="1" max="999" step="1" />
							천만 원
						</td>
						<th>이자</th>
						<td>
							<select name="interestRate" class="shortSelect">
								<option value="복리">복리</option>
								<option value="단리">단리</option>
							</select>
							&nbsp;&nbsp;최대
							<input name="interestRateLimit" class="shortInput" type="number" min="0" max="10" step="0.1" />
							%
						</td>
					</tr>
					<tr>
						<th>대출 목적</th>
						<td>
							<select name="loanPerpose" class="shortSelect">
								<option>??</option>
								<option>??</option>
							</select>
						</td>
						<th>적정 위험도</th>
						<td>
							<input name="adequateRisk" class="shortInput" type="number" min="0" max="100" step="1" />
							점
						</td>
					</tr>
				</table>
				<div class="innerButtonContainer">
					<button type="submit">등록</button>
				</div>
  			</form>
        </div>
    </main>
    <script>
		generateMenu('loan', 'productRegistration');		
	</script>
	<script src="${pageContext.request.contextPath}/components/inputTable/inputTable.js "></script>
	<script src="${pageContext.request.contextPath}/view/loan/productRegistration/productRegistration.js"></script>
</body>
</html>
