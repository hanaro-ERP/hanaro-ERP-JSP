function validateForm() {
	var productName = document.getElementsByName("productName")[0].value;
	var loanPeriod = document.getElementsByName("loanPeriod")[0].value;
	var loanType = document.getElementsByName("loanType")[0].value;
	var collateralType = document.getElementsByName("collateralType")[0].value;
	var loanLimit = document.getElementsByName("loanLimit")[0].value;
	var interestRate = document.getElementsByName("interestRate")[0].value;
	var interestRateLimit = document.getElementsByName("interestRateLimit")[0].value;
	var loanPerpose = document.getElementsByName("loanPerpose")[0].value;
	var adequateRisk = document.getElementsByName("adequateRisk")[0].value;

	if (
		productName === "" ||
		loanPeriod === "" ||
		loanType === "" ||
		collateralType === "" ||
		loanLimit === "" ||
		interestRate === "" ||
		interestRateLimit === "" ||
		loanPerpose === "" ||
		adequateRisk === ""
	) {
		alert("모든 입력 필드를 채워주세요.");
		return false;
	}

	return true;
}