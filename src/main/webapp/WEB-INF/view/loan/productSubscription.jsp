<%@page import="DTO.RepaymentMethodDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>상품 가입: 하나로여신관리시스템</title>
<link rel="icon" href="${pageContext.request.contextPath}/public/images/favicon.svg">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchLayout.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loan/productSubscription.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/inputTable.css?ver=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/searchResultTable.css?ver=1">
<script src="${pageContext.request.contextPath}/js/components/aside.js"></script>
</head>
<body onload="goToController()">
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
			List<LoanProductDTO> loanProductList = (List<LoanProductDTO>) request.getAttribute("loanProductList");
			String mod = request.getQueryString();
			%>
			<form action="${pageContext.request.contextPath}/loan/subscription" method="post" onsubmit="return validateForm()">
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
				<div class="innerSubTitle2" id="riskResult">
					<h2 id="riskResultTitle">내부 신용 등급</h2><button class="customerDetailButton" name="close" type="button" onclick="return riskCalcFunc(this.form);">계산하기</button>
					<input id="checkOpen" name="isOpen" value="close"></input>
				</div>
				<div class="innerInformation" id="customerDetailInformation">
					<div class="innerInformationRow" id="riskResultTable">
						<div>
							<div class="innerInformationRowTitle">상품명</div>
							<div id="loanTitle"></div>
						</div>
						<div>
							<div class="innerInformationRowTitle">이자율</div>
							<div id="interestRate3"></div>
						</div>
						<div>
							<div class="innerInformationRowTitle">대출 기간</div>
							<div id="loanPeriod3"></div>		
						</div>			
					</div>
					<div class="innerInformationRow" id="riskResultTable">
						<div>
							<div class="innerInformationRowTitle">내부 신용 등급</div>
							<div id="innerRisk"></div>
						</div>
						<div>
							<div class="innerInformationRowTitle">이자율 적용</div>
							<div id="interestRate2"></div>
						</div>
						<div>
							<div class="innerInformationRowTitle">대출 기간 적용</div>
							<div id="loanPeriod2"></div>
						</div>
					</div>		
				</div>						
				<div class="innerSubTitle2"><h2>상품 정보 및 가입</h2></div>
				<table class="inputTable">
					<tr>
						<th>대출 구분</th>
						<td>    
						<select name="loanType" class="shortSelect" onchange="changeLoan(this.selectedIndex);">
					        <option value="">-</option>
					        <option value="담보대출">담보대출</option>
							<option value="신용대출">신용대출</option> 
					    </select>
						</td>
						<th>상품명</th>
						<td>
					    <select id="selectedProduct" name="loanProductName" class="longSelect" onchange="selectedLoan(this.selectedIndex)">
					        <option value="">-</option>
					        <% if(loanProductList != null && !loanProductList.isEmpty()) {
				                for (LoanProductDTO loan : loanProductList) { %>
				                    <option value="<%= loan.getLoanName() %>"><%= loan.getLoanName() %></option>
				                <% }
				            } else { %>
				                <option value="">No loan types available</option>
				            <% } %>
					    </select>
						</td>
					</tr>
					<tr>
						<th>담보</th>
						<td><input name="collateral" class="middleInput"></td>
						<th>대출 금액</th>
						<td><input type="number" id="loanAmount" name="loanAmount"
							class="shortInput" type="number" min="0" max="5000" step="100" />
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
					 	<th>거치 기간</th>
						<td>
						<input name="gracePeriod" class="shortInput" id="gracePeriod"> 년
						<th>상환 방법</th>
						<td colspan=3><select name="repaymentMethod"
							class="shortSelect" id="repaymentMethod" >
								<option value="-">-</option>
								<option value="원금만기일시상환">원금만기일시상환</option>
								<option value="원금균등상환">원금균등상환</option>
								<option value="원리금균등상환">원리금균등상환</option>
							</select>
						</td>
					</tr>
				</table>
				<div class="innerButtonContainer">					
					<button type="submit" id="search">등록</button>
				</div>
				<div id="repaymentMethodSelectTableDiv" style="display: none;">
					<h2 id="repaymentMethodSelectTableTitle">상환 방법</h2>
					<div id="repaymentAmountTotalDiv">

						<p id="repaymentAmountTotalTitle" style="display: none;">총 상환금	<p>
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
				<input type="hidden" name="repaymentAmountList" id="repaymentAmountList"> 
				<input type="hidden" name="identificationId" id="identificationId" value="<%= id1 + "-" + id2%>"> 
				<input type="hidden" name="loanProductNameSelect" id="loanProductNameSelect"> 
			</form>
			<div id="test"></div>
		</div>
	</main>
	<button class="upToButton" onclick="scrollToTop()">
		<svg width="30" height="20" viewBox="0 0 12 10" fill="none" xmlns="http://www.w3.org/2000/svg">
			<path d="M6 0L0 6L1.41 7.41L6 2.83L10.59 7.41L12 6L6 0Z" fill="#323232"/>
		</svg>
	</button>
	<script>
		generateMenu('loan', 'productSubscription');
	</script>
	<script src="${pageContext.request.contextPath}/js/components/inputTable.js"></script>
	<script src="${pageContext.request.contextPath}/js/components/searchLayout.js"></script>
	<script src="${pageContext.request.contextPath}/js/loan/productSubscription.js"></script>
	<script>
		const interestRateInput = document.getElementById("interestRate3");
		const periodInput = document.getElementById("loanPeriod3");
		const selectedProductName = document.getElementById("loanTitle");
		
		function goToController() {
	    	window.location.href = "${pageContext.request.contextPath}/loanProduct/list"; // 서블릿의 URL로 이동합니다.
	    }
		
		<% 
		if(mod != "" && loanProductList != null) {
		%>
			document.body.removeAttribute('onload');
		<%
		}
		%>

		
	    var loanProductArray = [];

	    <% if(loanProductList != null && !loanProductList.isEmpty()) {
	        for (LoanProductDTO loan : loanProductList) { %>
		        <% if (loan.getLoanType().equals("담보대출")) { %>
				        if (!loanProductArray[1]) {
			                loanProductArray[1] = ["-"];
			            }
			            loanProductArray[1].push("<%= loan.getLoanName() %>");
		         <% } else if (loan.getLoanType().equals("신용대출")) { %>
			            if (!loanProductArray[2]) {
			                loanProductArray[2] = ["-"];
			            }
			            loanProductArray[2].push("<%= loan.getLoanName() %>");
			     <% }
			}
		}%>
		
		function changeLoan(add) {
		    const selectElement = document.getElementsByName("loanProductName")[0];
		    
		    // 옵션 메뉴 삭제
		    selectElement.innerHTML = "";

		    // 옵션 박스 추가
		    for (let i = 0; i < loanProductArray[add].length; i++) {
		        let option = document.createElement("option");
		        option.value = loanProductArray[add][i];
		        option.text = loanProductArray[add][i];
		        selectElement.appendChild(option);
		    }

		    var collateralField = document.getElementsByName("collateral")[0];

		    // selectedIndex가 2 (신용대출)이면 담보 필드를 비활성화하고 값을 초기화합니다.
		    if (add === 2) {
		        collateralField.disabled = true;
		        collateralField.value = "";
		        collateralField.style.backgroundColor = "#E5E8EB";
		    } else {
		        collateralField.disabled = false;
		        // 색상 변경
		        collateralField.style.backgroundColor = "#fff";
		    }
		}

		var loanMinRate = "";
		var loanManRate = "";
		var loanMinPeriod = "";
		var loanMaxPeriod = "";
		var loanName = "";
		const test1 = document.getElementById("test");

		//alert(selectedProduct);
		function selectedLoan(add) {
			var selectedValue = document.getElementById("selectedProduct");
			
			<% if(loanProductList != null && !loanProductList.isEmpty()) { %>
		      <%  String flag = "false"; %>
			  <%  for (LoanProductDTO loan : loanProductList) { %>
		      		if (selectedValue.value === "<%= loan.getLoanName() %>") {
		      			loanMinRate = <%= String.valueOf(loan.getMinRate()) %>;
		      			loanMinPeriod = <%= String.valueOf(loan.getMinDuration()) %>;
		      			loanMaxRate = <%= String.valueOf(loan.getMaxRate()) %>; // Convert loanRate to a string
		                loanMaxPeriod = <%= String.valueOf(loan.getMaxDuration()) %>; // Convert loanPeriod to a string
		                loanName = "<%= loan.getLoanName() %>"; 
						<% flag = "true"; %>
		            }
		      		
		      <%  }
			}%>
			selectedProductName.innerHTML = loanName;
			interestRateInput.innerHTML = loanMinRate + "% ~ " + loanMaxRate + "%";
			periodInput.innerHTML = loanMinPeriod + "년 ~ " + loanMaxPeriod + "년";
		}
		changeLoan(0);
		
		function riskCalcFunc() {
			//고객정보
			const customerDetailInformation = document.getElementById('customerDetailInformation');
			<%	
	    	CustomerDAO customerDAO = new CustomerDAO();
			CreditScoringDTO creditScoringDTO = new CreditScoringDTO();
			CreditService creditService = new CreditService();
			String addRate = "+0%";
			String addPeriod = "최대 N";
			
			int cId = 0; int eId = 0;
			String cName = "";
			if(customer != null) {
				cName = customer.getCustomerName();
				cId = customerDAO.getCustomerIdByCustomerName(customer.getCustomerName());
				eId = customerDAO.getCustomerIdByCustomerName(customer.getGuarantor());
				
				creditScoringDTO.setCustomerId(cId); //고객정보
			    creditScoringDTO.setGuarantorId(eId); //보증인정보

			    creditService.setCreditScore(creditScoringDTO);
				
				//결과에 따른 이자율이랑 대출기간 다르게 하기
				String score = (creditService.getCreditScore()).substring(0, 1);
			
				switch(score) {
					case "1" :
						addRate = "+0.3%";
						addPeriod += "+1년"; //1년
						break;
					case "2" :
						addRate = "0.2%";
						addPeriod += "+1년";
						break;
					case "3" :
					case "4" :
						addPeriod = "+1년";
						break;
					case "6" :
					case "7" :
						addRate = "-0.1%";
						break;
					case "8" :
						addRate = "-0.1%";
						addPeriod += "-1년";
						break;
					case "9" :
						addRate = "-0.2%";
						addPeriod += "-1년";
						break;
					case "10" :
						addRate = "-0.3%";
						addPeriod += "-1년";
						break;
				}
			}
			%>
			
			<% if(cName != null && !cName.equals("")) { %>
					revealDetail();
					innerRisk.innerHTML = "<%= creditService.getCreditScore() %>";				
					interestRate2.innerHTML = "<%= addRate %>";
					loanPeriod2.innerHTML = "<%= addPeriod %>";
			<% } else { %>
					concealDetail();
					alert("먼저 가입할 고객을 찾아주세요.");
			<% } %>
		}


	</script>
	<script>
		<%
		String msg = (String)request.getAttribute("msg");
		if (msg != null) {
			%>
			alert("가입되지 않은 주민등록번호입니다.")
			<%
		}
		%>
		
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
				frm.action="/loan/searchReturn?formId=" + frm.id; 
			    frm.submit();
			    return true;
			}
			else
				alert("가입할 고객의 주민번호를 입력해주세요.");
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
	    	findById.style.display = 'flex';
	    	findResult.style.display = 'none';
	    }
	</script>
</body>
</html>
