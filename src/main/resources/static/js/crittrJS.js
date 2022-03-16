function setButtons(currentStatus) {
    console.log(currentStatus)
    switch (currentStatus) {
        case 0 : document.getElementById('accept-form').style.display = 'block';
            document.getElementById('discard-form').style.display = 'block';
            break;
        case 1 : document.getElementById('observe-form').style.display = 'block';
            document.getElementById('close-form').style.display = 'block';
            break;
        case 2 :
            setReopenButton();
            break;
        case 3 : document.getElementById('close-form').style.display = 'block';
            setReopenButton();
            break;
        case 4 :
            document.getElementById('assigned-to-tablerow').style.display = 'none';
            setReopenButton();
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
    const buttonDisableable = document.getElementsByClassName('buttonDisableable')[0];
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

$('body').on('load', setColourTheme())


function setColourTheme() {
    $.ajax({
        type: 'GET',
        contentType: "application/json",
        url: '/api/colours',
        dataType: 'json',
        cache: false,
        timeout : 600000,
        success: function(response) {
            if(response.caretaker) {
                $('body').addClass("style-caretaker");
            }
        }
    })
}

$(document).ready(function() {
    let currentURL = location.href.split('/');
    const links = $('.nav-text-link');

    for (const link of links) {
        let path = link.href.split('/');
        if(path[3] === currentURL[3]){
            link.classList.add("link-highlighted");
        }
    }
})




