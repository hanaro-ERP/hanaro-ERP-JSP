function validateLimit() {
	const loanMinLimit = document.getElementById('loanMinLimitId');
	const loanMaxLimit = document.getElementById('loanMaxLimitId');

	if (parseInt(loanMinLimit.value) > parseInt(loanMaxLimit.value)) {
		alert('최소 금액이 최대 금액보다 클 수 없습니다.');
		return false;
	}
	return true;
}

function validatePeriod() {
	const loanMinPeriod = document.getElementById('loanMinPeriodId');
	const loanMaxPeriod = document.getElementById('loanMaxPeriodId');
	
	if (parseInt(loanMinPeriod.value) > parseInt(loanMaxPeriod.value)) {
		alert('최소 기간이 최대 기간보다 길 수 없습니다.');
		return false;
	}
	return true;
}

function validateRate() {
	const loanMinRate = document.getElementById('loanMinRateId');
	const loanMaxRate = document.getElementById('loanMaxRateId');
	
	if (parseFloat(loanMinRate.value) > parseFloat(loanMaxRate.value)) {
		alert('최소 이율이 최대 이율보다 클 수 없습니다.');
		return false;
	}
	return true;
}

function registerForm() {
	if (!validateLimit()) {
		return false;
	}
	if (!validatePeriod()) {
		return false;
	}
	if (!validateRate()) {
		return false;
	}
	if (!validateForm()) {
		return false;
	}
	return true;
}
