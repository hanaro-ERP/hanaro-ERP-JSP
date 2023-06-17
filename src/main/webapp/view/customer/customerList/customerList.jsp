<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/customer/customerList/customerList.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchResultTable/searchResultTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/components/searchLayout/searchLayout.css?ver=1">
<script src="${pageContext.request.contextPath}/components/aside/aside.js "></script>
</head>
<body>
	<%@ include file="../../../components/header/header.jsp" %>
	<main>
		<%@ include file="../../../components/aside/aside.jsp" %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>고객 검색</h1></div>
			<form>
				<div class="innerSubTitle"><h2>기본 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">이름(ID)</div>
						<input class="innerSearchInput" id="customerNameInput"></input>
						<div class="innerInformationRowTitle">직업 코드</div>
						<select id="jobCode" class="innerSelectBox">
							<option value="job000">000</option>
							<option value="job001">001</option>
						</select>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">담당 직원</div>
						<input class="innerSearchInput"  id="customerEmployeeInput"></input>
						<div class="innerInformationRowTitle">담당 지점</div>
						<select id="bankLocation" class="innerSelectBox">
							<option value="location1">성수역점</option>
							<option value="location2">서울숲점</option>
							<option value="location3">신자양점</option>
						</select>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">나이</div>
						<ul id="customerAge">
							<li>전체</li>
							<li>직접 입력</li>
							<li id="directInput">
								<input id="startAge" class="directInputValue" disabled="true"><p class="directInputText"> 세 이상</p>
								<input id="endAge" class="directInputValue" disabled="true"><p class="directInputText"> 세 이하</p>
							</li>
							<li>10대</li>
							<li>20대</li>
							<li>30대</li>
							<li>40대</li>
							<li>50대</li>
							<li>60대</li>
							<li>70대</li>
							<li>80대 이상</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">성별</div>
						<ul id="customerGender">
							<li>전체</li>
							<li>남성</li>
							<li>여성</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">고객 분류</div>
						<ul id="customerGrade">
							<li>전체</li>
							<li>VVIP</li>
							<li>VIP</li>
							<li>GOLD</li>
							<li>SILVER</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">신용 등급</div>
						<ul id="customerCredit">
							<li>전체</li>
							<li>1급</li>
							<li>2급</li>
							<li>3급</li>
							<li>4급</li>
							<li>5급</li>
						</ul>
					</div>
				</div>
				<div class="innerSubTitle"><h2>세부 정보<button id="customerDetailButton" type="button">▲</button></h2></div>
				<div class="innerInformation" id="customerDetailInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">소속 국가</div>
						<select id="customerCountry" class="innerSelectBox">
							<option value="SouthKorea">대한민국</option>
							<option value="USA">미국</option>
							<option value="China">중국</option>
							<option value="Japan">일본</option>
						</select>
						<div class="innerInformationRowTitle">장애 등급</div>
						<ul id="disabilityGrade">
							<li>전체</li>
							<li>비장애</li>
							<li>1급</li>
							<li>2급</li>
							<li>3급</li>
						</ul>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">거주지</div>
						<div class="innerInformationRowSubtitle">시·도</div>
						<form name="form">
							<select id="citySelect" name="city" class="innerSelectBox customerCity" onchange="changeCounty(this.selectedIndex);">
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
						</form>
					</div>
				</div>
				<div class="innerButtonContainer">
					<button id="customerSearchButton" type="button">검색</button>
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
					<th>담당 직원</th>
					<th>주거래지점</th>
					<th>국가</th>
					<th>장애 등급</th>
					<th>작업 코드</th>
					<th>고객 등급</th>
					<th>위험도</th>
				</tr>
				<tr>
					<td>001</td>
					<td>백연정</td>
					<td>27</td>
					<td>여</td>
					<td>1등급</td>
					<td>백연정</td>
					<td>성수점</td>
					<td>대한민국</td>
					<td>-</td>
					<td>000</td>					
					<td>VVIP</td>
					<td>0점</td>
				</tr>
				<tr>
					<td>001</td>
					<td>백연정</td>
					<td>27</td>
					<td>여</td>
					<td>1등급</td>
					<td>백연정</td>
					<td>성수점</td>
					<td>대한민국</td>
					<td>-</td>
					<td>000</td>					
					<td>VVIP</td>
					<td>0점</td>
				</tr>
				<tr>
					<td>001</td>
					<td>백연정</td>
					<td>27</td>
					<td>여</td>
					<td>1등급</td>
					<td>백연정</td>
					<td>성수점</td>
					<td>대한민국</td>
					<td>-</td>
					<td>000</td>					
					<td>VVIP</td>
					<td>0점</td>
				</tr>
			</table>
		</div>
	</main>
	<script>
		generateMenu('customer', 'customerList');		
	</script>
	<script src="${pageContext.request.contextPath}/view/customer/customerList/customerList.js "></script>
</body>
</html>
