<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/employee/bank/bankList.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchResultTable/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchLayout/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/components/aside/aside.js "></script>
</head>
<body>
	<%@ include file="../../../components/header/header.jsp" %>
	<main>
		<%@ include file="../../../components/aside/aside.jsp" %>
		
		<div class="innerContainer" id="container2">
			<div class="innerTitle"><h1>지점 목록</h1></div>
			<form action="${pageContext.request.contextPath}/bankList" method="post">
				<div class="innerSubTitle"><h2>지점 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">이름(ID)</div>
						<input name="bankName" id="bankLocNameSearchInput" class="innerMiddleInput"></input>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">거주지</div>
						<div class="innerInformationRowSubtitle">시·도</div>
						<select name="citySelect" name="city" class="innerSelectBox customerCity" onchange="changeCounty(this.selectedIndex);">
							<option value="전체">전체</option>
						    <option value="서울">서울특별시</option>
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
						<select name="district" class="select"> <!--  class="innerSelectBox customerCity"> -->
							<option value="">전체</option>
						</select>
					</div>
					<div class="innerButtonContainer">
					<button type="submit">검색</button>
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
				
				
				%>
				
			</table>
		</div>
	</main>
	<script>
		generateMenu('employee', 'bankList');		
	</script>
	<script src="${pageContext.request.contextPath}/view/employee/bank/bankList.js "></script>
</body>
</html>
