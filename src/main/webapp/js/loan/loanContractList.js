selectMultiItemsWithDirectInput("loanContractStartDate");
selectMultiItemsWithDirectInput("loanContractEndDate");
selectOneItemWithDirectInput("balanceList");
selectOneItemWithDirectInput("latePayment");

setYearSelect("loanContractStartDate");
setYearSelect("loanContractEndDate");
setMonthSelect();
setDaySelect(true);
changeDate();

// 팝업 창
const searchTableRows = document.querySelectorAll('#loanSearchTable tr :not(th)');
const popupBox = document.querySelector('.popupBox');
const popupExitButton = document.querySelector('.popupExitButton');

searchTableRows.forEach((item) => {
	item.addEventListener('click', () => {
		popupBox.classList.toggle('display');
		let parent = item.parentNode;
		console.log(parent);
		console.log(parent.querySelector('form'));
		parent.querySelector('form').submit();
	});	
});

popupExitButton.addEventListener('click', () => {
	popupBox.classList.toggle('display');
});