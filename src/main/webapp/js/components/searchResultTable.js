function scrollToBottom() {
	var resultTable = document.querySelector(".scrollTo");
	resultTable.scrollIntoView({ behavior: "smooth", block: "start" });
}

function scrollToTop() {
	window.scrollTo({
		top: 0,
		behavior: 'smooth'
	});
}
