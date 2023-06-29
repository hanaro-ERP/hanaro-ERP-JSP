selectMultiItemsWithDirectInput("loanContractStartDate");
selectMultiItemsWithDirectInput("loanContractEndDate");
selectOneItemWithDirectInput("balanceList");
selectOneItemWithDirectInput("latePayment");

setYearSelect("loanContractStartDate");
setYearSelect("loanContractEndDate");
setMonthSelect();
setDaySelect(true);
changeDate();

const searchTableRows = document.querySelectorAll('#loanSearchTable tr');

searchTableRows.forEach((item, index) => {
	if (index !== 0) {
		var idTd = item.querySelector(".loanContractId");
		var loanName = item.querySelector(".loanName");
		var customerName = item.querySelector(".customerName");
		item.addEventListener('click', () => {
			var idValue = idTd.innerHTML;
			var loanNameValue = loanName.innerHTML;
			var customerNameValue = customerName.innerHTML;

			window.open("/hanaro-ERP-JSP/loanContracts/repaymentList?id=" + idValue + "&loan=" + loanNameValue + "&customer=" + customerNameValue + "&page=1", "_blank", "width=1000,height=600");
		});
	}
});