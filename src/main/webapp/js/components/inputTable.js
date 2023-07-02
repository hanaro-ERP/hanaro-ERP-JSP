function validateForm() {
    const inputs = document.querySelectorAll('input');
    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].type !== 'hidden' && inputs[i].value === '') {
            if (inputs[i].disabled) {
            	return true;
        	}
            alert('모든 항목을 입력해주세요.');
            return false;
        }
        
        if(inputs[i].id === 'searchResultMessage' && inputs[i].value === '') {
        	alert('검색을 완료해주세요');
        	return false;
        }
    }
    return true;
}
