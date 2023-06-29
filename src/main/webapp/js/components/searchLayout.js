// select 부분 변수 및 함수 추가
var countyList = new Array();
countyList[0] = new Array("");
countyList[1] = new Array(
	"",
	"강남구",
	"강동구",
	"강북구",
	"강서구",
	"관악구",
	"광진구",
	"구로구",
	"금천구",
	"노원구",
	"도봉구",
	"동대문구",
	"동작구",
	"마포구",
	"서대문구",
	"서초구",
	"성동구",
	"성북구",
	"송파구",
	"양천구",
	"영등포구",
	"용산구",
	"은평구",
	"종로구",
	"중구",
	"중랑구"
);
countyList[2] = new Array(
	"",
	"강서구",
	"금정구",
	"남구",
	"동구",
	"동래구",
	"부산진구",
	"북구",
	"사상구",
	"사하구",
	"서구",
	"수영구",
	"연제구",
	"영도구",
	"중구",
	"해운대구",
	"기장군"
);
countyList[3] = new Array(
	"",
	"남구",
	"달서구",
	"동구",
	"북구",
	"서구",
	"수성구",
	"중구",
    "달성군"
);
countyList[4] = new Array(
	"",
	"계양구",
	"남구",
	"남동구",
	"동구",
	"부평구",
	"서구",
	"연수구",
	"중구",
	"강화군",
	"옹진군"
);
countyList[5] = new Array("", "광산구", "남구", "동구", "북구", "서구");
countyList[6] = new Array("", "대덕구", "동구", "서구", "유성구", "중구");
countyList[7] = new Array("", "남구", "동구", "북구", "중구", "울주군");
countyList[8] = new Array(
	"",
	"고양시",
	"과천시",
	"광명시",
	"구리시",
	"군포시",
	"남양주시",
	"동두천시",
	"부천시",
	"성남시",
	"수원시",
	"시흥시",
	"안산시",
	"안양시",
	"오산시",
	"의왕시",
	"의정부시",
	"평택시",
	"하남시",
	"가평군",
	"광주시",
	"김포시",
 	"안성시",
	"양주군",
	"양평군",
	"여주군",
	"연천군",
	"용인시",
	"이천군",
	"파주시",
	"포천시",
	"화성시"
);
countyList[9] = new Array(
	"",
	"강릉시",
	"동해시",
	"삼척시",
	"속초시",
	"원주시",
	"춘천시",
	"태백시",
	"고성군",
	"양구군",
	"양양군",
	"영월군",
	"인제군",
	"정선군",
	"철원군",
	"평창군",
	"홍천군",
	"화천군",
	"황성군"
);
countyList[10] = new Array(
	"",
	"제천시",
	"청주시",
	"충주시",
	"괴산군",
	"단양군",
	"보은군",
	"영동군",
	"옥천군",
	"음성군",
	"진천군",
	"청원군"
);
countyList[11] = new Array(
	"",
	"공주시",
	"보령시",
	"서산시",
	"아산시",
	"천안시",
	"금산군",
	"논산군",
	"당진군",
	"부여군",
	"서천군",
	"연기군",
	"예산군",
	"청양군",
	"태안군",
	"홍성군"
);
countyList[12] = new Array(
	"",
	"군산시",
	"김제시",
	"남원시",
	"익산시",
	"전주시",
	"정읍시",
	"고창군",
	"무주군",
	"부안군",
	"순창군",
	"완주군",
	"임실군",
	"장수군",
	"진안군"
);
countyList[13] = new Array(
	"",
	"광양시",
	"나주시",
	"목포시",
	"순천시",
	"여수시",
	"여천시",
	"강진군",
	"고흥군",
	"곡성군",
	"구례군",
	"담양군",
	"무안군",
	"보성군",
	"신안군",
	"여천군",
	"영광군",
	"영암군",
	"완도군",
	"장성군",
	"장흥군",
	"진도군",
	"함평군",
	"해남군",
	"화순군"
);
countyList[14] = new Array(
	"",
	"경산시",
	"경주시",
	"구미시",
	"김천시",
	"문겅시",
	"상주시",
	"안동시",
	"영주시",
	"영천시",
	"포항시",
	"고령군",
	"군위군",
	"봉화군",
	"성주군",
	"영덕군",
	"영양군",
	"예천군",
	"울릉군",
	"울진군",
	"의성군",
	"청도군",
	"청송군",
	"칠곡군"
);
countyList[15] = new Array(
	"",
	"거제시",
	"김해시",
	"마산시",
	"밀양시",
	"사천시",
	"울산시",
	"진주시",
	"진해시",
	"창원시",
	"통영시",
	"거창군",
	"고성군",
	"남해군",
	"산청군",
	"양산시",
	"의령군",
	"창녕군",
	"하동군",
	"함안군",
	"함양군",
	"합천군"
);
countyList[16] = new Array("", "서귀포시", "제주시", "남제주군", "북제주군");

function changeCounty(add) {
	const selectElement = document.getElementById('districtSelect');
	/* 옵션메뉴삭제 */
	selectElement.innerHTML = '';
	/* 옵션박스추가 */
	for (let i = 0; i < countyList[add].length; i++) {
		const option = document.createElement('option');
		option.value = countyList[add][i];
		option.text = countyList[add][i];
		selectElement.appendChild(option);
	}
}

function checkCheckBox(item, ulId) {
	const checkboxes = item.querySelectorAll('input');

	checkboxes.forEach(checkbox => {
		if (checkbox) {
			checkbox.checked = true;
		    checkbox.setAttribute('name', ulId);
		}
	})
}

function uncheckCheckBox(item) {
	const checkboxes = item.querySelectorAll('input');

	checkboxes.forEach(checkbox => {
		if (checkbox) {
			checkbox.checked = false;
			checkbox.removeAttribute('name');
		}
	})
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

function notSelectedCheck(ulId) {
	const ulElement = document.getElementById(ulId);
	const listItems = ulElement.querySelectorAll('li');

	const firstListItem = listItems[0];
	
	const selectedItems = ulElement.querySelectorAll('.selectedLi');
	const selectedCount = selectedItems.length;
	
	if (selectedCount == 0) {
		selectItem(firstListItem, ulId);
	}
}

function initializeCheckbox(ulId) {
	const ulElement = document.getElementById(ulId);
	const listItems = ulElement.querySelectorAll('li');
	const firstListItem = listItems[0];
	
	selectItem(firstListItem, ulId);
	
	listItems.forEach((item, index) => {
		if (index !== 0) {
			unselectItem(item);
		}
	});
}

function allSelectedCheck(ulId, cnt) {
	const ulElement = document.getElementById(ulId);
	const listItems = ulElement.querySelectorAll('li');

	const firstListItem = listItems[0];
	
	const selectedItems = ulElement.querySelectorAll('.selectedLi');
	const selectedCount = selectedItems.length;
	
	if (selectedCount == listItems.length - cnt) {
		initializeCheckbox(ulId);
	}
}

function selectMultiItems(ulId) {
	const ulElement = document.getElementById(ulId);
	
	const listItems = ulElement.querySelectorAll('li');
	const firstListItem = listItems[0];

	initializeCheckbox(ulId);
	
	listItems.forEach((item, index) => {
		if (index !== 0) {
			item.addEventListener('click', () => {
				toggleItem(item, ulId);
				unselectItem(firstListItem);
				
				notSelectedCheck(ulId);
				allSelectedCheck(ulId, 1);
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
			
			notSelectedCheck(ulId);
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
	} else {
		inputElements[0].disabled = !isDisabled;
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
				
				notSelectedCheck(ulId);
				allSelectedCheck(ulId, 2);
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

function selectOneItemWithDirectInput(ulId) {
	const ulElement = document.getElementById(ulId);
	
	const listItems = ulElement.querySelectorAll('li');
	const firstListItem = listItems[0];
	
	selectItem(firstListItem, ulId);
	
	listItems.forEach((item, selectedIndex) => {
		if (selectedIndex !== 1) {
			item.addEventListener('click', () => {
				toggleItem(item, ulId);
				toggleDirectInput(item, true);
				
				listItems.forEach((item, anotherIndex) => {
					if (selectedIndex !== anotherIndex) {
						unselectItem(item);
						toggleDirectInput(item, false);
					}
				});
				
				notSelectedCheck(ulId);
			});
		} else {
			item.addEventListener('click', () => {
				selectItem(item, ulId);

				listItems.forEach((item, index) => {
					if (index !== 1) {
						unselectItem(item);
					}
				});
				toggleDirectInput(item, true);
			});	
		}
	});
}

let yearSelectList, monthSelectList, daySelectList;
let yearSelectRow, monthSelectRow, daySelectRow;
let yearSelect, monthSelect, daySelect;
let rowIndex = 0;

// 연도 설정
function setYearSelect(ulId) {
	yearSelectList = document.querySelector('#' + ulId + ' .yearSelect');
	
	let endOfYearList = ""
	
	if (ulId.includes("Start")){		
		endOfYearList = new Date().getFullYear();
	}
	else {
		endOfYearList = 2050;
	}
	
	for (let year = 1990; year <= endOfYearList; year++) {
		const option = document.createElement("option");
		option.value = year;
		option.textContent = year;
		yearSelectList.appendChild(option);
	}
}

//월 설정
function setMonthSelect() {
	monthSelect = document.getElementsByClassName("monthSelect");

	Array.from(monthSelect).forEach(monthSelect => {
		const option = document.createElement("option");
		option.value = "";
		option.textContent = "";
		monthSelect.appendChild(option);
		
		for (let month = 1; month <= 12; month++) {
			const option = document.createElement("option");
			if (month < 10) {
				month = "0"+month;
			}
			option.value = month;
			option.textContent = month;
			monthSelect.appendChild(option);
		}
	});
}

// 일 설정
function setDaySelect(isInitial) {	
	console.log("setDaySelect ");
	daySelectList = document.getElementsByClassName("daySelect");	
	let endDay;	// 선택한 월에 따라 endDay 다르게
	endDay = new Date(year, month, 0).getDate();
	
	if (isInitial) {
		yearSelectRow = document.getElementsByClassName("yearSelect")[rowIndex];
		monthSelectRow = document.getElementsByClassName("monthSelect")[rowIndex];
		yearSelectRow.value = 1990;
		monthSelectRow.value = "";

		yearSelect = parseInt(yearSelectRow.value);
		monthSelect = parseInt(monthSelectRow.value);
		//daySelect = parseInt(daySelectRow.value);
		endDay = new Date(yearSelect, monthSelect, 0).getDate();

		Array.from(daySelectList).forEach(daySelectRow => {
			daySelectRow.innerHTML = "";
			const option = document.createElement("option");
			option.value = "";
			option.textContent = "";
			daySelectRow.appendChild(option);
		});
		Array.from(daySelectList).forEach(daySelectRow => {				
			for (let day = 1; day <= endDay; day++) {
				const option = document.createElement("option");
				if (day < 10) {
					day = "0" + day;
				}
				option.value = day;
				option.textContent = day;
				daySelectRow.appendChild(option);
			}
		});
	}

	else {
		daySelectRow = document.getElementsByClassName("daySelect")[rowIndex];
		daySelectRow.innerHTML = "";
		const option = document.createElement("option");
		option.value = "";
		option.textContent = "";
		daySelectRow.appendChild(option);


		for (let day = 1; day <= endDay; day++) {
			const option = document.createElement("option");
			if (day < 10) {
				day = "0" + day;
			}
			option.value = day;
			option.textContent = day;
			daySelectRow.appendChild(option);
		}
	}
}

changeDate();

// 선택에 따라 일 범위 바꾸기
function changeDate() {
	console.log("changeDate ");
	yearSelectList = document.getElementsByClassName("yearSelect");
	monthSelectList = document.getElementsByClassName("monthSelect");

	if (yearSelectList.length > 0) {
		Array.from(yearSelectList).forEach(yearSelectRow => {
			yearSelectRow.removeEventListener("click", (event) => handleDateSelect(event, true));
			yearSelectRow.addEventListener("click", (event) => handleDateSelect(event, true));
		});
	}

	if (monthSelectList.length > 0) {
		Array.from(monthSelectList).forEach(monthSelectRow => {
			monthSelectRow.removeEventListener("click", (event) => handleDateSelect(event, false));
			monthSelectRow.addEventListener("click", (event) => handleDateSelect(event, false));
		});
	}
}

// 연도인지 월인지 판단해서 넘겨주기
function handleDateSelect(event, isYear) {
	console.log("handleDateSelect ");
	const selectedDate = event.target;
	const liElement = selectedDate.closest('ul').querySelector('li');
	const ulId = selectedDate.closest('ul').id;

	if (ulId.includes("deposit")) {
		changeDateRowSelect("depositStartDate");
	}
	else if (ulId.includes("Start")) {
		changeDateRowSelect("loanContractStartDate");
		rowIndex = 0;
	}
	else {
		changeDateRowSelect("loanContractEndDate");
		rowIndex = 1;
	}

	if (isYear) {
		yearSelect = selectedDate.value;
		monthSelect = parseInt(monthSelectRow.value);
	}
	else {
		monthSelect = selectedDate.value;
		yearSelect = parseInt(yearSelectRow.value);
	}
	setDaySelect(false); // 일 범위 바꾸기
}

// 대출일, 만기일 날짜 선택하면 '전체' -> '직접입력'
function changeDateRowSelect(rowId) {
	console.log("changeDateRowSelect");
	let ulElement, listItems;

	if (rowId === "loanContractStartDate") {
		ulElement = document.getElementById("loanContractStartDate");
	}
	else if (rowId === "loanContractEndDate") {
		ulElement = document.getElementById("loanContractEndDate");
	}
	else if (rowId === "depositStartDate") {
		ulElement = document.getElementById("depositStartDate");
		rowIndex = 0;
	}
	else {
		return; // 유효한 rowId가 아닌 경우 함수 종료
	}
	listItems = Array.from(ulElement.querySelectorAll('li'));
	
	listItems[0].classList.remove('selectedLi'); // '전체' 해제하고
	listItems[1].classList.add('selectedLi'); // '직접입력' 선택
}
