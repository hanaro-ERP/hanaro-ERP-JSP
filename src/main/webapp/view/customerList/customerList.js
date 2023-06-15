function selectItems(ulId) {
	const ulElement = document.getElementById(ulId);

	const listItems = ulElement.querySelectorAll('li');
	const firstListItem = listItems[0];

	firstListItem.classList.add('selectedLi');

	listItems.forEach((item, index) => {
		if (index !== 0) {
			item.addEventListener('click', () => {
			item.classList.toggle('selectedLi');
			firstListItem.classList.remove('selectedLi');
			});
		}
	});

	firstListItem.addEventListener('click', () => {
    	firstListItem.classList.toggle('selectedLi');

		listItems.forEach((item, index) => {
			if (index !== 0) {
				item.classList.remove('selectedLi');
			}
		});
	});	
}

const startAgeInput = document.getElementById('startAge');
const endAgeInput = document.getElementById('endAge');
const ageInputText = document.querySelectorAll('.directInputText');

function toggleDirectAgeInput(isTrue) {
	startAgeInput.disabled = isTrue;
	endAgeInput.disabled = isTrue;
	
	let textColor = '';
	if (isTrue) {
		textColor = '#B0B8C1';
	} else {
		textColor = '#191F29';
	}
	
	ageInputText.forEach(p => {
			p.style.color = textColor;
	})
}

function selectAgeItems(ulId) {
	const ulElement = document.getElementById(ulId);

	const listItems = ulElement.querySelectorAll('li');
	const firstListItem = listItems[0];
	const secondListItem = listItems[1];

	firstListItem.classList.add('selectedLi');

	listItems.forEach((item, index) => {
		if (index !== 0 && index !== 1 && index !== 2) {
			item.addEventListener('click', () => {
			item.classList.toggle('selectedLi');
			firstListItem.classList.remove('selectedLi');
			secondListItem.classList.remove('selectedLi');
			
			toggleDirectAgeInput(true);
			});
		}
	});

	firstListItem.addEventListener('click', () => {
    	firstListItem.classList.toggle('selectedLi');

		listItems.forEach((item, index) => {
			if (index !== 0) {
				item.classList.remove('selectedLi');
			}
		});
		
		toggleDirectAgeInput(true);
	});	
	
	secondListItem.addEventListener('click', () => {
    	secondListItem.classList.toggle('selectedLi');

		listItems.forEach((item, index) => {
			if (index !== 1) {
				item.classList.remove('selectedLi');
			}
		});
		
		toggleDirectAgeInput(false);
	});	
}

const customerDetailSelect = document.getElementById('customerDetailButton');
const customerDetailInformation = document.getElementById('customerDetailInformation');

customerDetailInformation.style.display = 'none'; // 초기에 숨김 상태로 설정

customerDetailSelect.addEventListener('click', function() {
  if (customerDetailInformation.style.display === 'none') {
    customerDetailInformation.style.display = 'block';
    customerDetailSelect.innerHTML = '▼';
  } else {
    customerDetailInformation.style.display = 'none';
    customerDetailSelect.innerHTML = '▲';
  }
});

const informationUlIds = ['customerAge', 'customerGender', 'customerGrade', 'customerCredit', 'disabilityGrade'];

const customerNameInput = document.getElementById('customerNameInput');
const customerJobCode = document.getElementById('jobCode');
const customerEmployeeInput = document.getElementById('customerEmployeeInput');
const bankLocation = document.getElementById('bankLocation');
const customerCountry = document.getElementById('customerCountry');
const customerCity = document.querySelectorAll('.customerCity');

const customerSearchButton = document.getElementById('customerSearchButton');

customerSearchButton.addEventListener('click', function() {
 	const customerNameInputValue = customerNameInput.value;
 	const customerJobCodeValue = customerJobCode.value;
 	const customerEmployeeInputValue = customerEmployeeInput.value;
 	const bankLocationValue = bankLocation.value;
	const customerCountryValue = customerCountry.value;
	let customerCityValue = [];
	customerCity.forEach(city => {
		customerCityValue.push(city.value);
	})

	const returnInformations = {};
	
	returnInformations['customerName'] = customerNameInputValue;
	returnInformations['jobCode'] = customerJobCodeValue;
	returnInformations['employeeName'] = customerEmployeeInputValue;
	returnInformations['bankLocation'] = bankLocationValue;
	returnInformations['customerCountry'] = customerCountryValue;
	returnInformations['bankLocation'] = bankLocationValue;
	returnInformations['customerCity'] = customerCityValue;
	
	getSelectedAttributes(informationUlIds, returnInformations);
	
	// 나중에 서블릿 메소드 추가될 때 삭제하겠습니다.
 	console.log(returnInformations);
});

function getSelectedAttributes(ulIds, dict) {	
	ulIds.forEach(ulId => {
		const ulElement = document.getElementById(ulId);
		const listItems = ulElement.querySelectorAll('li');
  
		const selectedItems = [];
  
		listItems.forEach((item, index) => {
			if (index !== 0 && item.classList.contains('selectedLi')) {
				const liText = item.textContent;
				
				if (liText === '직접 입력') {
					const directInputs = document.querySelectorAll('.directInputValue');
					directInputs.forEach(directInput => {
						selectedItems.push(directInput.value);
					})
				} else {
					selectedItems.push(liText);
				}
			}
	   	dict[ulId] =  selectedItems;	
		});
	});
	
	return dict;
}

selectAgeItems('customerAge');
selectItems('customerGender');
selectItems('customerGrade');
selectItems('customerCredit');
selectItems('disabilityGrade');
