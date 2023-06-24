<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>상품 가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/inputTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loan/productSubscription.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js "></script>
</head>
<body>
	<%@ include file="../../components/header.jsp" %>
	<main>
		<%@ include file="../../components/aside.jsp" %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>상품 가입</h1></div>
			<form action="${pageContext.request.contextPath}/loanSubscription" method="post" onsubmit="return validateForm()">
				<div class="innerSubTitle"><h2>고객 정보</h2></div>
				<table class="inputTable">
 					<tr>
						<th>이름</th>
						<td><input id="customerName" name="customerName" class="middleInput"/></td>
						<th>전화번호</th>
						<td>
							<input type="number" id="phoneNumber1" name="phoneNumber" class="shortInput"/>
							-
							<input type="number" id="phoneNumber2" name="phoneNumber" class="shortInput"/>
							-
							<input type="number" id="phoneNumber3" name="phoneNumber" class="shortInput"/>
						</td>
					</tr>
					<tr>
						<th>보증인</th>
						<td><input id="suretyName" name="suretyName" class="middleInput"/></td>
						<th>주민번호</th>
						<td>
							<input id="residentRegistrationNumber1" name="residentRegistrationNumber" class="shortInput"/>
								-
							<input id="residentRegistrationNumber2" name="residentRegistrationNumber" class="shortInput"/>
						</td>
					</tr>
					<tr>
						<th>국가</th>
						<td>
							<select name="country" class="shortSelect">
								<option value="southKorea">대한민국</option>
								<option value="usa">미국</option>
								<option value="china">중국</option>
								<option value="japan">일본</option>
							</select>
						</td>
						<th>거주지</th>
						<td>
							<select name="city" class="shortSelect" onchange="changeCounty(this.selectedIndex);">
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
							<select name="district" class="shortSelect">
								<option value="">전체</option>
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
						<th>고객 등급</th>
						<td>
							<select name="customerRank" class="shortSelect">
								<option value="Green">Green</option>
								<option value="Famil">Family</option>
								<option value="Hana Family">Hana Family</option>
								<option value="VIP">VIP</option>
								<option value="Hana">Hana VIP</option>
							</select>
						</td>
						<th>신용 등급</th>
						<td>
							<select name="creditRank" class="shortSelect">
								<option value="credit1">1</option>
								<option value="credit2">2</option>
								<option value="credit3">3</option>
								<option value="credit4">4</option>
								<option value="credit4">5</option>
							</select>
							급
						</td>
					</tr>
					<tr>
						<th>직업</th>
						<td>
							<select name="job" class="shortSelect">
								<option value="governmentEmployee">공무원</option>
								<option value="personalBussiness">자영업</option>
								<option value="houseWife">주부</option>
								<option value="unemployed">무직</option>
							</select>
						</td>
					</tr>
				</table>
				<div class="innerSubTitle"><h2>상품 정보</h2></div>
				<table class="inputTable">
					<tr>
						<th>대출 구분</th>
						<td>
							<select name="loanType" class="shortSelect">
								<option value="creditLoan">신용대출</option>
								<option value="collateralLoan">담보대출</option>
							</select>
						</td>
						<th>상품명</th>
						<td>
							<select name="loanProductName" class="longSelect">
								<option value="prod1">상품1</option>
								<option value="prod2">상품2</option>
								<option value="prod3">상품3</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>담보</th>
						<td>
							<select name="collateral" class="shortSelect">
								<option value="house">주택</option>
								<option value="car">자동차</option>
								<option value="car">주식</option>
							</select>
						</td>
						<th>담보가치</th>
						<td>
							<input type="number" id="collateralValue" name="collateralValue" class="shortInput" type="number" min="0" max="999" step="1"/>
							천만 원
						</td>
					</tr>
					<tr>
						<th>대출 금액</th>
						<td>
							&nbsp;최대
							<input type="number" id="loanAmount" name="loanAmount" class="shortInput" type="number" min="0" max="999" step="1"/>
							천만 원
						</td>
						<th>이자</th>
						<td>
							<select name="interest" class="shortSelect">
								<option value="simple">복리</option>
								<option value="compound">단리</option>
							</select>
							&nbsp;연
							<input type="number" step="0.1" max="10" id="interestRate" name="interestRate" class="shortInput" />
							%
						</td>
					</tr>
					<tr>
						<th>대출 목적</th>
						<td>
							<select name="loanPerpose" class="shortSelect">
								<option value="dunno" >??</option>
								<option value="dunno">??</option>
							</select>
						</td>
						<th>상환 방법</th>
						<td>
							<select name="repaymentMethod" class="shortSelect">
								<option value="dunno">??</option>
								<option value="dunno">??</option>
							</select>
						</td>
					</tr>
				</table>
				<div class="innerButtonContainer">
					<button type="submit">검색</button>
				</div>
			</form>
		</div>
	</main>
	<script>
		generateMenu('loan', 'productSubscription');		
	</script>
	<script src="${pageContext.request.contextPath}/js/components/inputTable.js"></script>
	<script src="${pageContext.request.contextPath}/js/loan/productSubscription.js"></script>
</body>
</html>