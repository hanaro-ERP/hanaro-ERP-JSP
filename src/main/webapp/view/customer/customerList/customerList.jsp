<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/customer/customerList/customerList.css?ver=1">
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
						<select id="citySelect" class="innerSelectBox customerCity">
							<option value="Seoul">서울</option>
							<option value="Busan">부산</option>
							<option value="Daegu">대구</option>
							<option value="Incheon">인천</option>
							<option value="Gwangju">광주</option>
							<option value="Daejeon">대전</option>
							<option value="Ulsan">울산</option>
							<option value="Sejong">세종</option>
							<option value="Gyeonggi">경기</option>
							<option value="Gangwon">강원</option>
							<option value="Chungbuk">충북</option>
							<option value="Chungnam">충남</option>
							<option value="Jeonbuk">전북</option>
							<option value="Jeonnam">전남</option>
							<option value="Gyeongbuk">경북</option>
							<option value="Gyeongnam">경남</option>
							<option value="Jeju">제주</option>
						</select>
						<div class="innerInformationRowSubtitle">시·군·구</div>
						<select id="district" class="innerSelectBox customerCity">
							<optgroup label="서울특별시">
							    <option value="종로구">종로구</option>
							    <option value="중구">중구</option>
							    <option value="용산구">용산구</option>
							    <!-- 서울특별시의 다른 구들을 추가로 작성 -->
							</optgroup>
							<optgroup label="부산광역시">
							    <option value="중구">중구</option>
							    <option value="서구">서구</option>
							    <option value="동구">동구</option>
							    <!-- 부산광역시의 다른 구들을 추가로 작성 -->
							</optgroup>
							<!-- 다른 시/도들을 추가로 작성 -->
						</select>
					</div>
				</div>
				<div class="innerButtonContainer">
					<button id="customerSearchButton" type="button">검색</button>
				</div>
			</form>
		</div>
	</main>
	<script>
		generateMenu('customer');		
	</script>
	<script src="${pageContext.request.contextPath}/view/loanProductList/loanProductList.js "></script>
	<script src="${pageContext.request.contextPath}/view/customerList/customerList.js "></script>
</body>
</html>
