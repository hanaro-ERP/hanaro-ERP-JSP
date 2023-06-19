function checkCheckBox(item, ulId) {
	const checkbox = item.querySelector('input');
	if (checkbox) {
	    checkbox.checked = true;
	    checkbox.setAttribute('name', ulId);
	}
}

function uncheckCheckBox(item) {
	const checkbox = item.querySelector('input');
	if (checkbox) {
	    checkbox.checked = false;
	    checkbox.removeAttribute('name');
	}
}

function toggleCheckBox(item, ulId) {
    const itemCheckBox = item.querySelector('input');
    if (itemCheckBox) {
        if (item.classList.contains('selectedLi')) {
            checkCheckBox(item, ulId);
        } else {
            uncheckCheckBox(item);
        }
    }
}

function selectItem(item, ulId) {
	item.classList.add('selectedLi');
	checkCheckBox(item, ulId);
}

function unselectItem(item) {
	item.classList.remove('selectedLi');
	uncheckCheckBox(item);
}

function toggleItem(item, ulId) {
	item.classList.toggle('selectedLi');
	toggleCheckBox(item, ulId);
}

function selectMultiItems(ulId) {
	const ulElement = document.getElementById(ulId);
	
	const listItems = ulElement.querySelectorAll('li');
	const firstListItem = listItems[0];

	selectItem(firstListItem, ulId);
	
	listItems.forEach((item, index) => {
		if (index !== 0) {
			item.addEventListener('click', () => {
				toggleItem(item, ulId);
				unselectItem(firstListItem);
			});
		}
	});

	firstListItem.addEventListener('click', () => {
		selectItem(firstListItem, ulId);
		
		listItems.forEach((item, index) => {
			if (index !== 0) {
				unselectItem(item);
			}
		});
	});
}

function selectOneItem(ulId) {
	const ulElement = document.getElementById(ulId);
	
	const listItems = ulElement.querySelectorAll('li');
	const firstListItem = listItems[0];

	selectItem(firstListItem, ulId);
	
	listItems.forEach((item, selectedIndex) => {
		item.addEventListener('click', () => {
			toggleItem(item, ulId);
			toggleDirectInput(item, true);
			
			listItems.forEach((item, anotherIndex) => {
				if (selectedIndex !== anotherIndex) {
					unselectItem(item);
					toggleDirectInput(item, false);
				}
			});
		});
	});
}

function toggleDirectInput(directInput, isDisabled) {
	const inputElements = directInput.querySelectorAll('input');
	const selectElements = directInput.querySelectorAll('select');

	if (inputElements && inputElements.length >= 2) {
		inputElements[0].disabled = !isDisabled;
		inputElements[1].disabled = !isDisabled;
	} else if (selectElements && selectElements.length >= 3) {
		selectElements[0].disabled = !isDisabled;
		selectElements[1].disabled = !isDisabled;
		selectElements[2].disabled = !isDisabled;
	}
}

function selectMultiItemsWithDirectInput(ulId) {
	const ulElement = document.getElementById(ulId);

	const listItems = ulElement.querySelectorAll('li');
	const firstListItem = listItems[0];
	const secondListItem = listItems[1];

	selectItem(firstListItem, ulId);

	listItems.forEach((item, index) => {
		if (index !== 0 && index !== 1) {
			item.addEventListener('click', () => {
				toggleItem(item, ulId);
				unselectItem(firstListItem);
				unselectItem(secondListItem);
				
				toggleDirectInput(secondListItem, false);
			});
		}
	});

	firstListItem.addEventListener('click', () => {
		selectItem(firstListItem, ulId);

		listItems.forEach((item, index) => {
			if (index !== 0) {
				unselectItem(item);
			}
		});
		toggleDirectInput(secondListItem, false);
	});	
	
	secondListItem.addEventListener('click', () => {
		selectItem(secondListItem, ulId);

		listItems.forEach((item, index) => {
			if (index !== 1) {
				unselectItem(item);
			}
		});
		toggleDirectInput(secondListItem, true);
	});	
}
