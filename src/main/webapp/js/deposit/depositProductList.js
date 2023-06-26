selectOneItem('depositBalance');
selectMultiItemsWithDirectInput("depositStartDate");
selectOneItemWithDirectInput('depositType');

const searchTableRows = document.querySelectorAll('#depositSearchTable tr :not(th)');
const popupBox = document.querySelector('.popupBox');
const popupExitButton = document.querySelector('.popupExitButton');

searchTableRows.forEach((item) => {
	item.addEventListener('click', () => {
		popupBox.classList.toggle('display');
		item.parentNode.querySelector('form').submit();
	});	
});

popupExitButton.addEventListener('click', () => {
	popupBox.classList.toggle('display');
});

setYearSelect();
setMonthSelect();
setDaySelect(true);
changeDate();