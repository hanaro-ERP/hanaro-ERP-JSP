selectMultiItems('depositType');
selectMultiItemsWithDirectInput("depositStartDate");
selectOneItemWithDirectInput('depositBalance');

setYearSelect();
setMonthSelect();
setDaySelect(true);
changeDate();

const searchTableRows = document.querySelectorAll('#depositSearchTable tr');

searchTableRows.forEach((item, index) => {
	if (index !== 0) {
		var idTd = item.querySelector(".accountId");
		var nameTd = item.querySelector(".accountName");
		var numberTd = item.querySelector(".accountNumber");
		item.addEventListener('click', () => {
			var idValue = idTd.innerHTML;
			var nameValue = nameTd.innerHTML;
			var numberValue = numberTd.innerHTML;

			window.open("/hanaro-ERP-JSP/depositList/searchTransactions?id=" + idValue + "&name=" + nameValue + "&number=" + numberValue, "_blank", "width=1000,height=600");
		});	
	}
});

function validateIdentification() {
    const identification1 = document.getElementById("identification1").value;
    const identification2 = document.getElementById("identification2").value;

    if (identification1 !== "" && identification1.length !== 6) {
        alert("주민번호 앞자리는 빈값이거나 6자리이어야 합니다");
        return false;
    } else if (identification2 !== "" && identification2.length !== 7) {
    	alert("주민번호 뒷자리는 빈값이거나 7자리이어야 합니다");
        return false;
    }
    	
    return true;
}