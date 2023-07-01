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
 			String msg = (String) request.getAttribute("msg");
			CustomerDTO customer = (CustomerDTO) request.getAttribute("customer");
			LoanProductDTO loanProduct = (LoanProductDTO) request.getAttribute("loanProductDTO");
			
			%>
			<form action="${pageContext.request.contextPath}/loan/subscription" method="post" onsubmit="return validateForm()">
				<div class="innerSubTitle">
					<h2>고객 정보 찾기</h2>
					<div class="innerSubTitleRow">주민번호
						<div>
							<input id="identification1" name="identification1" class="identificationInput" value="<%= customer != null ? customer.getIdentification().substring(0, 6) : "" %>" maxlength="6"/>
							-
							<input type="password" id="identification2" id="key" name="identification2" class="identificationInput" value="<%= customer != null ? customer.getIdentification().substring(7, 14) : "" %>" maxlength="7"/>	
						</div>
						<button type="button" id="show" onclick="showIdentification()">SHOW</button>
						<button class="customerDetailButton" id="customerDetailButton" type="button" onclick="return openSearchPopup(this.form)"> 검색 </button>
					</div>
				</div>
				<div id="searchResultMessage"></div>
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
				</table>
				<div class="innerButtonContainer">					
					<button type="submit" id="search">등록</button>
				</div>
			</form>

			<form action="${pageContext.request.contextPath}/loan/repayment" method="post">
				<div id="repaymentMethodSelectTableDiv" style="display: none;">
					<h2 id="repaymentMethodSelectTableTitle">상환 방법</h2>
					<div id="repaymentAmountTotalDiv">

						<p id="repaymentAmountTotalTitle" style="display: none;">총 상환금	<p>
						<p id="repaymentAmountTotal" style="display: none;"><p>
						<button type="submit" id="updateRepaymentDB"
							style="display: none;">확정</button>
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
				<input type="hidden" name="repaymentAmountList" id="repaymentAmountList"> 
				<input type="hidden" name="identificationId" id="identificationId" value="<%= id1 + "-" + id2%>"> 
				<input type="hidden" name="loanProductNameSelect" id="loanProductNameSelect"> 
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
		
		//고객정보
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
		
		

		const identificationInput1 = document.getElementById("identification1");
		const identificationInput2 = document.getElementById("identification2");		

		function openSearchPopup(frm) {
			if (identificationInput1.value !== '' && identificationInput2.value !== '') {
				frm.id="productFind"
				frm.action="/hanaro-ERP-JSP/customer/searchReturn?formId=" + frm.id; 
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
	</script>
</body>
</html>