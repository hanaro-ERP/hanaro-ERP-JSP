window.addEventListener('DOMContentLoaded', function() {
	var menuItems = document.querySelectorAll('header ul a');
	var currentAnchor = window.location.pathname;
	console.log(currentAnchor);
	const menus = ["customer", "employee", "loan", "deposit"];

	menuItems.forEach((item, index) => {
		if (index === 1) {
			if (currentAnchor.includes(menus[index]) || currentAnchor.includes("bank")) {
				menuItems[index].classList.add('active');
			}
		} else if (index === 2) {
			if (currentAnchor.includes(menus[index]) || currentAnchor.includes("customer/searchReturn")) {
				menuItems[index].classList.add('active');
			}
		} else if (index === 3) {
			if (currentAnchor.includes(menus[index]) || currentAnchor.includes("depositFind")) {
				menuItems[index].classList.add('active');
			}
		} else {
			if (currentAnchor.includes(menus[index])) {
				menuItems[index].classList.add('active');
			}
		}
	})
});
