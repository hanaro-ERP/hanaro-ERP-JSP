const loanTypeSelect = document.querySelector('#loanType');
const jobRow = document.querySelector('#loanProductJobRow');
const collateralRow = document.querySelector('#loanProductCollateralRow');

function toggleLoanType(selectedLoanType) {
	if (selectedLoanType == "신용대출") {
		collateralRow.classList.add('display');
		jobRow.classList.remove('display');
	} else {
		jobRow.classList.add('display');
		collateralRow.classList.remove('display');
	}
}

loanTypeSelect.addEventListener('change', function () {
	const selectedLoanType = this.value;
	
	toggleLoanType(selectedLoanType);
});
