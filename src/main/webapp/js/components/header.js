window.addEventListener('DOMContentLoaded', function() {
  var menuItems = document.querySelectorAll('header ul a');
  var currentAnchor = window.location.pathname;
  
  const menus = ["customer", "employee", "loan", "deposit"];
  
  menuItems.forEach((item, index) => {
	  if (currentAnchor.includes(menus[index])) {
		  menuItems[index].classList.add('active');
	  } 
  })
});