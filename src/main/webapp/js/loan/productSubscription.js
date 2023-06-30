var loanProduct = new Array();
loanProduct[0] = new Array(
	"-",
	"하나햇살론뱅크",
	"하나예금담보대출",
	"하나원큐주택담보대출"
);
loanProduct[1] = new Array(
	"-",
	"닥터클럽대출 - 골드",
	"로이어클럽대출",
	"하나 수의사클럽대출",
	"공무원클럽대출",
	"프리미엄 직장인론",
	"하나 새희망홀씨"
);

function changeLoan(add) {
	const selectElement = document.getElementsByName("loanProductName")[0];

	/* 옵션메뉴삭제 */
	for (let i = selectElement.length - 1; i >= 0; i--) {
		selectElement.options[i] = null;
	}
	/* 옵션박스추가 */
	for (let i = 0; i < loanProduct[add].length; i++) {
		selectElement.options[i] = new Option(loanProduct[add][i], loanProduct[add][i]);
	}

	var collateralField = document.getElementsByName("collateral")[0];

	// selectedIndex가 2 (신용대출)이면 담보 필드를 비활성화하고 값을 초기화합니다.
	if (add === 1) {
		collateralField.disabled = true;
		collateralField.value = "-";

		collateralField.style.backgroundColor = "#E5E8EB";
	} else {
		collateralField.disabled = false;

		// 색상 변경
		collateralField.style.backgroundColor = "#fff";
	}
}

var repaymentAmountTotal = 0;
var repaymentAmountArray = [];

function updateTable() {
	var repaymentMethod = document.getElementById("repaymentMethod");	// 상환 방법
	var loanPeriod = document.getElementById("loanPeriod");	// 기간
	var loanAmount = document.getElementById("loanAmount");	// 원금
	var interestRate = document.getElementById("interestRate");	// 연 이자율
	var gracePeriod = document.getElementById("gracePeriod");	// 거치 기간

	var repaymentMethodValue = repaymentMethod.value;
	var loanPeriodValue = loanPeriod.value * 12;	// 개월 수
	var loanAmountValue = loanAmount.value * 10000;
	var interestRateValue = interestRate.value / 12 * 0.01;	// 월 이자율로 변경
	var gracePeriodValue = gracePeriod.value * 12;	// 거치 기간 개월 수

	var repaymentMethodSelectTableTitle = document.getElementById("repaymentMethodSelectTableTitle");
	repaymentMethodSelectTableTitle.innerText = repaymentMethodValue;

	var repaymentAmount = 0;	// 상환금 = 상환한 원금 + 이자
	var principalPayment = 0;	// 납입 원금
	var interest = 0;	// 월 납부 이자
	var cumulativePrincipalPayment = 0;	// 납입원금누계
	var balance = loanAmountValue;	// 남은 대출 원금

	var repaymentMethodTable = document.getElementById("repaymentMethodSelectTable");
	removeTableRow(repaymentMethodTable);

	// 상환 방법에 따라 다르게 계산
	if (repaymentMethodValue.includes("만기")) {	// 원금만기일시상환
		interest = loanAmountValue * interestRateValue ;	// 이자
		principalPayment = 0;	// 납입 원금
		cumulativePrincipalPayment = 0; 	// 납입원금누계
		balance = loanAmountValue; // 남은 대출 원금

		for (var month = 1; month < loanPeriodValue; month++) {
			repaymentAmount += interest;	// 상환금
			repaymentAmountTotal += repaymentAmount;
			makeRowCell(repaymentMethodTable, month, parseInt(repaymentAmount), parseInt(principalPayment), parseInt(interest), parseInt(cumulativePrincipalPayment), parseInt(balance));
		}		
		// 마지막 달
		principalPayment = loanAmountValue;	// 납입 원금
		cumulativePrincipalPayment = loanAmountValue; 	// 납입원금누계
		balance = 0; // 남은 대출 원금
		repaymentAmount += principalPayment + interest;	// 상환금
		repaymentAmountTotal += repaymentAmount;
		makeRowCell(repaymentMethodTable, month, parseInt(repaymentAmount), parseInt(principalPayment), parseInt(interest), parseInt(cumulativePrincipalPayment), parseInt(balance));
	}

	else if (repaymentMethodValue.includes("원금균등")) { // 원금균등상환
		for (var month = 1; month <= loanPeriodValue; month++) {
			// 거치 기간 있는 경우
			if (gracePeriodValue > 0 && month <= gracePeriodValue) {
				interest = loanAmountValue * interestRateValue;
				repaymentAmount = interest;	
				repaymentAmountTotal += repaymentAmount;
			}
			// 거치 기간 없는 경우
			else {
				principalPayment = loanAmountValue / (loanPeriodValue-gracePeriodValue);	// 납입 원금
				interest = balance * interestRateValue;	// 이자
				repaymentAmount = interest + principalPayment;	// 상환금 = 이자 + 납입 원금
				cumulativePrincipalPayment += principalPayment;	// 납입원금누계
				balance -= principalPayment;	// 남은 대출 원금
				repaymentAmountTotal += repaymentAmount;
				
				checkLastMonthBalance();
			}
			makeRowCell(repaymentMethodTable, month, parseInt(repaymentAmount), parseInt(principalPayment), parseInt(interest), parseInt(cumulativePrincipalPayment), parseInt(balance));
		}
	}

	else if (repaymentMethodValue.includes("원리금균등")) {	// 원리금균등상환
		for (var month = 1; month <= loanPeriodValue; month++) {
			// 거치 기간 있는 경우
			if (gracePeriodValue > 0 && month <= gracePeriodValue) {
				interest = loanAmountValue * interestRateValue;
				repaymentAmount = interest;
				repaymentAmountTotal += repaymentAmount;
			}
			else {
				var rate = Math.pow((1 + interestRateValue), (loanPeriodValue-gracePeriodValue));
				repaymentAmount = (loanAmountValue * interestRateValue * (rate)) / (rate - 1);
				interest = balance * interestRateValue;
				principalPayment = repaymentAmount - interest;
				cumulativePrincipalPayment += principalPayment;
				balance -= principalPayment;
				repaymentAmountTotal += repaymentAmount;
				
				checkLastMonthBalance();
			}
			makeRowCell(repaymentMethodTable, month, parseInt(repaymentAmount), parseInt(principalPayment), parseInt(interest), parseInt(cumulativePrincipalPayment), parseInt(balance));
		}
	}

	// 마지막 달인데 잔액 안 맞는 경우
	function checkLastMonthBalance() {
		if (month == loanPeriodValue && balance > 0) {
			var amountDifference = loanAmountValue - cumulativePrincipalPayment;
			cumulativePrincipalPayment = loanAmountValue;
			principalPayment += amountDifference;	// 납입 원금
			repaymentAmount += amountDifference;	// 상환금
			balance = 0;
			repaymentAmountTotal += amountDifference;
		}
		
		else if (month == loanPeriodValue && balance < 0) {
			var amountDifference = cumulativePrincipalPayment - loanAmountValue;
			cumulativePrincipalPayment = loanAmountValue;
			principalPayment -= amountDifference;	// 납입 원금
			repaymentAmount -= amountDifference;	// 상환금
			balance = 0;
			repaymentAmountTotal -= amountDifference;
		}
	}

	function makeRowCell(repaymentMethodTable, month, repaymentAmount, principalPayment, interest, cumulativePrincipalPayment, balance) {
		var row = repaymentMethodTable.insertRow();
		var monthCell = row.insertCell();
		var repaymentAmountCell = row.insertCell();
		var principalPaymentCell = row.insertCell();
		var interestCell = row.insertCell();
		var cumulativePrincipalPaymentCell = row.insertCell();
		var balanceCell = row.insertCell();

		monthCell.innerHTML = month;
		repaymentAmountCell.innerHTML = repaymentAmount.toLocaleString();
		principalPaymentCell.innerHTML = principalPayment.toLocaleString();
		interestCell.innerHTML = interest.toLocaleString();
		cumulativePrincipalPaymentCell.innerHTML = cumulativePrincipalPayment.toLocaleString();
		balanceCell.innerHTML = balance.toLocaleString();
		
		repaymentAmountArray.push(repaymentAmount);
		
		if(month == loanPeriodValue) {
			console.log("마지막");
			var jsonData = JSON.stringify(repaymentAmountArray);
			document.getElementById("repaymentAmountList").value = jsonData;

			var updateRepaymentDBButton = document.getElementById("updateRepaymentDB");
			updateRepaymentDBButton.style.display = "block";	
		}
	}
}

function removeTableRow(tableId) {
	for (var i = tableId.rows.length - 1; i > 0; i--) {
		tableId.deleteRow(i);
	}
}

var selectrepaymentMethodElement = document.getElementById("repaymentMethod");
 
const repaymentMethodSelectTableDiv = document.getElementById("repaymentMethodSelectTableDiv");
const repaymentAmountTotalTitleTag = document.getElementById("repaymentAmountTotalTitle");
const repaymentAmountTotalTag = document.getElementById("repaymentAmountTotal");

var inputGracePeriod = document.getElementsByName("gracePeriod")[0];
	
selectrepaymentMethodElement.addEventListener("change", function() {
	repaymentMethodSelectTableDiv.style.display = "block";
	repaymentAmountTotalTitleTag.style.display = "block";
	repaymentAmountTotalTag.style.display = "block";
	repaymentAmountTotalTag.textContent = repaymentAmountTotal.toLocaleString() + "원";

	var selectrepaymentMethodValue = selectrepaymentMethodElement.options[selectrepaymentMethodElement.selectedIndex].value;

	var inputGracePeriod = document.getElementsByName("gracePeriod")[0];

	console.log("selectrepaymentMethodValue ", selectrepaymentMethodValue);
	if (selectrepaymentMethodValue.includes("만기")) {	// 원금만기일시상환
		console.log("만기");
		inputGracePeriod.disabled = true;	
		inputGracePeriod.style.backgroundColor = "#E5E8EB";
	}
	else {		
		console.log("만기 xxx");
		inputGracePeriod.disabled = false;
		inputGracePeriod.style.backgroundColor = "#fff";
	}
});

selectrepaymentMethodElement.addEventListener("click", function() {
	console.log("상환방법 click");
	var inputGracePeriod = document.getElementsByName("gracePeriod")[0];

	inputGracePeriod.disabled = false;
	inputGracePeriod.style.backgroundColor = "#fff";
});

var loanProductNameElement = document.getElementsByName("loanProductName")[0];

loanProductNameElement.addEventListener("change", function() {
	var loanProductNameValue = loanProductNameElement.options[loanProductNameElement.selectedIndex].value;
	document.getElementById("loanProductNameSelect").value = loanProductNameValue;
});

function changeLoanProductName(selectedIndex) {
	var selectLoanProductNameElement = document.getElementsByName("loanProductName")[0];
	var selectedLoanProductNameValue = selectLoanProductNameElement.options[selectedIndex].value;
	document.getElementById("loanProductNameSelect").value = selectedLoanProductNameValue;
}

changeLoan(0);
