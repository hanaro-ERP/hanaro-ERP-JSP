<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>상품 가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loan/productsubscription.css?ver=1">
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
			<form action="${pageContext.request.contextPath}/loan/subscription" method="post" onsubmit="return validateForm()">
				<div class="innerSubTitle"><h2>고객 정보</h2></div>
				<table class="inputTable">
 					<tr>
						<th>이름</th>
						<td><input id="customerName" name="customerName" class="middleInput"/></td>
						<th>전화번호</th>
						<td>
							<input id="phoneNumber" name="phoneNumber" class="middleInput"/>
						</td>
					</tr>
					<tr>
						<th>거주지</th>
						<td colspan=4>
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
							<select name="district" class="select">
								<option value="">-</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>주민번호</th>
						<td>
							<input id="residentRegistrationNumber1" name="residentRegistrationNumber" class="shortInput"/>
								-
							<input id="residentRegistrationNumber2" name="residentRegistrationNumber" class="shortInput"/>
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
						<th>보증인</th>
						<td><input id="suretyName" name="suretyName" class="middleInput"/></td>
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
								<option value="SILVER">SILVER</option>
								<option value="GOLD">GOLD</option>
								<option value="VIP">VIP</option>
								<option value="VVIP">VVIP</option>
							</select>
						</td>
						<th>신용 등급</th>
						<td>
							<select name="creditRank" class="shortSelect">
								<option value="1급">1</option>
								<option value="2급">2</option>
								<option value="3급">3</option>
								<option value="4급">4</option>
								<option value="5급">5</option>
							</select>
							급
						</td>
					</tr>
				</table>

				<div class="innerSubTitle"><h2>상품 정보</h2></div>
				<table class="inputTable">
					<tr>
						<th>대출 구분</th>
						<td>
							<select name="loanType" class="shortSelect" onchange="changeLoan(this.selectedIndex);">
								<option value="담보대출">담보대출</option>
								<option value="신용대출">신용대출</option>
							</select>
						</td>
						<th>상품명</th>
						<td>
							<select name="loanProductName" class="longSelect">
								<option value="">-</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>담보</th>
						<td>
							<select name="collateral" class="shortSelect">
								<option value="예적금">예적금</option>
								<option value="주택">주택</option>
								<option value="전세자금">전세자금</option>
							</select>
						</td>
						<th>대출 금액</th>
						<td>
							<input type="number" id="loanAmount" name="loanAmount" class="shortInput" type="number" min="0" max="999" step="1"/>
							만원
						</td>
					</tr>
					<tr>
						<th>이자</th>
						<td>
							<!-- <select name="interest" class="shortSelect">
								<option value="simple">복리</option>
								<option value="compound">단리</option>
							</select>  -->
							&nbsp;연
							<input type="number" step="0.1" max="10" id="interestRate" name="interestRate" class="shortInput" />
							%
						</td>
						<th>상환 방법</th>
						<td>
							<select name="repaymentMethod" class="shortSelect">
								<option value="원리금균등분할상환">원리금균등분할상환</option>
								<option value="원금균등분할상환">원금균등분할상환</option>
								<option value="만기일시상환">만기일시상환</option>
							</select>
						</td>
					</tr>
				</table>
				<div class="innerButtonContainer">
					<button type="submit">등록</button>
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
