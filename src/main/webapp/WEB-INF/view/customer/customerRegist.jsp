<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하나로여신관리시스템 - 고객 등록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/customerRegist.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/inputTable.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js "></script>
</head>
<body>	
	<%@ include file="../../components/header.jsp" %>
	<main>
		<%@ include file="../../components/aside.jsp" %>
		<%@ page import="DTO.CustomerDTO" %>
		<%@ page import="DAO.EmployeeDAO" %>
		<%@ page import="DAO.BankDAO" %>
		<%
			CustomerDTO customer = (CustomerDTO) request.getAttribute("customer");
			CustomerDTO inputData = (CustomerDTO) request.getAttribute("inputData");
			String sessionName = (String)request.getSession().getAttribute("loginName");

			String isCreated = request.getQueryString();
			
			EmployeeDAO employeeDAO = new EmployeeDAO();
			BankDAO bankDAO = new BankDAO();
			
			int id = employeeDAO.getEmployeeIdByEmployeeName(sessionName);
			String bankName = bankDAO.getBankNameByBankId(id);
		%>
		<div class="innerContainer">
			<div class="innerTitle"><h1>고객 등록</h1></div>
			<form action="${pageContext.request.contextPath}/customer/register" method="post" onsubmit="return validateForm()">
				<div class="innerSubTitle"><h2>고객 정보</h2></div>
				<table class="inputTable">
 					<tr>
						<th>이름</th>
						<td><input id="customerName" name="customerName" class="middleInput" value="<%= inputData != null && inputData.getCustomerName() != null ? inputData.getCustomerName() : "" %>"/></td>
						<th>전화번호</th>
						<td><input id="phoneNumber" name="phoneNumber" class="middleInput" value="<%= inputData != null && inputData.getPhoneNumber() != null ? inputData.getPhoneNumber() : "" %>"/></td>
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
								<input id="userIdentification1" name="userIdentification1" class="identification" maxlength="6" value="<%= inputData != null && inputData.getId1() != null ? inputData.getId1() : "" %>"/>
									-
								<input type="password" id="userIdentification2" name="userIdentification2" class="identification" maxlength="7" value="<%= inputData != null && inputData.getId2() != null ? inputData.getId2() : "" %>"/>
								<button type="button" id="show1" onclick="showIdentification1()">SHOW</button>
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
					<tr>
						<th>보증인</th>
						<td colspan=4>
						<div id="findById">
							<input id="guarantorIdentification1" name="identification1" class="identification" maxlength="6" value="<%= customer != null ? customer.getIdentification().substring(0, 6) : "" %>"/>
							-
							<input type="password" id="guarantorIdentification2" id="key" name="identification2" class="identification" maxlength="7" value="<%= customer != null ? customer.getIdentification().substring(7, 14) : "" %>"/>	
							<button type="button" id="show2" onclick="showIdentification2()">SHOW</button>
							<button class="customerDetailButton" id="customerDetailButton" type="button" onclick="return openSearchPopup(this.form)"> 검색 </button>
						</div>
						<div id="findResult" class="findResultTable">
							<%= customer != null ? customer.getCustomerName() : "" %>
							<input name="guarantor" id="searchResultMessage" type="hidden" value="<%= customer != null ? customer.getCustomerName() : "" %>">
							<button class="customerDetailButton" id="customerDetailButton" type="button" onclick="reSearch()"> 재검색 </button>						
						</div>
					</tr>
				</table>
				<div class="innerButtonContainer">
					<button type="submit">등록</button>
				</div>
			</form>
		</div>	
	</main>
	<script>
		generateMenu('customer', 'customerRegist');		
	</script>
	<script src="${pageContext.request.contextPath}/js/components/inputTable.js"></script>
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js"></script>
	<script src="${pageContext.request.contextPath}/js/customer/customerList.js"></script>
	<script>
		<%
		String msg = (String)request.getAttribute("msg");
		if (msg != null) {
			%>
			alert("가입되지 않은 주민등록번호입니다.")
			<%
		}
		%>
		
		const customerIdentificationInput1 = document.getElementById("userIdentification1");
		const customerIdentificationInput2 = document.getElementById("userIdentification2");
		const guarantorIdentificationInput1 = document.getElementById("guarantorIdentification1");
		const guarantorIdentificationInput2 = document.getElementById("guarantorIdentification2");

		var show1 = document.getElementById("show1");
		var show2 = document.getElementById("show2");

		function openSearchPopup() {
			var firstTd = document.getElementById("suretyName"); // customerName 필드를 가리키는 변수 firstTd
			if(firstTd.value !== '')
				window.open("/customerSearch?name=" + firstTd.value + "&pageId=" + 1, "_blank", "width=1170,height=500");
		}

		function openSearchPopup(frm) {
			if (guarantorIdentificationInput1.value !== '' && guarantorIdentificationInput2.value !== '') {
				frm.id = "guarantorFind";
				frm.action="/customer/searchReturn?formId=" + frm.id;
				frm.submit();
				return true;
			}
			else {
				alert("보증인의 주민번호를 입력해주세요.");
			}

			if (document.getElementById("findById").style.display === "none") {
				document.getElementById("findById").style.display = "inline"; // findById 요소를 보여줍니다.
				document.getElementById("searchResultMessage").style.display = "none"; // searchResultMessage 요소를 숨깁니다.
			}
		}

		function showIdentification1() {
			if(customerIdentificationInput2.type == "text") {
				customerIdentificationInput2.type = "password";
				show1.innerText = "SHOW";
			}
			else {
				customerIdentificationInput2.type = "text";
				show1.innerText = "HIDE";
			}
		}

		function showIdentification2() {
			if(guarantorIdentificationInput2.type == "text") {
				guarantorIdentificationInput2.type = "password";
				show2.innerText = "SHOW";
			}
			else {
				guarantorIdentificationInput2.type = "text";
				show2.innerText = "HIDE";
			}
		}

		const findById = document.getElementById("findById");
		const findResult = document.getElementById("findResult");
		const gurantorName = document.getElementById("searchResultMessage");
		findResult.style.display = 'none';

		if (gurantorName.value !== "") {
			findById.style.display = 'none';
			findResult.style.display = 'block';
		}
		function reSearch() {
			findById.style.display='block';
			findResult.style.display = 'none';
		}

		document.addEventListener('DOMContentLoaded', function() {
		var citySelect = document.getElementById('citySelect');
		var districtSelect = document.getElementById('districtSelect');
		var country = document.getElementById('country');
		var job = document.getElementById('jobSelect');
		var grade = document.getElementById('customerRank');
		var credit = document.getElementById('creditRank');
		
		// 이전 요청 값을 가져와서 select 요소에 설정
		var cityValue = "<%= inputData != null && inputData.getCity() != null ? inputData.getCity() : "" %>";
		var districtValue = "<%= inputData != null && inputData.getDistrict() != null ? inputData.getDistrict() : "" %>";
		var countryValue = "<%= inputData != null && inputData.getCountry() != null ? inputData.getCountry() : "" %>";
		var jobValue = "<%= inputData != null && inputData.getJobCode() != null ? inputData.getJobCode() : "" %>";
		var gradeValue = "<%= inputData != null && inputData.getGrade() != null ? inputData.getGrade() : "" %>";
		var creditValue = "<%= inputData != null && inputData.getCredit() != null ? inputData.getCredit() : "" %>";

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

		<% 
		if (isCreated != null) {
			if (isCreated.equals("mod=1")) {
				%>
				alert("고객 등록에 성공했습니다.")
				<%
			} else if (isCreated.equals("mod=-1")) {
				%>
				alert("고객 등록에 실패했습니다.")
				
				<%
			}
		}
		%>
	        
	</script>
</body>
</html>
