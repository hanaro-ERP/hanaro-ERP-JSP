
generateMenu('loan');

// selectItems 함수 호출
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
	const monthSelectElements = document.getElementsByClassName("monthSelect");
	
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
	// 일 설정
	const daySelect = document.getElementsByClassName("daySelect"); 
	
	// 선택한 월에 따라 endDay 다르게
	let endDay;
	
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


function selectMonth() {
	const monthSelect = document.getElementsByClassName("monthSelect");
	 
	if (monthSelect.length > 0) {	
		Array.from(monthSelect).forEach(monthSelect => {
			monthSelect.addEventListener("change", function (event) {
			    const selectedMonth = event.target.value;
			    setDaySelect(selectedMonth);
	 		});
		});
	}
}

// 초기 선택된 월 값으로 setDateSelect() 호출
const initialMonth = "1";
setYearSelect();
setMonthSelect();
setDaySelect(initialMonth);


// 다른 것 선택하면 이미 선택되었던 것 해제
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
