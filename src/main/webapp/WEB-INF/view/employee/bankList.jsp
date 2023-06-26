<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/employee/bankList.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js "></script>
</head>
<body>
	<%@ page import="java.util.List" %>
	<%@ page import="DTO.BankDTO" %> 
	<%@ include file="../../components/header.jsp" %>
	<main>
		<%@ include file="../../components/aside.jsp" %>
		<% List<BankDTO> getBankList =(List<BankDTO>)request.getAttribute("findBankList");
			BankDTO bankDTO = (BankDTO)request.getAttribute("searchInputValue");%>
		<div class="innerContainer" id="container2">
			<div class="innerTitle"><h1>지점 목록</h1></div>
			<form action="${pageContext.request.contextPath}/bank/list" method="post">
				<div class="innerSubTitle"><h2>지점 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">이름(ID)</div>
						<input name="bankName" id="bankLocNameSearchInput" class="innerMiddleInput"
						value="<%= bankDTO == null || bankDTO.getBankName() == null ? "" : bankDTO.getBankName() %>">
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">거주지</div>
						<div class="innerInformationRowSubtitle">시·도</div>
						<select id="citySelect" name="citySelect" class="innerSelectBox2 customerCity" onchange="changeCounty(this.selectedIndex);">
							<option value="">-</option>
						    <option value="서울특별시">서울특별시</option>
						    <option value="부산시">부산광역시</option>
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
						<div class="innerInformationRowSubtitle">시·군·구</div>
						<select id="districtSelect" name="district" class="select">
							<option value="">-</option>
						</select>
					</div>
					<div class="innerButtonContainer">
						<button type="submit">검색</button>
					</div>
				</div>
			</form>
			<div class="searchTitle"><h1>검색 결과</h1></div>
			<table class="searchTable" id="customerSearchTable">
				<tr>
					<th>지점 ID</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>주소</th>
				</tr>
				<%
				if(getBankList != null && !getBankList.isEmpty()) {
					for(BankDTO bank : getBankList) {
				%>
				<tr>
					<td><%= bank.getBankId() %></td>
					<td><%= bank.getBankName() %></td>
					<td><%= bank.getPhoneNumber() %></td>
					<td><%= bank.getLocation() %></td>					
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
		generateMenu('employee', 'bankList');	
		
		const citySelect = document.getElementById('citySelect');
		citySelect.value = "${searchInputValue.city}";
		
		changeCounty(citySelect.selectedIndex);
		
		const districtSelect = document.getElementById('districtSelect');
		districtSelect.value = "${searchInputValue.district}";
	</script>
</body>
</html>
