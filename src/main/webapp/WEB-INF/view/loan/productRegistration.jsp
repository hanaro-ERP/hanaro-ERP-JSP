<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>상품 가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/inputTable.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js "></script>
</head>
<body>
	<%@ include file="../../components/header.jsp" %>
	<main>
		<%@ include file="../../components/aside.jsp" %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>상품 등록</h1></div>
			<form id="productRegistrationForm" onsubmit="return registerForm()" action="${pageContext.request.contextPath}/loan/registration" method="post">
				<div class="innerSubTitle"><h2>상품 정보</h2></div>
				<table class="inputTable">
 					<tr>
						<th>대출 구분</th>
						<td>
							<select name="loanType" class="shortSelect">
								<option value="신용대출">신용 대출</option>
								<option value="담보대출">담보 대출</option>
							</select>
						</td>
						<th>상품명</th>
						<td>
							<input name="productName" class="longInput"/>
						</td>
					</tr>
					<tr>
						<th id="collateralTypeContainer">직업</th>
						<td>
							<select id="loanTypeSelect" name="jobCode" class="shortSelect">
								<option value="000">무관</option>
								<option value="001">직장인</option>
								<option value="002">공무원</option>
								<option value="003">군인</option>
								<option value="004">금융인</option>
								<option value="005">전문직</option>
								<option value="006">의사</option>
								<option value="007">자영업</option>
								<option value="100">무직</option>
							</select>
						</td>
						<th>연수입</th>
						<td>
							&nbsp;연
							<input type="number" name="loanIncome"  type="number" min="0" max="99999"  class="shortInput"/>
							천만원
						</td>
					</tr>
					<tr>
						<th>대출 금액</th>
						<td>
							&nbsp;최소
							<input id="loanMinLimitId" name="loanMinLimit" class="shortInput" type="number" min="0" max="99999" step="1" />&nbsp;천만원
							&nbsp;&nbsp;&nbsp;최대
							<input id="loanMaxLimitId" name="loanMaxLimit" class="shortInput" type="number" min="0" max="99999" step="1" />&nbsp;천만원
						</td>
						<th>대출 기간</th>
						<td>
							&nbsp;최소
							<input id="loanMinPeriodId" name="loanMinPeriod" class="shortInput" type="number" min="0" max="100" step="1"/>&nbsp;년
							&nbsp;&nbsp;&nbsp;최대&nbsp;
							<input id="loanMaxPeriodId" name="loanMaxPeriod" class="shortInput" type="number" min="0" max="100" step="1"/>&nbsp;년
						</td>
					</tr>
					<tr>
						<th>대출 이율</th>
						<td>
							&nbsp;최소
							<input id="loanMinRateId" name="loanMinRate" class="shortInput" type="number" min="0" max="10" step="0.001"/>&nbsp;%
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;최대
							<input id="loanMaxRateId" name="loanMaxRate" class="shortInput" type="number" min="0" max="10" step="0.001"/>&nbsp;%
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
		function toggleCollateralType() {
			const loanTypeSelect = document.querySelector('select[name="loanType"]');
			const collateralTypeSelect = document.querySelector('#loanTypeSelect');
			const collateralTypeContainer = document.querySelector('#collateralTypeContainer');

			loanTypeSelect.addEventListener('change', function() {
				const selectedLoanType = this.value;
				const collateralTypeOptions = collateralTypeSelect.querySelectorAll('option');
    	    
				if (selectedLoanType === '신용대출') {
					collateralTypeContainer.innerHTML = '직업';
					collateralTypeSelect.setAttribute('name', 'job');
					collateralTypeSelect.innerHTML = `
						<option value="000">무관</option>
						<option value="001">직장인</option>
						<option value="002">공무원</option>
						<option value="003">군인</option>
						<option value="004">금융인</option>
						<option value="005">전문직</option>
						<option value="006">의사</option>
						<option value="007">자영업</option>
						<option value="100">무직</option>
					`;
				} else if (selectedLoanType === '담보대출') {
					collateralTypeContainer.innerHTML = '담보 종류';
					collateralTypeSelect.setAttribute('name', 'collateralType');
					collateralTypeSelect.innerHTML = `
						<option value="예금">예금</option>
						<option value="적금">적금</option>
						<option value="주택">주택</option>
						<option value="전세자금">전세자금</option>
					`;
				}
			});
    	}

    	// 호출하여 함수 실행
    	toggleCollateralType();
		generateMenu('loan', 'productRegistration');
		
	</script>
	<script src="${pageContext.request.contextPath}/js/components/inputTable.js"></script>
	<script src="${pageContext.request.contextPath}/js/loan/productRegistration.js"></script>
	<script>
		<%
			Integer isLoanRegistered = (Integer) request.getAttribute("isLoanRegistered");
			if (isLoanRegistered != null) {
			    if (isLoanRegistered != -1) {
					%>
					alert("여신 상품이 성공적으로 등록되었습니다.");
					<%
				} else {
					%>
					alert("여신 상품이 등록에 실패하였습니다.");
					<%
				}
			}
		%>
	</script>
</body>
</html>
