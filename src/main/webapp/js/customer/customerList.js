const customerDetailSelect = document.getElementById('customerDetailButton');
const customerDetailInformation = document.getElementById('customerDetailInformation');
const checkOpen = document.getElementById('checkOpen');

checkOpen.style.display = 'none'
customerDetailInformation.style.display = 'none'; // 초기에 숨김 상태로 설정

function revealDetail() {
	checkOpen.setAttribute('value', 'open');
	customerDetailInformation.style.display = 'block';
	customerDetailSelect.innerHTML = '▼';
}

function concealDetail() {
	checkOpen.setAttribute('value', 'close');
	customerDetailInformation.style.display = 'none';
	customerDetailSelect.innerHTML = '▲';
}

customerDetailSelect.addEventListener('click', function() {
	if (customerDetailInformation.style.display === 'none') {
		revealDetail();
	} else {
		concealDetail();
	}
});

selectMultiItemsWithDirectInput('customerAge');
selectMultiItems('customerGender');
selectMultiItems('customerGrade');
selectMultiItems('customerCredit');

const searchTableRows = document.querySelectorAll('#customerSearchTable tr');

searchTableRows.forEach((item, index) => {
	if (index !== 0) {
		var firstTd = item.querySelector(".customerId");
		item.addEventListener('click', () => {
			var value = firstTd.innerHTML;
			window.open("/hanaro-ERP-JSP/customerDetail?id=" + value, "_blank", "width=1000,height=600");
		});	
	}
});
