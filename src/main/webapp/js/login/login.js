const MINIMUM_ID_LENGTH = 1;
const MINIMUM_PW_LENGTH = 1;

const employeeId = document.querySelector('#employeeId');
const password = document.querySelector('#password');
const loginButton = document.querySelector('#loginSubmit');

let idLongEnough = false;
let pwLongEnough = false;

employeeId.addEventListener("input", () => {
	const regex = /[^0-9]/g;
	employeeId.value = employeeId.value.replace(regex, "");
	idLongEnough = employeeId.value.length >= MINIMUM_ID_LENGTH ? true : false;
	pwLongEnough = password.value.length >= MINIMUM_PW_LENGTH ? true : false;
	toggleLoginButton(idLongEnough, pwLongEnough);
});

password.addEventListener("input", () => {
	idLongEnough = employeeId.value.length >= MINIMUM_ID_LENGTH ? true : false;
	pwLongEnough = password.value.length >= MINIMUM_PW_LENGTH ? true : false;
	toggleLoginButton(idLongEnough, pwLongEnough);
});

function toggleLoginButton(idLongEnough, pwLongEnough){
	if (idLongEnough && pwLongEnough) {
		loginButton.classList.add('makeButtonGreen');
	} else {
		loginButton.classList.remove('makeButtonGreen');		
	}
}