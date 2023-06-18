const menuData = {
 	customer: {
		menuTitle: '고객 관리',
		menuIcon: 'customerBtnIcon',
		menuItems: [
			{ id: 'customerList', label: '고객 목록', link: '/dashboard' }
		]
	},
	employee: {
		menuTitle: '직원 관리',
		menuIcon: 'employeeBtnIcon',
		menuItems: [
			{ id: 'employeeList', label: '직원 목록', link: '/dashboard' },
			{ id: 'bankList', label: '지점 목록', link: '/profile' }
		]
	},
  	loan: {
		menuTitle: '여신 관리',
		menuIcon: 'loanBtnIcon',
		menuItems: [
			{ id: 'loanProductList', label: '여신 상품', link: '/dashboard' },
			{ id: 'loanHistory', label: '여신 이력', link: '/profile' },
			{ id: 'productRegistration', label: '상품 등록', link: '/settings' },
			{ id: 'productSubscription', label: '상품 가입', link: '/settings' }
		]
	},
	deposit: {
		menuTitle: '수신 관리',
		menuIcon: 'depositBtnIcon',
		menuItems: [
			{ id: 'depositProductList', label: '계좌 목록', link: '/dashboard' }
		]
	}
};
	
function generateMenu(page, subPage) {
	const pageData = menuData[page]; // 해당 페이지에 대한 메뉴 데이터 가져오기
	const pageTitle = pageData.menuTitle; // 페이지 제목 가져오기
	const menuItems = pageData.menuItems; // 해당 페이지의 메뉴 데이터 가져오기
	const menuElement = document.querySelector('.asideMenuContainer'); // 메뉴를 표시할 요소 선택
	const pageTitleElement = document.querySelector('.asideTitleContainer p'); // 페이지 제목을 표시할 요소 선택
	const menuIconElement = document.querySelector('.asideTitleContainer img'); // 아이콘을 표시할 요소 선택

	while (menuElement.firstChild) {
		menuElement.firstChild.remove();
	}

	menuItems.forEach(item => {
		const menuItem = document.createElement('li');
		const link = document.createElement('a');
		link.href = item.link;
		link.textContent = item.label;
		
		if (item.id === subPage) menuItem.setAttribute('id', 'selectedMenuLi');
		
		menuItem.appendChild(link);
		menuElement.appendChild(menuItem);
	});

	pageTitleElement.textContent = pageTitle;
	menuIconElement.src = `../../../public/images/${pageData.menuIcon}.svg`;
}
