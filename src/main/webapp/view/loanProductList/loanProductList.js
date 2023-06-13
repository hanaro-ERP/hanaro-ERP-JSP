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
  rowDiv.classList.add('loanProductInformationRow');

  const titleDiv = document.createElement('div');
  titleDiv.classList.add('loanProductInformationRowTitle');
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
  const infoTable = document.querySelector('.loanProductInformation');
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

function initializeSelectItems(selectedLoanType) {
	const loanData = loanProductInfoData[selectedLoanType];
		if (loanData) {
			loanData.forEach(data => {
				selectItems(data.id);
		});
	}
}

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

initializeSelectItems(loanTypeSelect.value);
