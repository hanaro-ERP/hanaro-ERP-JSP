<%@page import="DTO.RepaymentMethodDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>하나로여신관리시스템 - 계좌 개설</title>
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
			<div class="innerTitle"><h1>계좌 개설</h1></div>
			<% 
			CustomerDTO customer = (CustomerDTO) request.getAttribute("customer");
			LoanProductDTO loanProduct = (LoanProductDTO) request.getAttribute("loanProductDTO");
			%>
			<form action="${pageContext.request.contextPath}/deposit/creation" method="post" onsubmit="return validateForm()">
				<div class="innerSubTitle">
					<h2>고객 정보 찾기</h2>
					<div id="findById" class="innerSubTitleRow">주민번호
						<div>
							<input id="identification1" name="identification1" class="shortInput" value="<%= customer != null ? customer.getIdentification().substring(0, 6) : "" %>" maxlength="6"/>
							-
							<input type="password" id="identification2" id="key" name="identification2" class="shortInput" value="<%= customer != null ? customer.getIdentification().substring(7, 14) : "" %>" maxlength="7"/>	
						</div>
						<button type="button" id="show" onclick="showIdentification()">SHOW</button>
						<button class="customerDetailButton" id="customerDetailButton" type="button" onclick="return openSearchPopup(this.form)"> 검색 </button>
					</div>
					<div id="findResult">
						<% if (customer != null) { %>
					       <%= customer.getCustomerName() %> 님의 검색결과입니다.
					   <% } %>
						<input name="guarantor" id="searchResultMessage" type="hidden" value="<%= customer != null ? customer.getCustomerName() : "" %>">
						<button class="customerDetailButton" id="customerDetailButton" type="button" onclick="reSearch()"> 재검색 </button>	
						<input style="display:none" name="customerId" value="<%= customer != null ? customer.getCustomerId() : "" %>"/>					
					</div>
				</div>
				<%    
                     String id1 = "";
                     String id2 = "";
                     if(customer != null) {
                        id1 = customer.getIdentification().substring(0, 6);
                        id2 = customer.getIdentification().substring(7,14);
                     }
                %> 
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
						<input type="hidden" id="suretyName" name="suretyName" value="<%= customer != null ? customer.getGuarantor() : "" %>">
						<%= customer != null && customer.getGuarantor() != null ? customer.getGuarantor() : "" %>
						</td>
					</tr>
					<tr>
						<th>담당 직원</th>
						<td>
						<input type="hidden" id="employeeName" name="employeeName" value="<%= customer != null ? customer.getEmployeeName() : "" %>">
						<%= customer != null ? customer.getEmployeeName() : "" %>
						</td>
						<th class="office">주거래지점</th>
						<td><%= customer != null ? customer.getBankName() : "" %>
						<input type="hidden" id="bankName" name="bankName" value="<%= customer != null ? customer.getBankName() : "" %>">
						</td>
					</tr>
				</table>
				<div class="innerSubTitle2"><h2>계좌 정보</h2></div>
				<table class="inputTable">
					<tr>
						<th>대출 구분</th>
						<td>
							<select name="depositType" class="shortSelect">
								<option value="예금">예금</option>
								<option value="적금">적금</option>
							</select>
						</td>
						<th>계좌 번호</th>
						<td>
							<input type="text" id="accountNumber" name="accountNumber"/>
							<button id="accountCreationButton" type="button" onclick="generateAccountNumber()">생성</button>
						</td>
					</tr>
				</table>
				<div class="innerButtonContainer">					
					<button type="submit" id="search">확인</button>
				</div>
			</form>
		</div>
	</main>
	<script>
		generateMenu('deposit', 'depositCreation');
	</script>
	<script src="${pageContext.request.contextPath}/js/components/inputTable.js"></script>
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js"></script>
	<script src="${pageContext.request.contextPath}/js/loan/productSubscription.js"></script>
	<script>
		<%
		String msg = (String)request.getAttribute("msg");
		if (msg != null) {
			%>
			alert("가입되지 않은 주민등록번호입니다.")
			<%
		}
		%>
		
		function generateAccountNumber() {
			const accountNumberElement = document.getElementById("accountNumber");
			const randomNumber = generateRandomNumber();
			accountNumberElement.setAttribute("value", randomNumber);
		}

		function generateRandomNumber() {
			let randomNumber = "0";
			for (let i = 0; i < 13; i++) {

			randomNumber += Math.floor(Math.random() * 10);
			if (i === 1 || i === 7){
				randomNumber += "-";
			}
		}
			return randomNumber;
		}

		const identificationInput1 = document.getElementById("identification1");
		const identificationInput2 = document.getElementById("identification2");		

		function openSearchPopup(frm) {
			if (identificationInput1.value !== '' && identificationInput2.value !== '') {
				frm.id="depositFind"
				frm.action="/hanaro-ERP-JSP/deposit/searchReturn?formId=" + frm.id;
			    frm.submit();
			    return true;
			}
			else
				alert("주민번호를 입력해주세요.");
		}
		
		var show = document.getElementById("show");
		function showIdentification() {
		    if(identificationInput2.type == "text") {
		    	identificationInput2.type = "password";
		    	show.innerText = "SHOW";
		    }
		    else {
		    	identificationInput2.type = "text";
		    	show.innerText = "HIDE";	
		    }
		}
		const findById = document.getElementById("findById");
		const findResult = document.getElementById("findResult");
		const gurantorName = document.getElementById("searchResultMessage");
		findResult.style.display = 'none';
		
	    if (gurantorName.value !== "") {
	    	findById.style.display = 'none';
	    	findResult.style.display = 'flex';
	    }
	    function reSearch() {
	    	findById.style.display='flex';
	    	findResult.style.display = 'none';
	    }
	</script>
</body>
</html>