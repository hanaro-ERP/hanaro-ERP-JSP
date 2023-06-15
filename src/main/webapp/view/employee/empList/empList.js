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

const container1 = document.getElementById('container1');
const container2 = document.getElementById('container2');
container2.style.display = 'none';

document.querySelector('.asideMenuContainer').addEventListener('click', function(event) {
  	if (event.target.textContent === '지점 목록') {
    	container1.style.display = 'none';
    	container2.style.display = 'block';
  	}
  	else {
    	container2.style.display = 'none';
    	container1.style.display = 'block';	
	}
});
