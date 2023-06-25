
const loanTypeSelect = document.querySelector('#loanType');

loanTypeSelect.addEventListener('change', function () {
	const selectedLoanType = this.value;
	
	const jobRow = document.querySelector('#loanProductJobRow');
	const collateralRow = document.querySelector('#loanProductCollateralRow');

	jobRow.classList.toggle('display');
	collateralRow.classList.toggle('display');
	
	selectMultiItems("loanProductJob");
	selectMultiItems("loanProductCollateral");
});

const productInput = document.getElementById('loanProductSearchInput');

function initializeSelectItems(selectedLoanType) {
	selectMultiItems("loanProductJob");
	productInput.value = "";
}

selectMultiItemsWithDirectInput("loanProductPeriod");
selectOneItemWithDirectInput("loanProductIncome");
selectOneItemWithDirectInput("loanProductLimit");

initializeSelectItems(loanTypeSelect.value);
