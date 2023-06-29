<%@page import="DTO.RepaymentMethodDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>상품 가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loan/productSubscription.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/inputTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchResultTable.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js"></script>
</head>
<body>\
	<%@ include file="../../components/header.jsp" %>	
	<%@ page import="java.util.List"%>
	<%@ page import="DTO.LoanContractDTO"%>	
	<%@ page import="util.LoanUtil"%>	\
	<%@ page import="DTO.CustomerDTO" %>\
	<main>
		<%@ include file="../../components/aside.jsp" %>
		<div class="innerContainer">
			<div class="innerTitle"><h1>상품 가입</h1></div>
			<form action="${pageContext.request.contextPath}/loan/subscription" method="post" onsubmit="return validateForm()">
				<div class="innerSubTitle"><h2>고객 정보 찾기</h2></div>
				<table class="inputTable">
 					<% CustomerDTO customer = (CustomerDTO) request.getAttribute("customer"); %>
					<tr>
						<th>이름</th>
						<td><input id="customerName" name="customerName" class="middleInput" value="<%= customer != null ? customer.getCustomerName() : "" %>"/>
						<button type="button" onclick="openSearchPopup()">검색</button></td>						
						<th>전화번호</th>
						<td><%= customer != null ? customer.getPhoneNumber() : "" %>
						<input type="hidden" id="phoneNumber" name="phoneNumber" value="<%= customer != null ? customer.getPhoneNumber() : "" %>"/>
						</td>
					</tr>
					<tr>
						<th>거주지</th>
						<td>
						<input type="hidden" id="address" name="address" value="<%= customer != null ? customer.getAddress() : "" %>">
						<%= customer != null ? customer.getAddress() : "" %>
						</td>
						<th>국가</th>
						<td>
						<input type="hidden" id="country" name="country" value="<%= customer != null ? customer.getCountry() : "" %>">
						<%= customer != null ? customer.getCountry() : "" %>
						</td>
					</tr>
					<tr>
						<th>주민번호</th>
						<td>
						<% 	
							String id1 = "";
							String id2 = "";
							if(customer != null) {
								id1 = customer.getIdentification().substring(0, 6);
								id2 = customer.getIdentification().substring(7,14);
						%>
						<%= id1 + "-" + id2%>
						<input type="hidden" id="identification" name="identification" value="<%= customer != null ? customer.getIdentification() : "" %>">
						<% } %>
						</td>
						<th>직업</th>
						<td>
							<input type="hidden" id="jobCode" name="jobCode" value="<%= customer != null ? customer.getJobName() : "" %>">
							<%= customer != null ? customer.getJobName() : "" %>
						</td>
					</tr>
					<tr>												
						<th>고객 등급</th>
						<td>
						<input type="hidden" id="grade" name="grade" value="<%= customer != null ? customer.getGrade() : "" %>">
						<%= customer != null ? customer.getGrade() : "" %>
						</td>
						<th>신용 등급</th>
						<td>
						<input type="hidden" id="credit" name="credit" value="<%= customer != null ? customer.getCredit() : "" %>">
						<%= customer != null ? customer.getCredit() : "" %>
						</td>
					</tr>
					<tr>
						<th>담당 직원</th>
						<td>
						<input type="hidden" id="employeeName" name="employeeName" value="<%= customer != null ? customer.getEmployeeName() : "" %>">
						<%= customer != null ? customer.getEmployeeName() : "" %>
						</td>
						<th class="office">주거래지점</th>
						<td>
						<input type="hidden" id="bankName" name="bankName" value="<%= customer != null ? customer.getBankName() : "" %>">
						<%= customer != null ? customer.getBankName() : "" %>
						</td>
					</tr>
					<tr>
						<th>보증인</th>
						<td>
						<input type="hidden" id="suretyName" name="suretyName" value="<%= customer != null ? customer.getSuretyName() : "" %>">
						<%= customer != null ? customer.getSuretyName() : "" %>
						</td>
						<th>내부 위험도</th>
						<td> - <button type="button">계산하기</button></td>
					</tr>
				</table>

				<div class="innerSubTitle"><h2>상품 정보 및 가입</h2></div>
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
							<input type="number" step="0.1" max="10" id="loanPeriod" name="loanPeriod" class="shortInput" />
							년
						</td>
						<th> 거치 기간</th>
						<td>
						<input name="gracePeriod" class="shortInput" id="gracePeriod"> 년
					</tr>
					<tr>
						<th>상환 방법</th>
						<td colspan=3>
							<select name="repaymentMethod" class="shortSelect" id="repaymentMethod" onchange="updateTable()">
								<option value="-">-</option>
								<option value="원금만기일시상환">만기일시상환</option>
								<option value="원금균등상환">원금균등분할상환</option>
								<option value="원리금균등상환">원리금균등분할상환</option>
							</select>
						</td>
					</tr>
				</table>
				<div class="innerButtonContainer">					
					<button type="submit" id="search">검색</button>
				</div>
				
				
			<%-- 	<div id="repaymentMethodTableDiv">
					<h3 id="repaymentMethodTableTitle">
				
						<%List<RepaymentMethodDTO> repaymentMethodDTOList = (List<RepaymentMethodDTO>) request.getAttribute("repaymentMethod");

						if (repaymentMethodDTOList != null && repaymentMethodDTOList.size() > 0) {
							System.out.println("jsp repaymentlist size ="+repaymentMethodDTOList.size());							
							String method = repaymentMethodDTOList.get(0).getMethod();
							if (method.equals("만기")) {
								out.println("원금만기일시상환");
							} 
							else if (method.contains("원금균등")) {
								out.println("원금균등상환");
							} 
							else {
								out.println("원리금균등상환");
							}
						%>
					</h3>
					<table class="searchTable" id="repaymentMethodTable">
						<tr>
							<th>회차</th>
							<th>상환금</th>
							<th>납입 원금</th>
							<th>이자</th>
							<th>납입원금누계</th>
							<th>잔금</th>
						</tr>
								
						<%
							LoanUtil loanUtil = new LoanUtil();
							if (repaymentMethodDTOList != null && !repaymentMethodDTOList.isEmpty()) {
								for (RepaymentMethodDTO dto : repaymentMethodDTOList) {
									%>
									<tr class="searchResultRow" >
										<td><%= dto.getTimes()%></td>
										<td><%= loanUtil.formatCurrency(dto.getRepaymentAmount())%></td>									
										<td><%= loanUtil.formatCurrency(dto.getPrincipalPayment())%></td>
										<td><%= loanUtil.formatCurrency(dto.getInterest())%></td>
										<td><%= loanUtil.formatCurrency(dto.getCumulativePrincipalPayment())%></td>
										<td><%= loanUtil.formatCurrency(dto.getBalance())%></td>
									</tr>
									<%
								}
							}
						}
						else{
							System.out.println("jsp repaymentlist null");
						}
						%>
				</table>
				</div> --%>
				
				<div id="repaymentMethodSelectTableDiv">
					<h3 id="repaymentMethodSelectTableTitle">						
					상환 방법
					</h3>
					<table class="searchTable" id="repaymentMethodSelectTable">
						<tr>
							<th>회차</th>
							<th>상환금</th>
							<th>납입 원금</th>
							<th>이자</th>
							<th>납입원금누계</th>
							<th>잔금</th>
						</tr>
				</table>
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
	<script>
	    function openSearchPopup() {
	    	var firstTd = document.getElementById("customerName"); // customerName 필드를 가리키는 변수 firstTd
	    	if(firstTd.value !== '')
		    	window.open("/hanaro-ERP-JSP/customerSearch?name=" + firstTd.value + "&pageId=" + 2, "_blank", "width=1000,height=200");
	    }
	</script>
</body>
</html>
