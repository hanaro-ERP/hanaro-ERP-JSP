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
changeLoan(0);
