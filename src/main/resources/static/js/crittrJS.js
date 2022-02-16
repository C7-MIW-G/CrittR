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