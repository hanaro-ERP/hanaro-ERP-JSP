function validateForm() {
    const inputs = document.querySelectorAll('input');
    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].type !== 'hidden' && inputs[i].value === '') {
            alert('모든 항목을 입력해주세요.');
            return false;
        }
    }
    return true;
}
