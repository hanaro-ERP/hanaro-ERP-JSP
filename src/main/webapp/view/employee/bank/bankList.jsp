<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			<div class="innerInformationContainer">
				<div class="innerSubTitle"><h2>지점 정보</h2></div>
				<div class="innerInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">이름(ID)</div>
						<input id="bankLocNameSearchInput"></input>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">거주지</div>
						<div class="innerInformationRowSubtitle">시·도</div>
						<select id="citySelect" class="innerSelectBox">
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
						<select id="district" class="innerSelectBox">
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
			</div>
		</div>
	</main>
	<script>
		generateMenu('employee', 'bankList');		
	</script>
	<script src="${pageContext.request.contextPath}/view/employee/empList/empList.js "></script>
</body>
</html>