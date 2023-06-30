window.addEventListener('DOMContentLoaded', function() {
	var menuItems = document.querySelectorAll('header ul a');
	var currentAnchor = window.location.pathname;

	const menus = ["customer", "employee", "loan", "deposit"];

	menuItems.forEach((item, index) => {
		if (index === 1) {
			if (currentAnchor.includes(menus[index]) || currentAnchor.includes("bank")) {
				menuItems[index].classList.add('active');
			}
		} else if (index === 0) {
			if (window.location.search.includes("pageId=2")) {
			    menuItems[2].classList.add('active'); // Assuming the desired menu item index is 0
			} 	
		
		} else {
			if (currentAnchor.includes(menus[index])) {
				menuItems[index].classList.add('active');
			}
		}
	})
});
