selectOneItem('depositBalance');
selectMultiItemsWithDirectInput("depositStartDate");
selectOneItem('depositType');

const searchTableRows = document.querySelectorAll('#depositSearchTable tr :not(th)');
const popupBox = document.querySelector('.popupBox');
const popupExitButton = document.querySelector('.popupExitButton');

searchTableRows.forEach((item) => {
	item.addEventListener('click', () => {
		popupBox.classList.toggle('display');
	});	
});

popupExitButton.addEventListener('click', () => {
	popupBox.classList.toggle('display');
});

setYearSelect();
setMonthSelect();
setDaySelect(true);
changeDate();
