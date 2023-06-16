const loanProductInfoData = {
	mortgageLoan : [
		{
			id: "loanProductMortgage",
			title: "담보 종류",
			tags: ["전체", "예적금", "주택", "전세자금"]
		}
	],
	creditLoan : [
		{
			id: "loanProductJob",
			title: "직업",
			tags: ["전체", "직장인", "공무원", "군인", "금융인", "전문직", "의사", "자영업", "무직"]
		},
		{
			id: "loanProductPeriod",
			title: "기간",
			tags: ["전체", "1년", "2년", "3년", "5년", "10년", "10년 이상"]
		},
		{
			id: "loanProductIncome",
			title: "연소득",
			tags: ["전체", "~2천만원", "~3천만원", "~5천만원", "~1억원", "1억원 이상"]
		},
		{
			id: "loanProductLimit",
			title: "대출 한도",
			tags: ["전체", "5천만원", "1억원", "5억원", "10억원"]
		}
	]
}

function initializeInfoTable(infoTable) {
  const childElements = infoTable.children;

  for (let i = childElements.length - 2; i > 0; i--) {
    const childElement = childElements[i];
    infoTable.removeChild(childElement);
  }
}

function createInfoRow(id, title, tags) {
  const rowDiv = document.createElement('div');
  rowDiv.classList.add('innerInformationRow');

  const titleDiv = document.createElement('div');
  titleDiv.classList.add('innerInformationRowTitle');
  titleDiv.textContent = title;
  rowDiv.appendChild(titleDiv);

  const ul = document.createElement('ul');
  ul.id = id;
  tags.forEach(tag => {
    const li = document.createElement('li');
    li.textContent = tag;
    ul.appendChild(li);
  });
  rowDiv.appendChild(ul);

  return rowDiv;
}

function generateLoanProductInformation(loanType) {
  const infoTable = document.querySelector('.innerInformation');
  initializeInfoTable(infoTable);

  const loanData = loanProductInfoData[loanType];
  if (loanData) {
    loanData.forEach(data => {
      const row = createInfoRow(data.id, data.title, data.tags);
      infoTable.insertBefore(row, infoTable.lastElementChild);
    });
  }
}

const loanTypeSelect = document.querySelector('#loanType');

loanTypeSelect.addEventListener('change', function () {
	const selectedLoanType = this.value;
	generateLoanProductInformation(selectedLoanType);
  
	initializeSelectItems(selectedLoanType);
});

const productInput = document.getElementById('loanProductSearchInput');

function initializeSelectItems(selectedLoanType) {
	const loanData = loanProductInfoData[selectedLoanType];
		if (loanData) {
			loanData.forEach(data => {
				selectItems(data.id);
		});
	}
	productInput.value = "";
}

function toggleNameSelect(item){
	const input = item.querySelector('input');
	if (item.className === 'selectedLi') {
		input.setAttribute('name', 'dynamicName');
	} else {
		input.removeAttribute('name');
	}
}

function selectItems(ulId) {
	const ulElement = document.getElementById(ulId);

	const listItems = ulElement.querySelectorAll('li');
	const firstListItem = listItems[0];

	firstListItem.classList.add('selectedLi');
	toggleNameSelect(firstListItem);
	
	listItems.forEach((item, index) => {
		if (index !== 0) {
			item.addEventListener('click', () => {
				item.classList.toggle('selectedLi');
				toggleNameSelect(item);
				firstListItem.classList.remove('selectedLi');
				toggleNameSelect(firstListItem);
			});
		}
	});

	firstListItem.addEventListener('click', () => {
    	firstListItem.classList.toggle('selectedLi');
    	toggleNameSelect(firstListItem);
    	
		listItems.forEach((item, index) => {
			if (index !== 0) {
				item.classList.remove('selectedLi');
				toggleNameSelect(item);
			}
		});
	});
}

const searchButton = document.querySelector('button[type="submit"]');

searchButton.addEventListener('click', function() {
 	const selectedLoanType = loanTypeSelect.value;
 	const selectedAttributes = getSelectedAttributes(selectedLoanType);
	const productInputValue = productInput.value;

	// 나중에 서블릿 메소드 추가될 때 삭제하겠습니다.
 	console.log('대출 구분:', selectedLoanType);
 	console.log('선택된 속성:', selectedAttributes);
 	console.log('상품 이름:', productInputValue)
});

function getSelectedAttributes(loanType) {
	const selectedAttributes = {};
	const loanData = loanProductInfoData[loanType];
	if (loanData) {
		loanData.forEach(data => {
			const ulElement = document.getElementById(data.id);
			const listItems = ulElement.querySelectorAll('li');
      
			const selectedItems = [];
      
			listItems.forEach((item, index) => {
				if (index !== 0 && item.classList.contains('selectedLi')) {
					selectedItems.push(item.textContent);
				}
		   	selectedAttributes[data.title] =  selectedItems;	
			});
		});
	}
	return selectedAttributes;
}

initializeSelectItems(loanTypeSelect.value);