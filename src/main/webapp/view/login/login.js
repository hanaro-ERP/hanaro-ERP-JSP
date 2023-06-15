const loginID = document.querySelector('#employeeId');

loginID.addEventListener("input", () => {
	const regex = /[^a-zA-Z0-9]/g;
    loginID.value = loginID.value.replace(regex, "");
});