<%@page import="DTO.RepaymentMethodDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>상품 가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchLayout.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loan/productSubscription.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/inputTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchResultTable.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js"></script>
</head>
<body>
	<%@ include file="../../components/header.jsp" %>	
	<%@ page import="java.util.List"%>
	<%@ page import="DTO.LoanContractDTO"%>
	<%@ page import="util.LoanUtil"%>
	<%@ page import="DTO.CustomerDTO" %>
	<%@ page import="DAO.CustomerDAO" %>
	<%@ page import="DTO.CreditScoringDTO" %>
	<%@ page import="DTO.LoanProductDTO" %>
	<%@ page import="Service.CreditService" %>
	<main>
		<%@ include file="../../components/aside.jsp" %>
		
		<div class="innerContainer">
			<div class="innerTitle"><h1>상품 가입</h1></div>
			<% 
 			CustomerDTO customer = (CustomerDTO) request.getAttribute("customer");
			LoanProductDTO loanProduct = (LoanProductDTO) request.getAttribute("loanProductDTO");
			%>
			<form action="${pageContext.request.contextPath}/loan/subscription" method="post" onsubmit="return validateForm()">
				<div class="innerSubTitle"><h2>고객 정보 찾기</h2></div>
				<div class="innerSubTitleRow">주민번호
				<input id="identification1" name="identification1" class="middleInput" value="<%= customer != null ? customer.getIdentification().substring(0, 6) : "" %>"/>
				-
				<input id="identification2" name="identification2" class="middleInput" maxlength="7" value="<%= customer != null ? customer.getIdentification().substring(7, 14) : "" %>"/>
				<button id="customerDetailButton" type="button" onclick="openSearchPopup()"> 검색 </button>
				</div>
				<div id="searchResultMessage"></div>
				<table class="inputTable">
					<tr>
						<th>이름</th>
						<td>
						<%= customer != null ? customer.getCustomerName() : "" %>
						<input type="hidden" id="customerName" name="customerName" value="<%= customer != null ? customer.getCustomerName() : "" %>"/>
						</td>									
						<th>전화번호</th>
						<td>
						<%= customer != null ? customer.getPhoneNumber() : "" %>
						<input type="hidden" id="phoneNumber" name="phoneNumber" value="<%= customer != null ? customer.getPhoneNumber() : "" %>"/>
						</td>					
					</tr>
					<tr>
						<th>국가</th>
						<td>
						<input type="hidden" id="country" name="country" value="<%= customer != null ? customer.getCountry() : "" %>">
						<%= customer != null ? customer.getCountry() : "" %>
						<th>거주지</th>
						<td>
						<input type="hidden" id="address" name="address" value="<%= customer != null ? customer.getAddress() : "" %>">
						<%= customer != null ? customer.getAddress() : "" %>
						</td>
					</tr>
					<tr>
						<th>직업</th>
						<td>
							<input type="hidden" id="jobCode" name="jobCode" value="<%= customer != null ? customer.getJobName() : "" %>">
							<%= customer != null ? customer.getJobName() : "" %>
						</td>
						<th>고객 등급</th>
						<td>
						<input type="hidden" id="grade" name="grade" value="<%= customer != null ? customer.getGrade() : "" %>">
						<%= customer != null ? customer.getGrade() : "" %>
						</td>
					</tr>
					<tr>												
						<th>외부 신용 등급</th>
						<td>
						<input type="hidden" id="credit" name="credit" value="<%= customer != null ? customer.getCredit() : "" %>">
						<%= customer != null ? customer.getCredit() : "" %>
						</td>
						<th>보증인</th>
						<td>
						<input type="hidden" id="suretyName" name="suretyName" value="<%= customer != null ? customer.getSuretyName() : "" %>">
						<%= customer != null ? customer.getSuretyName() : "" %>
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
				</table>
				<div class="innerSubTitle" id="riskResult">
					<h2>내부 위험도 결과</h2><button id="customerDetailButton" name="close" type="button" onclick="return riskCalcFunc(this.form);">계산하기</button>
					<input id="checkOpen" name="isOpen" value="close"></input>
				</div>
				<div class="innerInformation" id="customerDetailInformation">
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">내부 위험도</div>
						<div id="innerRisk"></div>
					</div>
					<div class="innerInformationRow">
						<div class="innerInformationRowTitle">이자율 적용</div>
						<div id="interestRate2"></div>
					<div class="innerInformationRowTitle">대출기간 적용</div>
						<div id="loanPeriod2"></div>
					</div>				
				</div>						
				<div class="innerSubTitle"><h2>상품 정보 및 가입</h2></div>
				<table class="inputTable">
					<tr>
						<th>대출 구분</th>
						<td><select name="loanType" class="shortSelect"
							onchange="changeLoan(this.selectedIndex);">
								<option value="담보대출">담보대출</option>
								<option value="신용대출">신용대출</option>
						</select></td>
						<th>상품명</th>
						<td><select name="loanProductName" class="longSelect" 
							onchange="changeLoanProductName(this.selectedIndex);">
								<option value="">-</option>
						</select></td>
					</tr>
					<tr>
						<th>담보</th>
						<td><input name="collateral" class="middleInput"></td>
						<th>대출 금액</th>
						<td><input type="number" id="loanAmount" name="loanAmount"
							class="shortInput" type="number" min="0" max="5000" step="1" />
							만원</td>
					</tr>
					<tr>
						<th>이자</th>
						<td>
							&nbsp;연
							<input type="number" step="0.1" max="10" id="interestRate" name="interestRate" class="shortInput" />
							%
						</td>
						<th>대출기간</th>
						<td>
						<input type="number" step="0.1" max="10" id="loanPeriod" name="loanPeriod" class="shortInput" />
						년
						</td>
					</tr>
					<tr>
					 	<th> 거치 기간</th>
						<td>
						<input name="gracePeriod" class="shortInput" id="gracePeriod"> 년
						<th>상환 방법</th>
						<td colspan=3><select name="repaymentMethod"
							class="shortSelect" id="repaymentMethod" onchange="updateTable()">
								<option value="-">-</option>
								<option value="원금만기일시상환">만기일시상환</option>
								<option value="원금균등상환">원금균등분할상환</option>
								<option value="원리금균등상환">원리금균등분할상환</option>
							</select>
						</td>
					</tr>
				</table>
				<div class="innerButtonContainer">					
					<button type="submit" id="search">등록</button>
				</div>
			</form>

			<form action="${pageContext.request.contextPath}/loan/repayment" method="post">
				<%    
                     String id1 = "";
                     String id2 = "";
                     if(customer != null) {
                        id1 = customer.getIdentification().substring(0, 6);
                        id2 = customer.getIdentification().substring(7,14);
                     }
                %> 
				
				<input type="hidden" name="repaymentAmountList" id="repaymentAmountList"> 
				<input type="hidden" name="identificationId" id="identificationId" value="<%= id1 + "-" + id2%>"> 
				<input type="hidden" name="loanProductNameSelect" id="loanProductNameSelect"> 
				<input type="submit" value="상환 방법 확정하기">
				
				<div id="repaymentMethodSelectTableDiv" style="display: none;">
					<h2 id="repaymentMethodSelectTableTitle">상환 방법</h2>
					<div id="repaymentAmountTotalDiv">
						<p id="repaymentAmountTotalTitle" style="display: none;">총 상환금 <p>
						<p id="repaymentAmountTotal" style="display: none;"><p>
					</div>
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
		
		//고객정보
		const customerDetailSelect = document.getElementById('customerDetailButton');
		const customerDetailInformation = document.getElementById('customerDetailInformation');
		const checkOpen = document.getElementById('checkOpen');
		checkOpen.style.display = 'none'
		customerDetailInformation.style.display = 'none'; // 초기에 숨김 상태로 설정
		
		const customerNameInformation = document.getElementById('innerSubTitleRow');
		
		function revealDetail() {
			checkOpen.setAttribute('value', 'open');
			customerDetailInformation.style.display = 'block';
		}
		function concealDetail() {
			checkOpen.setAttribute('value', 'close');
			customerDetailInformation.style.display = 'none';
		}
		
		function riskCalcFunc() {
			//고객정보
			const customerDetailInformation = document.getElementById('customerDetailInformation');
			<%	
	    	CustomerDAO customerDAO = new CustomerDAO();
			CreditScoringDTO creditScoringDTO = new CreditScoringDTO();
			CreditService creditService = new CreditService();
			String addRate = "";
			String addPeriod = "최대 N";
			
			int cId = 0; int eId = 0;
			if(customer != null) {
				cId = customerDAO.getCustomerIdByCustomerName(customer.getCustomerName());
				eId = customerDAO.getCustomerIdByCustomerName(customer.getSuretyName());
				
				if(eId != -1) {
					creditScoringDTO.setCustomerId(cId); //고객정보
			    	creditScoringDTO.setGuarantorId(eId); //보증인정보

					
			    	creditService.setCreditScore(creditScoringDTO);
				
					//결과에 따른 이자율이랑 대출기간 다르게 하기
					String score = (creditService.getCreditScore()).substring(0, 1);
				
					switch(score) {
						case "1" :
							addRate = "+0.2%";
							addPeriod += "+1년"; //1년
							break;
						case "2" :
							addRate = "0.1%";
							addPeriod += "+1년";
							break;
						case "4" :
							addRate = "-0.1%";
							break;
						case "5" :
							addRate = "-0.2%";
							break;
						case "6" :
							addRate = "-0.3%";
							addPeriod += "-1년";
							break;
					}
				}
			}
			%>
			
			<% if(eId > 0) { %>
					revealDetail();
					innerRisk.innerHTML = "<%= creditService.getCreditScore() %>";				
					interestRate2.innerHTML = "<%= addRate %>";
					loanPeriod2.innerHTML = "<%= addPeriod %>";
			<% } else { %>
					concealDetail();
					alert("데이터가 부족합니다.");
			<% } %>
		}
		
		function openSearchPopup() {
	    	var firstTd = document.getElementById("identification1"); // customerName 필드를 가리키는 변수 firstTd
	    	var secondTd = document.getElementById("identification2");
	    	if(firstTd.value !== '') {
	    		var identification = 
		    	window.open("/hanaro-ERP-JSP/customerSearch?id1=" + firstTd.value + "&id2=" + secondTd.value + "&pageId=" + 2, "_blank", "width=1000,height=200");
			}
		}		
	</script>
</body>
</html>
