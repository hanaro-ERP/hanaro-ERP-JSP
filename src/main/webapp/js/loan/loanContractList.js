selectMultiItemsWithDirectInput("loanContractStartDate");
selectMultiItemsWithDirectInput("loanContractEndDate");
selectMultiItemsWithDirectInput("balanceList");
selectMultiItemsWithDirectInput("latePayment");

setYearSelect();
setMonthSelect();
setDaySelect(true);
changeDate();

// 팝업 창
const searchTableRows = document.querySelectorAll('#loanSearchTable tr :not(th)');
const popupBox = document.querySelector('.popupBox');
const popupExitButton = document.querySelector('.popupExitButton');

console.log("searchTableRows", searchTableRows);

searchTableRows.forEach((item) => {
	item.addEventListener('click', () => {
		popupBox.classList.toggle('display');
	});	
});

popupExitButton.addEventListener('click', () => {
	popupBox.classList.toggle('display');
});

