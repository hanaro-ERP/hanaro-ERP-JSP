var loanProduct = new Array();
loanProduct[0] = new Array(
	"-",
	"하나햇살론뱅크",
	"하나예금담보대출",
	"하나원큐주택담보대출"
);
loanProduct[1] = new Array(
	"-",
	"닥터클럽대출-골드",
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

function updateTable() {
	console.log("===== update table======");

	var repaymentMethod = document.getElementById("repaymentMethod");	// 상환 방법
	var loanPeriod = document.getElementById("loanPeriod");	// 기간
	var loanAmount = document.getElementById("loanAmount");	// 원금
	var interestRate = document.getElementById("interestRate");	// 월 이자율
	var gracePeriod = document.getElementById("gracePeriod");	// 거치 기간

	var repaymentMethodValue = repaymentMethod.value;
	var loanPeriodValue = loanPeriod.value * 12;	// 개월 수
	var loanAmountValue = loanAmount.value * 10000;
	var interestRateValue = interestRate.value * 0.01;
	var gracePeriodValue = gracePeriod.value * 12;	// 거치 기간 개월 수

	console.log("repaymentMethodValue = ", repaymentMethodValue);
	console.log("loanPeriodValue = ", loanPeriodValue);
	console.log("loanAmountValue = ", loanAmountValue);
	console.log("interestRateValue = ", interestRateValue);
	console.log("gracePeriodValue = ", gracePeriodValue);

	var repaymentAmount = 0;	// 상환금 = 상환한 원금 + 이자
	var principalPayment = 0;	// 납입 원금
	var interest = 0;	// 월 납부 이자
	var cumulativePrincipalPayment = 0;	// 납입원금누계
	var balance = loanAmountValue;	// 남은 대출 원금

	var repaymentMethodTable = document.getElementById("repaymentMethodSelectTable");
	removeTableRow(repaymentMethodTable);

	// 상환 방법에 따라 다르게 계산
	if (repaymentMethodValue.includes("만기")) {	// 원금만기일시상환
		interest = (loanAmountValue * interestRateValue * loanPeriodValue);	// 이자
		principalPayment = 0;	// 납입 원금
		cumulativePrincipalPayment = 0; 	// 납입원금누계
		balance = loanAmountValue; // 남은 대출 원금

		for (var month = 1; month < loanPeriodValue; month++) {
			var row = repaymentMethodTable.insertRow();
			var monthCell = row.insertCell();
			var repaymentAmountCell = row.insertCell();
			var principalPaymentCell = row.insertCell();
			var interestCell = row.insertCell();
			var cumulativePrincipalPaymentCell = row.insertCell();
			var balanceCell = row.insertCell();

			repaymentAmount += interest;	// 상환금

			// 값 넣기
			monthCell.innerHTML = month;
			repaymentAmountCell.innerHTML = repaymentAmount.toLocaleString();
			principalPaymentCell.innerHTML = principalPayment.toLocaleString();
			interestCell.innerHTML = interest.toLocaleString();
			cumulativePrincipalPaymentCell.innerHTML = cumulativePrincipalPayment.toLocaleString();
			balanceCell.innerHTML = balance.toLocaleString();
		}
		// 마지막 달
		var row = repaymentMethodTable.insertRow();
		var monthCell = row.insertCell();
		var repaymentAmountCell = row.insertCell();
		var principalPaymentCell = row.insertCell();
		var interestCell = row.insertCell();
		var cumulativePrincipalPaymentCell = row.insertCell();
		var balanceCell = row.insertCell();

		principalPayment = loanAmountValue;	// 납입 원금
		cumulativePrincipalPayment = loanAmountValue; 	// 납입원금누계
		balance = 0; // 남은 대출 원금
		repaymentAmount += principalPayment + interest;	// 상환금

		// 값 넣기
		monthCell.innerHTML = month;
		repaymentAmountCell.innerHTML = parseInt(repaymentAmount).toLocaleString();
		principalPaymentCell.innerHTML = parseInt(principalPayment).toLocaleString();
		interestCell.innerHTML = parseInt(interest).toLocaleString();
		cumulativePrincipalPaymentCell.innerHTML = parseInt(cumulativePrincipalPayment).toLocaleString();
		balanceCell.innerHTML = parseInt(balance).toLocaleString();
	}

	else if (repaymentMethodValue.includes("원금균등")) { // 원금균등상환
		principalPayment = loanAmountValue / loanPeriodValue;	// 납입 원금

		for (var month = 1; month <= loanPeriodValue; month++) {
			var row = repaymentMethodTable.insertRow();
			var monthCell = row.insertCell();
			var repaymentAmountCell = row.insertCell();
			var principalPaymentCell = row.insertCell();
			var interestCell = row.insertCell();
			var cumulativePrincipalPaymentCell = row.insertCell();
			var balanceCell = row.insertCell();

			monthCell.innerHTML = month;

			// 거치 기간 있는 경우
			if (gracePeriodValue > 0) {
				interest = loanAmountValue * interestRateValue * (((12 * loanPeriodValue) - gradpgracePeriodValue) + 1) / 24;
				// 계산 해야 함
			}
			// 거치 기간 없는 경우
			else {
				interest = balance * interestRateValue;	// 이자
				repaymentAmount = interest + principalPayment;	// 상환금 = 이자 + 납입 원금
				cumulativePrincipalPayment += principalPayment;	// 납입원금누계
				balance -= principalPayment;	// 남은 대출 원금
				
				checkLastMonthBalance();
			}

			repaymentAmountCell.innerHTML = parseInt(repaymentAmount).toLocaleString();
			principalPaymentCell.innerHTML = parseInt(principalPayment).toLocaleString();
			interestCell.innerHTML = parseInt(interest).toLocaleString();
			cumulativePrincipalPaymentCell.innerHTML = parseInt(cumulativePrincipalPayment).toLocaleString();
			balanceCell.innerHTML = parseInt(balance).toLocaleString();
		}
	}

	else if (repaymentMethodValue.includes("원리금균등")) {	// 원리금균등상환
		for (var month = 1; month <= loanPeriodValue; month++) {
			var row = repaymentMethodTable.insertRow();
			var monthCell = row.insertCell();
			var repaymentAmountCell = row.insertCell();
			var principalPaymentCell = row.insertCell();
			var interestCell = row.insertCell();
			var cumulativePrincipalPaymentCell = row.insertCell();
			var balanceCell = row.insertCell();

			monthCell.innerHTML = month;

			// 거치 기간 있는 경우
			if (gracePeriodValue > 0) {
				// 계산 해야 함
			}
			else {
				var rate = Math.pow((1 + interestRateValue), loanPeriodValue);
				repaymentAmount = (loanAmountValue * interestRateValue * (rate)) / (rate - 1);
				interest = balance * interestRateValue;
				principalPayment = repaymentAmount - interest;
				cumulativePrincipalPayment += principalPayment;

				if (cumulativePrincipalPayment > loanAmountValue) {
					var amountDifference = cumulativePrincipalPayment - loanAmountValue;
					cumulativePrincipalPayment = loanAmountValue;
					principalPayment -= amountDifference;
				}
				balance -= principalPayment;
				
				checkLastMonthBalance();
			}

			repaymentAmountCell.innerHTML = parseInt(repaymentAmount).toLocaleString();
			principalPaymentCell.innerHTML = parseInt(principalPayment).toLocaleString();
			interestCell.innerHTML = parseInt(interest).toLocaleString();
			cumulativePrincipalPaymentCell.innerHTML = parseInt(cumulativePrincipalPayment).toLocaleString();
			balanceCell.innerHTML = parseInt(balance).toLocaleString();
		}
	}	

	// 마지막 달인데 잔액 남은 경우
	function checkLastMonthBalance() {
		if (month == loanPeriodValue && balance > 0) {
			var amountDifference = loanAmountValue - cumulativePrincipalPayment;
			cumulativePrincipalPayment = loanAmountValue;
			principalPayment += amountDifference;	// 납입 원금
			repaymentAmount += amountDifference;	// 상환금
			balance = 0;
		}
	}
	
}

function removeTableRow(tableId) {
	for (var i = tableId.rows.length - 1; i > 0; i--) {
		tableId.deleteRow(i);
	}
}

changeLoan(0);
