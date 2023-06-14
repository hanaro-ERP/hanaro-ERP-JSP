const depositProductInfoData = ["loanProductType", "depositProductBalance"]

function initializeSelectItems(depositProductInfoData) {
		if (depositProductInfoData) {
			depositProductInfoData.forEach(data => {
				selectItems(data);
		});
	}
}

function selectItems(ulId) {
	const ulElement = document.getElementById(ulId);

	const listItems = ulElement.querySelectorAll('li');
	const firstListItem = listItems[0];

	firstListItem.classList.add('selectedLi');

	listItems.forEach((item, index1) => {
		item.addEventListener('click', () => {
			item.classList.toggle('selectedLi');
			listItems.forEach((item, index2) => {
				if (index2 !== index1) {
					item.classList.remove('selectedLi');
				}
			});
		});
	});
}

initializeSelectItems(depositProductInfoData);

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