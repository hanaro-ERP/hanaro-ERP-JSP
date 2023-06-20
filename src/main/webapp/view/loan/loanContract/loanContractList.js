selectMultiItemsWithDirectInput("loanContractStartDate");
selectMultiItemsWithDirectInput("loanContractEndDate");
selectMultiItemsWithDirectInput("balanceList");
selectMultiItemsWithDirectInput("loanContractLimit");

let yearSelectList, monthSelectList, daySelectList;
let yearSelectRow, monthSelectRow, daySelectRow;
let yearSelect, monthSelect, daySelect;
let rowIndex = 0;

// 연도 설정
function setYearSelect() {
	yearSelectList = document.getElementsByClassName("yearSelect");
	const currentYear = new Date().getFullYear();

	Array.from(yearSelectList).forEach(yearSelectList => {
		for (let year = 1990; year <= currentYear; year++) {
			const option = document.createElement("option");
			option.value = year;
			option.textContent = year;
			yearSelectList.appendChild(option);
		}
	});
}

// 월 설정
function setMonthSelect() {
	monthSelect = document.getElementsByClassName("monthSelect");

	Array.from(monthSelect).forEach(monthSelect => {
		for (let month = 1; month <= 12; month++) {
			const option = document.createElement("option");
			option.value = month;
			option.textContent = month;
			monthSelect.appendChild(option);
		}
	});
}

// 일 설정
function setDaySelect(isInitial) {
	daySelectList = document.getElementsByClassName("daySelect");
	daySelectRow = document.getElementsByClassName("daySelect")[rowIndex];
	let endDay;	// 선택한 월에 따라 endDay 다르게

	if (isInitial) {
		yearSelectRow = document.getElementsByClassName("yearSelect")[rowIndex];
		monthSelectRow = document.getElementsByClassName("monthSelect")[rowIndex];
		yearSelectRow.value = 1990;
		monthSelectRow.value = 1;

		yearSelect = parseInt(yearSelectRow.value);
		monthSelect = parseInt(monthSelectRow.value);
		daySelect = parseInt(daySelectRow.value);
		endDay = new Date(yearSelect, monthSelect, 0).getDate();

		Array.from(daySelectList).forEach(daySelectRow => {
			daySelectRow.innerHTML = "";
		});
		Array.from(daySelectList).forEach(daySelectRow => {
			for (let day = 1; day <= endDay; day++) {
				const option = document.createElement("option");
				option.value = day;
				option.textContent = day;
				daySelectRow.appendChild(option);
			}
		});
	}
	else {
		endDay = new Date(yearSelect, monthSelect, 0).getDate();
		daySelectRow.innerHTML = "";
		for (let day = 1; day <= endDay; day++) {
			const option = document.createElement("option");
			option.value = day;
			option.textContent = day;
			daySelectRow.appendChild(option);
		}
	}
}

// 선택에 따라 일 범위 바꾸기
function changeDate() {
	yearSelectList = document.getElementsByClassName("yearSelect");
	monthSelectList = document.getElementsByClassName("monthSelect");
	let ulId;

	if (yearSelectList.length > 0) {
		Array.from(yearSelectList).forEach(yearSelectRow => {
			yearSelectRow.removeEventListener("change", (event) => handleDateSelect(event, true));
			yearSelectRow.addEventListener("change", (event) => handleDateSelect(event, true));
		});
	}

	if (monthSelectList.length > 0) {
		Array.from(monthSelectList).forEach(monthSelectRow => {
			monthSelectRow.removeEventListener("change", (event) => handleDateSelect(event, false));
			monthSelectRow.addEventListener("change", (event) => handleDateSelect(event, false));
		});
	}
}

function handleDateSelect(event, isYear) {
	const selectedDate = event.target;
	const liElement = selectedDate.closest('ul').querySelector('li');
	const ulId = selectedDate.closest('ul').id;

	if (ulId.includes("Start")) {
		changeDateRowSelect("loanContractStartDate");
	}
	else {
		changeDateRowSelect("loanContractEndDate");
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
	let ulElement, listItems;

	if (rowId === "loanContractStartDate") {
		ulElement = document.getElementById("loanContractStartDate");
		rowIndex = 0;
	}
	else if (rowId === "loanContractEndDate") {
		ulElement = document.getElementById("loanContractEndDate");
		rowIndex = 1;
	}
	else {
		return; // 유효한 rowId가 아닌 경우 함수 종료
	}

	listItems = Array.from(ulElement.querySelectorAll('li'));
	listItems[0].classList.remove('selectedLi'); // '전체' 해제하고
	listItems[1].classList.add('selectedLi'); // '직접입력' 선택
}

setYearSelect();
setMonthSelect();
setDaySelect(true);
changeDate();
