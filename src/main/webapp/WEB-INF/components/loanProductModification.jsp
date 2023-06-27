<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/inputTable.css?ver=1">
</head>
<script src="${pageContext.request.contextPath}/js/components/inputTable.js"></script>
<script src="${pageContext.request.contextPath}/js/loan/productRegistration.js"></script>
<body style="padding: 0 20px">	
	<%@ page import="util.LoanUtil" %>		
	<%@ page import="DTO.LoanProductDTO" %>	
	<% 
	LoanUtil loanUtil = new LoanUtil();
	LoanProductDTO loanProduct =(LoanProductDTO) request.getAttribute("loanProduct");
	
	int loanId = loanProduct.getLoanId();
	String loanType = loanProduct.getLoanType();
	String loanName = loanProduct.getLoanName();
	String job = loanUtil.convertJobCode(loanProduct.getJob());
	int income = (int) loanProduct.getIncome() / 10000;
	int minAmount = (int) loanProduct.getMinAmount() / 10000;
	int maxAmount = (int) loanProduct.getMaxAmount() / 10000;
	int minDuration = loanProduct.getMinDuration();
	int maxDuration = loanProduct.getMaxDuration();
	float minRate = loanProduct.getMinRate();
	float maxRate = loanProduct.getMaxRate();
	%>
		
	<div class="innerTitle"><h1>상품 수정</h1></div>
	<form id="productRegistrationForm" onsubmit="return registerForm()" action="${pageContext.request.contextPath}/loan/modification" method="post">
		<div class="innerSubTitle"><h2>상품 정보</h2></div>
		<table class="inputTable">
				<tr>
				<th>대출 구분</th>
				<td>
					<select name="loanType" class="shortSelect">
						<option value="신용대출" <%= loanType.equals("신용대출") ? "selected" : "" %>>신용 대출</option>
						<option value="담보대출" <%= loanType.equals("담보대출") ? "selected" : "" %>>담보 대출</option>
					</select>
				</td>
				<th>상품명</th>
				<td>
					<input name="productName" class="longInput" value="<%= loanName %>"/>
				</td>
			</tr>
			<tr>
				<th id="collateralTypeContainer">직업</th>
				<td>
				<select id="loanTypeSelect" name="jobCode" class="shortSelect">
					<option value="000" <%= job.equals("무관") ? "selected" : "" %>>무관</option>
					<option value="001" <%= job.equals("직장인") ? "selected" : "" %>>직장인</option>
					<option value="002" <%= job.equals("공무원") ? "selected" : "" %>>공무원</option>
					<option value="003" <%= job.equals("군인") ? "selected" : "" %>>군인</option>
					<option value="004" <%= job.equals("금융인") ? "selected" : "" %>>금융인</option>
					<option value="005" <%= job.equals("전문직") ? "selected" : "" %>>전문직</option>
					<option value="006" <%= job.equals("의사") ? "selected" : "" %>>의사</option>
					<option value="007" <%= job.equals("자영업") ? "selected" : "" %>>자영업</option>
					<option value="100" <%= job.equals("무직") ? "selected" : "" %>>무직</option>
				</select>
				</td>
				<th>연수입</th>
				<td>
					&nbsp;연
					<input type="number" name="loanIncome" class="shortInput" min="1" max="99999"  value="<%= income %>"/>
					만원
				</td>
			</tr>
			<tr>
				<th>대출 금액</th>
				<td>
					&nbsp;최소
					<input id="loanMinLimitId" name="loanMinLimit" class="shortInput" type="number" min="1" max="99999" step="1" value="<%= minAmount %>" />&nbsp;만원
					&nbsp;&nbsp;&nbsp;최대
					<input id="loanMaxLimitId" name="loanMaxLimit" class="shortInput" type="number" min="1" max="99999" step="1" value="<%= maxAmount %>" />&nbsp;만원
				</td>
				<th>대출 기간</th>
				<td>
					&nbsp;최소
					<input id="loanMinPeriodId" name="loanMinPeriod" class="shortInput" type="number" min="0" max="10" step="0.1" value="<%= minDuration %>"/>&nbsp;년
					&nbsp;&nbsp;&nbsp;최대&nbsp;
					<input id="loanMaxPeriodId" name="loanMaxPeriod" class="shortInput" type="number" min="0" max="10" step="0.1" value="<%= maxDuration %>"/>&nbsp;년
				</td>
			</tr>
			<tr>
				<th>대출 이율</th>
				<td>
					&nbsp;최소
					<input id="loanMinRateId" name="loanMinRate" class="shortInput" type="number" min="0" max="10" step="0.001" value="<%= minRate %>"/>&nbsp;%
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;최대
					<input id="loanMaxRateId" name="loanMaxRate" class="shortInput" type="number" min="0" max="10" step="0.001" value="<%= maxRate %>"/>&nbsp;%
				</td>
			</tr>
		</table>
		<div class="modifyButtonContainer">
			<button type="submit" name="id" value="<%= loanId %>">수정</button>
		</div>
	</form>
<script>
	document.addEventListener('keydown', function(event) {
		if (event.key === 'Escape') {
			window.close();
		}
	});
	
	window.history.pushState(null, null, window.location.href);
	window.addEventListener('popstate', function(event) {
		window.history.pushState(null, null, window.location.href);
	});
</script>
</body>
</html>