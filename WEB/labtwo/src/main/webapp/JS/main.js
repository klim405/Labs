let textInput = document.getElementById('textInput');
textInput.oninput = validateForm;

function validateForm() {
    let btn = document.getElementById('submitBtn'),
        textInput = document.getElementById('textInput'),
        inputVal = textInput.value;

    inputVal = inputVal.replace(',', '.');
    if (inputVal.match(/^-?\d(\.\d{1,8})?$/g) && -3 <= inputVal && inputVal <= 3) {
        textInput.classList.remove("wrong")
        textInput.classList.add("correct")
        btn.removeAttribute('disabled');
    } else {
        textInput.classList.remove("correct")
        textInput.classList.add("wrong")
        btn.setAttribute('disabled', 'true');
    }
}