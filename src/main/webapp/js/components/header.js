window.addEventListener('DOMContentLoaded', function() {
	var menuItems = document.querySelectorAll('header ul a');
	var currentAnchor = window.location.pathname;
	var previousPage = document.referrer;
	
	const menus = ["customer", "employee", "loan", "deposit"];

	menuItems.forEach((item, index) => {
		if (index === 1) {
			if (currentAnchor.includes(menus[index]) || currentAnchor.includes("bank")) {
				menuItems[index].classList.add('active');
			}
		} else {
			if (currentAnchor.includes(menus[index])) {
				menuItems[index].classList.add('active');
			}
		}
	})
});
