<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/customerList.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js "></script>
</head>
<body>	
	<%@page import="java.util.Arrays"%>
	<%@ page import="java.util.List" %>
	<%@ page import="DTO.CustomerDTO" %>
	<%@ page import="DTO.CustomerSearchDTO" %>
	<%@ include file="../../components/header.jsp" %>	
	<main>
		<%@ include file="../../components/aside.jsp" %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>고객 검색</h1></div>
			<form action="${pageContext.request.contextPath}/customer/list" method="post">
				<div class="innerSubTitle"><h2>기본 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">고객 이름</div>
						<input name="customerName" class="innerSearchInput" value="${customerInput.customerName}"></input>
						<div class="innerInformationRowTitle">직업 코드</div>
						<select id="jobCode" name="jobCode" class="innerSelectBox">
							<option value="">-</option>
							<option value="job000">000</option>
							<option value="job001">001</option>
						</select>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">담당 직원</div>
						<input name="customerEmployee" class="innerSearchInput" value="${customerInput.employeeName}"></input>
						<div class="innerInformationRowTitle">담당 지점</div>
						<select id="bankLocation" name="bankLocation" class="innerSelectBox">
							<option value="">-</option>
							<option value="성수점">성수점</option>
							<option value="하나로점">하나로점</option>
						</select>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">주민번호</div>
						<input name="identificationNumber1" class="innerMiddleInput2" value="${customerInput.identification1}"></input>&nbsp;-&nbsp;
						<input name="identificationNumber2" class="innerMiddleInput2" value="${customerInput.identification2}"></input>
						<div class="innerInformationRowTitle">전화번호</div>
						<input name="phoneNumber1" class="innerShortInput" value="${customerInput.phoneNumber1}"></input>&nbsp;-&nbsp;
						<input name="phoneNumber2" class="innerShortInput" value="${customerInput.phoneNumber2}"></input>&nbsp;-&nbsp;
						<input name="phoneNumber3" class="innerShortInput" value="${customerInput.phoneNumber3}"></input>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">나이</div>
						<ul id="customerAge" class="directInputUl">
							<li id="ageAllLi"><input type="checkbox" value="" id="ageAll">전체</li>
							<li id="directInput">
							    <p>직접 입력</p>
							    <input id="directAgeInput1" name="customerAge" class="directInputValue" disabled="true">&nbsp;세 이상&nbsp;&nbsp;
							    <input id="directAgeInput2" name="customerAge" class="directInputValue" disabled="true">&nbsp;세 이하&nbsp;
							</li>
							<li id="age10sLi"><input type="checkbox" value="10대" id="age10s">10대</li>
							<li id="age20sLi"><input type="checkbox" value="20대" id="age20s">20대</li>
							<li id="age30sLi"><input type="checkbox" value="30대" id="age30s">30대</li>
							<li id="age40sLi"><input type="checkbox" value="40대" id="age40s">40대</li>
							<li id="age50sLi"><input type="checkbox" value="50대" id="age50s">50대</li>
							<li id="age60sLi"><input type="checkbox" value="60대" id="age60s">60대</li>
							<li id="age70sLi"><input type="checkbox" value="70대" id="age70s">70대</li>
							<li id="age80sLi"><input type="checkbox" value="80대" id="age80s">80대</li>
							<li id="age80sPlusLi"><input type="checkbox" value="80대 이상" id="age80sPlus">80대 이상</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">성별</div>
						<ul id="customerGender">
							<li id="genderAllLi"><input type="checkbox" value="" id="genderAll">전체</li>
							<li id="genderMaleLi"><input type="checkbox" value="남성" id="genderMale">남성</li>
							<li id="genderFemaleLi"><input type="checkbox" value="여성" id="genderFemale">여성</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">고객 분류</div>
						<ul id="customerGrade">
							<li id="gradeAllLi"><input type="checkbox" value="" id="membershipAll">전체</li>
							<li id="gradeVvipLi"><input type="checkbox" value="VVIP" id="membershipVVIP">VVIP</li>
							<li id="gradeVipLi"><input type="checkbox" value="VIP" id="membershipVIP">VIP</li>
							<li id="gradeGoldLi"><input type="checkbox" value="GOLD" id="membershipGOLD">GOLD</li>
							<li id="gradeSilverLi"><input type="checkbox" value="SILVER" id="membershipSILVER">SILVER</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">신용 등급</div>
						<ul id="customerCredit">
							<li id="creditAllLi"><input type="checkbox" value="">전체</li>
							<li id="credit1Li"><input type="checkbox" value="1등급">1등급</li>
							<li id="credit2Li"><input type="checkbox" value="2등급">2등급</li>
							<li id="credit3Li"><input type="checkbox" value="3등급">3등급</li>
							<li id="credit4Li"><input type="checkbox" value="4등급">4등급</li>
							<li id="credit5Li"><input type="checkbox" value="5등급">5등급</li>
						</ul>
					</div>
				</div>
				<div class="innerSubTitle">
					<h2>세부 정보</h2>
					<button id="customerDetailButton" name="close" type="button">▲</button>
					<input id="checkOpen" name="isOpen" value="close"></input>
				</div>
					
				<div class="innerInformation" id="customerDetailInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">소속 국가</div>
						<select id="countrySelect" name="country" class="innerSelectBox">
							<option value="">-</option>
							<option value="SouthKorea">대한민국</option>
							<option value="USA">미국</option>
							<option value="China">중국</option>
							<option value="Japan">일본</option>
						</select>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">거주지</div>
						<div class="innerInformationRowSubtitle">시·도</div>
						<select id="citySelect" name="city" class="innerSelectBox customerCity" onchange="changeCounty(this.selectedIndex);">
							<option value="">-</option>
						    <option value="서울시">서울특별시</option>
						    <option value="부산">부산광역시</option>
						    <option value="대구">대구광역시</option>
						    <option value="인천">인천광역시</option>
						    <option value="광주">광주광역시</option>
						    <option value="대전">대전광역시</option>
						    <option value="울산">울산광역시</option>
						    <option value="경기">경기도</option>
						    <option value="강원">강원도</option>
						    <option value="충북">충청북도</option>
						    <option value="충남">충청남도</option>
						    <option value="전북">전라북도</option>
						    <option value="전남">전라남도</option>
						    <option value="경북">경상북도</option>
						    <option value="경남">경상남도</option>
						    <option value="제주">제주도</option>
						</select>
						<div class="innerInformationRowSubtitle">시·군·구</div>
						<select id="districtSelect" name="district" class="select">
							<option value="">-</option>
						</select>
					</div>
				</div>
				<div class="innerButtonContainer">
					<button id="customerSearchButton" type="submit">검색</button>
				</div>
			</form>
			
			<div class="searchTitle"><h1>검색 결과</h1></div>
			<table class="searchTable" id="customerSearchTable">
				<tr>
					<th>고객 ID</th>
					<th>이름</th>
					<th>나이</th>
					<th>성별</th>
					<th>신용 등급</th>
					<th>작업 코드</th>
					<th>고객 등급</th>
					<th>위험도</th>
				</tr>
				<!-- 받아온 유저 정보를 테이블로 나타내는 코드 -->
				<%
				List<CustomerDTO> findCustomers = (List<CustomerDTO>) request.getAttribute("customerList");

				if (findCustomers != null && !findCustomers.isEmpty()) {
				    for (CustomerDTO customer : findCustomers) {
				      %>
				      <tr>
				        <td class="customerId"><%= customer.getCustomerId() %></td>
				        <td><%= customer.getCustomerName() %></td>
				        <td><%= customer.getAge() %></td>
				        <td><%= customer.getStrGender() %>
				        <td><%= customer.getCredit() %></td>
				        <td><%= customer.getJobCode() %></td>
						<td><%= customer.getGrade() %></td>
						<td>더미데이터 98</td>
				      </tr>
				      <%
				    }
				  }
				%>
			</table>
		</div>
	</main>
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js"></script>
	<script>
		generateMenu('customer', 'customerList');		
	</script>

	<script src="${pageContext.request.contextPath}/js/customer/customerList.js"></script>
	<%
	CustomerSearchDTO customerSearchDTO = (CustomerSearchDTO) request.getAttribute("customerInput");
	String[] customerAges = null;
	String[] customerGrades = null;
	String[] customerCredits = null;
	String gender = null;
	String isOpen = null;
	
	if (customerSearchDTO != null) {
	    customerAges = customerSearchDTO.getCustomerAges();
	    customerGrades = customerSearchDTO.getCustomerGrades();
	    customerCredits = customerSearchDTO.getCustomerCredits();
	    gender = customerSearchDTO.getStrGender();
	    isOpen = customerSearchDTO.getIsOpen();
	}

	%>
	<script>
		const jobCode = document.getElementById('jobCode');
		jobCode.value = "${customerInput.jobCode}";
		
		const bankLocation = document.getElementById('bankLocation');
		bankLocation.value = "${customerInput.bankName}";
		
		const countrySelect = document.getElementById('countrySelect');
		countrySelect.value = "${customerInput.country}";

		const citySelect = document.getElementById('citySelect');
		citySelect.value = "${customerInput.city}";
		
		changeCounty(citySelect.selectedIndex);
		
		const districtSelect = document.getElementById('districtSelect');
		districtSelect.value = "${customerInput.district}";
		
		<%
		if (isOpen != null) {
			if (isOpen.equals("open")) {
				%>
				revealDetail();
				<%
			}
		}
		%>

		<%
		if (gender != null) {
			%>
	    	const genderAllLi = document.getElementById('genderAllLi');

			if ("<%= gender %>" === "여성") {
				const genderFemaleLi = document.getElementById('genderFemaleLi');
				selectItem(genderFemaleLi, "customerGender");
				unselectItem(genderAllLi);
			} else if ("<%= gender %>" === "남성") {
				const genderMaleLi = document.getElementById('genderMaleLi');
				selectItem(genderMaleLi, "customerGender");
				unselectItem(genderAllLi);
			}
			<%
		}
		%>
		
    	const ageAllLi = document.getElementById('ageAllLi');
		selectItem(ageAllLi, "customerAge");
		<%
		if (customerAges != null) {
			for (int i = 0; i < customerAges.length; i++) {
				%>
				<% if (!customerAges[i].isEmpty()) { %>
					unselectItem(ageAllLi);
					if ("<%= customerAges[i] %>" === "10대") {
						const age10sLi = document.getElementById('age10sLi');
						selectItem(age10sLi, "customerAge");
					} else if ("<%= customerAges[i] %>" === "20대") {
						const age20sLi = document.getElementById('age20sLi');
						selectItem(age20sLi, "customerAge");
					} else if ("<%= customerAges[i] %>" === "30대") {
						const age30sLi = document.getElementById('age30sLi');
						selectItem(age30sLi, "customerAge");
					} else if ("<%= customerAges[i] %>" === "40대") {
						const age40sLi = document.getElementById('age40sLi');
						selectItem(age40sLi, "customerAge");
					} else if ("<%= customerAges[i] %>" === "50대") {
						const age50sLi = document.getElementById('age50sLi');
						selectItem(age50sLi, "customerAge");
					} else if ("<%= customerAges[i] %>" === "60대") {
						const age60sLi = document.getElementById('age60sLi');
						selectItem(age60sLi, "customerAge");
					} else if ("<%= customerAges[i] %>" === "70대") {
						const age70sLi = document.getElementById('age70sLi');
						selectItem(age70sLi, "customerAge");
					} else if ("<%= customerAges[i] %>" === "80대") {
						const age80sLi = document.getElementById('age80sLi');
						selectItem(age80sLi, "customerAge");
					} else if ("<%= customerAges[i] %>" === "80대 이상") {
						const age80sPlusLi = document.getElementById('age80sPlusLi');
						selectItem(age80sPlusLi, "customerAge");
					} else {
						const directInput = document.getElementById('directInput');
						const inputs = directInput.querySelectorAll('input');
						console.log(inputs);
						console.log("<%= customerAges[0] %>");
	
						inputs[0].value = "<%= customerAges[0] %>"; 
						<% if (customerAges.length >= 2) { %>
						inputs[1].value = "<%= customerAges[1] %>";
						<% } %>
						
						selectItem(directInput, "customerAge");
						toggleDirectInput(directInput, true);
					}
				<%
				}
			}
		}
		%>

		const gradeAllLi = document.getElementById('gradeAllLi');
		selectItem(gradeAllLi, "customerGrade");
		<%
		if (customerGrades != null) {
			for (int i = 0; i < customerGrades.length; i++) {
				%>
				<% if (!customerGrades[i].isEmpty()) { %>
					unselectItem(gradeAllLi);
					if ("<%= customerGrades[i] %>" === "VVIP") {
						const gradeVvipLi = document.getElementById('gradeVvipLi');
						selectItem(gradeVvipLi, "customerGrade");
					} else if ("<%= customerGrades[i] %>" === "VIP") {
						const gradeVipLi = document.getElementById('gradeVipLi');
						selectItem(gradeVipLi, "customerGrade");
					} else if ("<%= customerGrades[i] %>" === "GOLD") {
						const gradeGoldLi = document.getElementById('gradeGoldLi');
						selectItem(gradeGoldLi, "customerGrade");
					} else if ("<%= customerGrades[i] %>" === "SILVER") {
						const gradeSilverLi = document.getElementById('gradeSilverLi');
						selectItem(gradeSilverLi, "customerGrade");
					} 
				<%
				}
			}
		}
		%>
		
		const creditAllLi = document.getElementById('creditAllLi');
		selectItem(creditAllLi, "customerCredit");
		<%
		if (customerCredits != null) {
			for (int i = 0; i < customerCredits.length; i++) {
				%>
				<% if (!customerCredits[i].isEmpty()) { %>
					unselectItem(creditAllLi);
					if ("<%= customerCredits[i] %>" === "1등급") {
						const credit1Li = document.getElementById('credit1Li');
						selectItem(credit1Li, "customerCredit");
					} else if ("<%= customerCredits[i] %>" === "2등급") {
						const credit2Li = document.getElementById('credit2Li');
						selectItem(credit2Li, "customerCredit");
					} else if ("<%= customerCredits[i] %>" === "3등급") {
						const credit3Li = document.getElementById('credit3Li');
						selectItem(credit3Li, "customerCredit");
					} else if ("<%= customerCredits[i] %>" === "4등급") {
						const credit4Li = document.getElementById('credit4Li');
						selectItem(credit4Li, "customerCredit");
					} else if ("<%= customerCredits[i] %>" === "5등급") {
						const credit5Li = document.getElementById('credit5Li');
						selectItem(credit5Li, "customerCredit");
					} 
				<%
				}
			}
		}
		%>
	</script>
</body>
</html>
