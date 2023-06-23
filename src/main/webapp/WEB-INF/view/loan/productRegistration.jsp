<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>상품 가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/inputTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loan/productRegistration.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js "></script>
</head>
<body>
	<%@ include file="../../components/header.jsp" %>
	<main>
		<%@ include file="../../components/aside.jsp" %>
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
						<th id="collateralTypeContainer">직업</th>
						<td>
							<select id="loanTypeSelect" name="job" class="shortSelect">
								<option value="직장인">직장인</option>
								<option value="공무원">공무원</option>
								<option value="군인">군인</option>
								<option value="금융인">금융인</option>
								<option value="전문직">전문직</option>
								<option value="의사">의사</option>
								<option value="자영업">자영업</option>
								<option value="무직">무직</option>
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
							&nbsp;&nbsp;최소&nbsp;
							<input name="interestRate" class="shortInput" type="number" min="0" max="10" step="0.1"/>
							&nbsp;&nbsp;최대&nbsp;
							<input name="interestRate" class="shortInput" type="number" min="0" max="10" step="0.1"/>
							%
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
						<option value="직장인">직장인</option>
						<option value="공무원">공무원</option>
						<option value="군인">군인</option>
						<option value="금융인">금융인</option>
						<option value="전문직">전문직</option>
						<option value="의사">의사</option>
						<option value="자영업">자영업</option>
						<option value="무직">무직</option>
					`;
				} else if (selectedLoanType === '담보대출') {
					collateralTypeContainer.innerHTML = '담보 종류';
					collateralTypeSelect.setAttribute('name', 'collateralType');
					collateralTypeSelect.innerHTML = `
						<option value="예적금">예적금</option>
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
</body>
</html>
