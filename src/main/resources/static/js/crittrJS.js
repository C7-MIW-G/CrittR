function setButtons(currentStatus) {
    switch (currentStatus) {
        case 0 : document.getElementById('accept-form').style.display = 'block';
            document.getElementById('discard-form').style.display = 'block';
            break;
        case 1 : document.getElementById('observe-form').style.display = 'block';
            document.getElementById('close-form').style.display = 'block';
            break;
        case 2 : setReopenButton();
            break;
        case 3 : document.getElementById('close-form').style.display = 'block';
            setReopenButton();
            break;
        case 4 : setReopenButton();
            break;
    }
}

function setReopenButton() {
    document.getElementById('accept-form').style.display = 'block';
    document.getElementById('accept-button').value = 'Reopen';
}

function setErrorField() {
    const uniquenessError = document.getElementById('uniquenessError');
    if (uniquenessError.innerHTML === "") {
        uniquenessError.style.display = 'none'
    } else {
        uniquenessError.style.display = 'block'
    }
}

function checkPassword() {

    const message = document.getElementById('message');
    const buttonDisableableCollection = document.getElementsByClassName('buttonDisableable');
    const buttonDisableable = buttonDisableableCollection[0];
    if (document.getElementById('password').value ===
        document.getElementById('confirm_password').value) {
        message.style.visibility = 'hidden';
        buttonDisableable.disabled = false;
        buttonDisableable.classList.replace("btn-secondary", "btn-primary");

    } else {
        message.style.visibility = 'visible';
        buttonDisableable.disabled = true;
        buttonDisableable.classList.replace("btn-primary", "btn-secondary");
    }
}