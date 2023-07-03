<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지점 목록: 하나로여신관리시스템</title>
<link rel="icon" href="${pageContext.request.contextPath}/public/images/favicon.svg">
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
			<div class="innerSubTitle"><h2>지점 정보</h2></div>
				<form action="${pageContext.request.contextPath}/bank/list" method="post">
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">지점 이름</div>
						<input name="bankName" id="bankLocNameSearchInput" class="innerMiddleInput"
						value="<%= bankDTO == null || bankDTO.getBankName() == null ? "" : bankDTO.getBankName() %>">
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">거주지</div>
						<div class="innerInformationRowSubtitle">시·도</div>
						<select id="citySelect" name="citySelect" class="innerSelectBox2 customerCity" onchange="changeCounty(this.selectedIndex);">
							<option value="">-</option>
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
						<div class="innerInformationRowSubtitle">시·군·구</div>
						<select id="districtSelect" name="district" class="select">
							<option value="">-</option>
						</select>
					</div>
				</div>
				<div class="scrollTo"></div>
				<div class="innerButtonContainer">
					<button type="submit">검색</button>
				</div>
			<div class="searchTitle"><h1>검색 결과</h1><p><%= (bankDTO != null && bankDTO != null) ? "총 " + bankDTO.getCount() + "개의 검색 결과가 있습니다." : "" %></p></div>
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
				<tr class="noResultRow">
					<td><%= bank.getBankId() %></td>
					<td><%= bank.getBankName() %></td>
					<td><%= bank.getPhoneNumber() %></td>
					<td><%= bank.getLocation() %></td>					
				</tr>
				<%
					}
				} else {
					%>
					<tr class="searchResultRow noResultRow">
						<td colspan="8"> 검색 결과가 없습니다. </td>
					</tr>
					<%
				}
				%>	
			</table>
			<%
				// customerSearchDTO에서 page 값과 count 변수 추출
				int count = (bankDTO != null && bankDTO.getCount() != 0) ? bankDTO.getCount() : 0;
				int pages = (bankDTO != null && bankDTO != null) ? bankDTO.getPage() : 1;

				// 페이징 처리 로직
				int pageSize = 20; // 한 페이지에 표시할 레코드 수
				int totalPages = (int) Math.ceil((double) count / pageSize); // 전체 페이지 수
				int currentPage = pages; // 현재 페이지
				int startPage = Math.max(1, currentPage - ((currentPage-1) % 10)) ; // 시작 페이지
				int endPage = Math.min(startPage + 9, totalPages); // 끝 페이지

				// 이전 페이지와 다음 페이지 계산
				int prevPage = startPage - 1;
				int nextPage = endPage + 1;
				
				// 이전 페이지와 다음 페이지 범위 검사
				prevPage = Math.max(1, prevPage);
				nextPage = Math.min(totalPages, nextPage);
				%>
				
				<!-- 페이지 번호 표시 -->
				<div class="pagination">
					<% if (currentPage > 1) { %>
						<button type="submit" name="page" value="1"><<</button>
						<button type="submit" name="page" value="<%= prevPage %>"><</button>
					<% } %>
					
					<% for (int i = startPage; i <= endPage; i++) { %>
						<% if (i == currentPage) { %>
							<button type="submit" class="activePage" name="page" value="<%= i %>"><%= i %></button>
						<% } else { %>
							<button type="submit" name="page" value="<%= i %>"><%= i %></button>
						<% } %>
					<% } %>
					
					<% if (currentPage < totalPages) { %>
						<button type="submit" name="page" value="<%= nextPage %>">></button>
						<button type="submit" name="page" value="<%= totalPages %>">>></button>
					<% } %>
				</div>
			</form>
		</div>
		<button class="upToButton" onclick="scrollToTop()">
			<svg width="30" height="20" viewBox="0 0 12 10" fill="none" xmlns="http://www.w3.org/2000/svg">
				<path d="M6 0L0 6L1.41 7.41L6 2.83L10.59 7.41L12 6L6 0Z" fill="#323232"/>
			</svg>
		</button>
	</main>
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js"></script>
	<script src="${pageContext.request.contextPath}/js/components/searchResultTable.js"></script>
	<script>
		<%
		if (getBankList != null) {
			%>
			scrollToBottom();
			<%
		}
		%>
		
		generateMenu('employee', 'bankList');	
		
		const citySelect = document.getElementById('citySelect');
		citySelect.value = "${searchInputValue.city}";
		
		changeCounty(citySelect.selectedIndex);
		
		const districtSelect = document.getElementById('districtSelect');
		districtSelect.value = "${searchInputValue.district}";
	</script>
</body>
</html>
