function initializeInfoTable(infoTable) {
  const childElements = infoTable.children;

  for (let i = childElements.length - 2; i > 0; i--) {
    const childElement = childElements[i];
    infoTable.removeChild(childElement);
  }
}

function createInfoRow(id, title, tags) {
  const rowDiv = document.createElement('div');
  rowDiv.classList.add('customerInformationRow');

  const titleDiv = document.createElement('div');
  titleDiv.classList.add('customerInformationRowTitle');
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

const customerDetailSelect = document.getElementById('customerDetail');
const customerDetailInformation = document.querySelector('.customerDetailInformation');

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