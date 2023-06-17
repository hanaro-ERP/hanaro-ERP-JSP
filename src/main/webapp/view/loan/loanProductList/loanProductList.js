// 대출 구분에 따른 상품 정보 데이터
const loanProductInfoData = {
	담보대출 : [
		{
			id: "loanProductMortgage",
			title: "담보 종류",
			tags: ["전체", "예적금", "주택", "전세자금"]
		}
	],
	신용대출 : [
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

// 상품 정보 테이블 초기화
function initializeInfoTable(infoTable) {
  const childElements = infoTable.children;

  for (let i = childElements.length - 2; i > 0; i--) {
    const childElement = childElements[i];
    infoTable.removeChild(childElement);
  }
}

// 상품 정보 테이블 행 생성
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
    const input = document.createElement('input');
    input.setAttribute('type', 'checkbox');
    input.setAttribute('value', tag);
    input.setAttribute('id', tag);
    input.textContent = tag;
    li.appendChild(input);
    ul.appendChild(li);
  });
  rowDiv.appendChild(ul);

  return rowDiv;
}

// 대출 구분에 따른 상품 정보 테이블 전체 생성
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
				selectMultiItems(data.id);
		});
	}
	productInput.value = "";
}



initializeSelectItems(loanTypeSelect.value);