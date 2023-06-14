generateMenu('loan');
selectItems("loanContractStartDate");
selectItems("loanContractEndDate"); 
selectItems("balanceList");
selectItems("loanContractLimit"); 

function setYearSelect(){
	// 연도 설정
	const yearSelect = document.getElementsByClassName("yearSelect"); 
	const currentYear = new Date().getFullYear();
		
	// 기존 옵션 제거
	Array.from(yearSelect).forEach(yearSelect => {
		yearSelect.innerHTML = "";
	});
	
	Array.from(yearSelect).forEach(yearSelect => {
		for (let year = 1990; year <= currentYear; year++) {
			const option = document.createElement("option");
		  	option.value = year;
		  	option.textContent = year;
		  	yearSelect.appendChild(option);
		}	
	});	
}

function setMonthSelect(){
	// 월 설정
	const monthSelectElements = document.getElementsByClassName("monthSelect")
	
	// 기존 옵션 제거
	Array.from(monthSelectElements).forEach(monthSelectElement => {
	  monthSelectElement.innerHTML = "";
	});
	
	Array.from(monthSelectElements).forEach(monthSelectElement => {
 		for (let month = 1; month <= 12; month++) {
	    	const option = document.createElement("option");
	      	option.value = month;
	      	option.textContent = month;
	      	monthSelectElement.appendChild(option);
	    }
	});
}

function setDaySelect(monthSelect){
	const daySelect = document.getElementsByClassName("daySelect");	// 일 설정
	let endDay;	// 선택한 월에 따라 endDay 다르게
	
	switch(parseInt(monthSelect)) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			endDay = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			endDay = 30;
			break;
		case 2:
			endDay = 29;
			break;
		default:
			endDay = 31;
			break;
	}
	
	// 기존 옵션 제거
  	Array.from(daySelect).forEach(daySelect => {
    	daySelect.innerHTML = "";
  	});
	
	Array.from(daySelect).forEach(daySelect => {
		for (let day = 1; day <= endDay; day++) {
		  const option = document.createElement("option");
		  option.value = day;
		  option.textContent = day;
		  daySelect.appendChild(option);
		}	
	});
	
	selectMonth();
}
// 초기 선택된 월 값으로 setDateSelect() 호출
const initialMonth = "1";
setYearSelect();
setMonthSelect();
setDaySelect(initialMonth);

// 월 선택에 따라 일 범위 바꾸기
function selectMonth() {
  	const monthSelect = document.getElementsByClassName("monthSelect");
  	let ulId;

  	if(monthSelect.length > 0) {
    	Array.from(monthSelect).forEach(monthSelect => {
      	// 클릭 되면
      	monthSelect.removeEventListener("change", handleMonthSelect); // 기존의 이벤트 리스너 제거
      	monthSelect.addEventListener("change", handleMonthSelect); // 새로운 이벤트 리스너 등록
    	});
  	}
}

// 이벤트 핸들러 함수
function handleMonthSelect(event) {
  	const monthSelect = event.target;
  	const liElement = monthSelect.closest('ul').querySelector('li');
  	const ulId = monthSelect.closest('ul').id;

  	if (ulId.includes("Start")) {
    	changeDateRowSelect("loanContractStartDate");
  	} 
  	else {
    	changeDateRowSelect("loanContractEndDate");
  	}
  	const selectedMonth = monthSelect.value;
  	setDaySelect(selectedMonth); // 일 범위 바꾸기
}

// 다른 것 선택하면 이미 선택되었던 것 해제
function selectItems(ulId) {	
	const ulElement = document.getElementById(ulId);
	const listItems = ulElement.querySelectorAll('li');
	const firstListItem = listItems[0];
	let previousListItem = firstListItem;
	firstListItem.classList.add('selectedLi');

	listItems.forEach((item, index) => {
		if (index !== 0) {
			item.addEventListener('click', () => {
				item.classList.toggle('selectedLi');
				previousListItem.classList.remove('selectedLi');
				previousListItem = item;				
			});
		}
	});
}

// 연월일 선택에 따라 '전체' -> '직접입력'
function changeDateRowSelect(monthSelectId) {
  	let ulElement, listItems;

  	if (monthSelectId === "loanContractStartDate") {
    	ulElement = document.getElementById("loanContractStartDate");
	} 
	else if (monthSelectId === "loanContractEndDate") {
		ulElement = document.getElementById("loanContractEndDate");
	} 
	else {
	  	return; // 유효한 monthSelectId가 아닌 경우 함수 종료
	}

  	listItems = Array.from(ulElement.querySelectorAll('li'));
  	listItems[0].classList.remove('selectedLi'); // '전체' 해제하고
  	listItems[1].classList.add('selectedLi'); // '직접입력' 선택
}

selectMonth(); // selectMonth 함수 호출
