<%@page import="DTO.RepaymentMethodDTO"%>
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
<script src="${pageContext.request.contextPath}/js/components/aside.js"></script>
</head>
<body>
	<%@ include file="../../components/header.jsp" %>	
	<%@ page import="java.util.List"%>
	<%@ page import="DTO.LoanContractDTO"%>	
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
						<th>보증인</th>
						<td><input id="suretyName" name="suretyName" class="middleInput"/></td>
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
						<th>신용 등급</th>
						<td>
							<select name="creditRank" class="shortSelect">
								<option value="1등급">1</option>
								<option value="2등급">2</option>
								<option value="3등급">3</option>
								<option value="4등급">4</option>
								<option value="5등급">5</option>
							</select>
							급
						</td>
						<th>내부 위험도</th>
						<td> - <button type="button">계산하기</button></td>
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
							<input name="collateral" class="middleInput">
							<!-- <select name="collateral" class="shortSelect">
								<option value="예적금">예적금</option>
								<option value="주택">주택</option>
								<option value="전세자금">전세자금</option>
							</select>-->
						</td>
						<th>대출 금액</th>
						<td>
							<input type="number" id="loanAmount" name="loanAmount" class="shortInput" type="number" min="0" max="5000" step="1"/>
							만원
						</td>
					</tr>
					<tr>
						<th>이자 / 대출기간</th>
						<td>
							&nbsp;연
							<input type="number" step="0.1" max="10" id="interestRate" name="interestRate" class="shortInput" />
							%
							&nbsp; | &nbsp;&nbsp;
							<input type="number" step="0.1" max="10" id="interestRate" name="interestRate" class="shortInput" />
							년
						</td>
						<th> 거치 기간</th>
						<td>
						<input name="gracePeriod" class="shortInput"> 년
						
					</tr>
					<tr>
						<th>상환 방법</th>
						<td colspan=3>
							<select name="repaymentMethod" class="shortSelect">
								<option value="원리금균등분할상환">원리금균등분할상환</option>
								<option value="원금균등분할상환">원금균등분할상환</option>
								<option value="만기일시상환">만기일시상환</option>
							</select>
						</td>
					</tr>
				</table>
				<div class="innerButtonContainer">					
	<!-- 				<button id="repaymentDetailButton"> 상환 방법 <br>자세히 보기 </button> -->
					<button type="submit" id="search">검색</button>
				</div>
				
				
				<div id="equalRepaymentOfPrincipal">
					<%-- <h3>
				
						<%List<RepaymentMethodDTO> repaymentMethodDTOList = (List<RepaymentMethodDTO>) request.getAttribute("repaymentMethod");

						
						String method = repaymentMethodDTOList.get(0).getMethod();
						if (method.equals("원금만기")) {
							out.println("만기일시상환");
						} 
						else if (method.contains("원금균등")) {
							out.println("원금균등상환");
						} 
						else {
							out.println("원리금균등상환");
						}
						%>
					</h3>
					<table>
						<tr>
							<th>회차</th>
							<th>상환금</th>
							<th>납입 원금</th>
							<th>이자</th>
							<th>납입원금누계</th>
							<th>잔금</th>
						</tr>
								
						<%if (repaymentMethodDTOList != null && !repaymentMethodDTOList.isEmpty()) {
							for (RepaymentMethodDTO dto : repaymentMethodDTOList) {
								%>
								<tr class="searchResultRow" >
									<td><%= dto.getTimes()%></td>
									<td><%= dto.getRepaymentAmount()%></td>
									<td><%= dto.getPrincipalPayment()%></td>
									<td><%= dto.getInterest()%></td>
									<td><%= dto.getCumulativePrincipalPayment() %></td>
									<td><%= dto.getBalance()%></td>									
								</tr>
								<%
							}
						}
						%>
				</table> --%>
				</div>
				
			</form>
		</div>
	</main>
	<script>
		generateMenu('loan', 'productSubscription');		
	</script>
	<script src="${pageContext.request.contextPath}/js/components/inputTable.js"></script>
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js"></script>
	<script src="${pageContext.request.contextPath}/js/loan/productSubscription.js"></script>
</body>
</html>
