<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer/customerRegist.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/inputTable.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js "></script>
</head>
<body>	
	<%@ include file="../../components/header.jsp" %>
	<main>
		<%@ include file="../../components/aside.jsp" %>
		<%@ page import="DTO.CustomerDTO" %>
		<% CustomerDTO customer = (CustomerDTO) request.getAttribute("customer"); %>			
		<div class="innerContainer">
			<div class="innerTitle"><h1>고객 등록</h1></div>
			<form action="${pageContext.request.contextPath}/customer/register" method="post" onsubmit="return validateForm()">
				<div class="innerSubTitle"><h2>고객 정보</h2></div>
				<table class="inputTable">
 					<tr>
						<th>이름</th>
						<td><input id="customerName" name="customerName" class="middleInput"/></td>
						<th>전화번호</th>
						<td><input id="phoneNumber" name="phoneNumber" class="middleInput"/></td>
					</tr>
					<tr>
						<th>거주지</th>
						<td>
							<select name="citySelect" class="innerSelectBox customerCity" onchange="changeCounty(this.selectedIndex);">
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
							<select name="district" class="select" id="districtSelect">
								<option value="">-</option>
							</select>
						</td>
						<th>국가</th>
						<td>
							<select name="country" class="shortSelect">
								<option value="southKorea">대한민국</option>
								<option value="usa">미국</option>
								<option value="china">중국</option>
								<option value="japan">일본</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>주민번호</th>
						<td>
							<input id="residentRegistrationNumber1" name="residentRegistrationNumber" class="shortInput"/>
								-
							<input type="password" id="residentRegistrationNumber2" name="residentRegistrationNumber" class="shortInput" maxlength="7"/>
						</td>
						<th>직업</th>
						<td>
							<select id="loanTypeSelect" name="job" class="shortSelect">
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
						<th>신용 등급</th>
						<td>
							<select name="creditRank" class="shortSelect">
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
							<select name="customerRank" class="shortSelect">
								<option value="SILVER">SILVER</option>
								<option value="GOLD">GOLD</option>
								<option value="VIP">VIP</option>
								<option value="VVIP">VVIP</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>담당 직원</th>
						<td><input type="text" id="employeeName" name="employeeName" class="middleInput"/></td>
						<th class="office">주거래지점</th>
						<td><input type="text" id="bank" name="bank" class="middleInput"/></td>
					</tr>
					<tr>
						<th>보증인</th>
						<td colspan=4>
						<input id="suretyName" name="suretyName" class="shortInput" value="<%= customer != null ? customer.getCustomerName() : "" %>"/>
						<button type="button" onclick="openSearchPopup()">검색</button></td>						
					</tr>
				</table>
				<div class="innerButtonContainer">
					<button type="submit">등록</button>
				</div>
			</form>
		</div>	
	</main>
	<script>
		generateMenu('customer', 'customerList', 'customerRegist');		
	</script>
	<script src="${pageContext.request.contextPath}/js/components/inputTable.js"></script>
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js"></script>
	<script src="${pageContext.request.contextPath}/js/customer/customerList.js"></script>
	<script>
	    function openSearchPopup() {
	    	var firstTd = document.getElementById("suretyName"); // customerName 필드를 가리키는 변수 firstTd
	    	if(firstTd.value !== '')
		    	window.open("/hanaro-ERP-JSP/customerSearch?name=" + firstTd.value, "_blank", "width=500,height=300");
	    }
	</script>
</body>
</html>
