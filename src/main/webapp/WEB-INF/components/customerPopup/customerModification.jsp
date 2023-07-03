<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 정보 수정</title>
<link rel="icon" href="${pageContext.request.contextPath}/public/images/favicon.svg">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/inputTable.css?ver=1">
</head>
<script src="${pageContext.request.contextPath}/js/components/inputTable.js"></script>
<script src="${pageContext.request.contextPath}/js/components/searchLayout.js"></script>
<body style="padding: 0 20px">	
	<%@ page import="util.LoanUtil" %>		
	<%@ page import="DTO.CustomerDTO" %>	
	<%@ page import="DTO.CustomerDTO" %>
	<%@ page import="DAO.EmployeeDAO" %>
	<%@ page import="DAO.BankDAO" %>
	<%
	String sessionName = (String)request.getSession().getAttribute("loginName");

	String isCreated = request.getQueryString();
	
	EmployeeDAO employeeDAO = new EmployeeDAO();
	BankDAO bankDAO = new BankDAO();
	
	int id = employeeDAO.getEmployeeIdByEmployeeName(sessionName);
	String bankName = bankDAO.getBankNameByBankId(id);

	LoanUtil loanUtil = new LoanUtil();
	CustomerDTO customer = (CustomerDTO) request.getAttribute("customer");
	
	int customerId = customer.getCustomerId();
	%>
		
	<div class="innerTitle"><h1>고객 정보 수정</h1></div>
	<form id="productRegistrationForm" onsubmit="return registerForm()" action="${pageContext.request.contextPath}/customer/modification" method="post">
		<div class="innerSubTitle"><h2>고객 정보</h2></div>
		<table class="inputTable">
				<tr>
				<th>이름</th>
				<td><input id="customerName" name="customerName" class="middleInput" value="<%= customer != null && customer.getCustomerName() != null ? customer.getCustomerName() : "" %>"/></td>
				<th>전화번호</th>
				<td><input placeholder="'-' 는 제외해주세요." id="phoneNumber" name="phoneNumber" class="middleInput" value="<%= customer != null && customer.getPhoneNumber() != null ? customer.getPhoneNumber() : "" %>"/></td>
			</tr>
			<tr>
				<th>거주지</th>
				<td>
					<select id="citySelect" name="citySelect" class="innerSelectBox customerCity" onchange="changeCounty(this.selectedIndex);" style="width: 150px";>
						<option value="">전체</option>
						<option value="서울특별시">서울특별시</option>
						<option value="부산광역시">부산광역시</option>
						<option value="대구광역시">대구광역시</option>
						<option value="인천광역시">인천광역시</option>
						<option value="광주광역시">광주광역시</option>
						<option value="대전광역시">대전광역시</option>
						<option value="울산광역시">울산광역시</option>
						<option value="경기도">경기도</option>
						<option value="강원도">강원도</option>
						<option value="충청북도">충청북도</option>
						<option value="충청남도">충청남도</option>
						<option value="전라북도">전라북도</option>
						<option value="전라남도">전라남도</option>
						<option value="경상북도">경상북도</option>
						<option value="경상남도">경상남도</option>
						<option value="제주도">제주도</option>
					</select>
					<select name="district" class="select" id="districtSelect" style="width: 150px">
						<option value="">-</option>
					</select>
				</td>
				<th>국가</th>
				<td>
					<select id="country" name="country" class="shortSelect">
						<option value="대한민국">대한민국</option>
						<option value="미국">미국</option>
						<option value="중국">중국</option>
						<option value="일본">일본</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>주민번호</th>
				<td>
					<div>
						<input id="userIdentification1" name="userIdentification1" class="identification2" maxlength="6" value="<%= customer != null ? customer.getIdentification().substring(0, 6) : "" %>"/>
							-
						<input type="password" id="userIdentification2" name="userIdentification2" class="identification2" maxlength="7" value="<%= customer != null ? customer.getIdentification().substring(7, 14) : "" %>"/>
						<button type="button" id="show1" onclick="showIdentification()">SHOW</button>
					</div>
				</td>
				<th>직업</th>
				<td>
					<select id="jobSelect" name="job" class="shortSelect">
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
			</tr>
			<tr>						
				<th>외부 신용 등급</th>
				<td>
					<select id="creditRank" name="creditRank" class="shortSelect">
						<option value="1등급">1</option>
						<option value="2등급">2</option>
						<option value="3등급">3</option>
						<option value="4등급">4</option>
						<option value="5등급">5</option>
					</select>
					등급
				</td>
				<th>고객 등급</th>
				<td>
					<select id="customerRank" name="customerRank" class="shortSelect">
						<option value="SILVER">SILVER</option>
						<option value="GOLD">GOLD</option>
						<option value="VIP">VIP</option>
						<option value="VVIP">VVIP</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>담당 직원</th>
				<td><%= sessionName %>
				<input type="hidden" id="employeeName" name="employeeName" value="<%= sessionName %>"/></td>
				<th class="office">주거래지점</th>
				<td><%= bankName %>
				<input type="hidden" id="bank" name="bank" class="middleInput" value="<%= bankName %>"/></td>
			</tr>
		</table>
		<div class="modifyButtonContainer">
			<button type="submit" name="id" value="<%= customerId %>">수정</button>
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
	
	const customerIdentificationInput1 = document.getElementById("userIdentification1");
	const customerIdentificationInput2 = document.getElementById("userIdentification2");

	var show1 = document.getElementById("show1");

	function showIdentification() {
		if(customerIdentificationInput2.type == "text") {
			customerIdentificationInput2.type = "password";
			show1.innerText = "SHOW";
		}
		else {
			customerIdentificationInput2.type = "text";
			show1.innerText = "HIDE";
		}
	}
	
	document.addEventListener('DOMContentLoaded', function() {
		var citySelect = document.getElementById('citySelect');
		var districtSelect = document.getElementById('districtSelect');
		var country = document.getElementById('country');
		var job = document.getElementById('jobSelect');
		var grade = document.getElementById('customerRank');
		var credit = document.getElementById('creditRank');
		
		// 이전 요청 값을 가져와서 select 요소에 설정
		var cityValue = "<%= customer != null && customer.getCity() != null ? customer.getCity() : "" %>";
		var districtValue = "<%= customer != null && customer.getDistrict() != null ? customer.getDistrict() : "" %>";
		var countryValue = "<%= customer != null && customer.getCountry() != null ? customer.getCountry() : "" %>";
		var jobValue = "<%= customer != null && customer.getJobCode() != null ? customer.getJobCode() : "" %>";
		var gradeValue = "<%= customer != null && customer.getGrade() != null ? customer.getGrade() : "" %>";
		var creditValue = "<%= customer != null && customer.getCredit() != null ? customer.getCredit() : "" %>";

		for (var i = 0; i < citySelect.options.length; i++) {
			if (citySelect.options[i].value === cityValue) {
				citySelect.selectedIndex = i;
				changeCounty(i);
				break;
			}
		}
		for (var i = 0; i < districtSelect.options.length; i++) {
			if (districtSelect.options[i].value === districtValue) {
				districtSelect.selectedIndex = i;
				break;
			}
		}
		for (var i = 0; i < country.options.length; i++) {
			if (country.options[i].value === countryValue) {
				country.selectedIndex = i;
				break;
			}
		}
		for (var i = 0; i < job.options.length; i++) {
			if (job.options[i].value === jobValue) {
				job.selectedIndex = i;
				break;
			}
		}
		for (var i = 0; i < grade.options.length; i++) {
			if (grade.options[i].value === gradeValue) {
				grade.selectedIndex = i;
				break;
			}
		}
		for (var i = 0; i < credit.options.length; i++) {
			if (credit.options[i].value === creditValue) {
				credit.selectedIndex = i;
				break;
			}
		}
	});
</script>
</body>
</html>