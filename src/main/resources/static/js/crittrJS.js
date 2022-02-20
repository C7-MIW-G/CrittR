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

$('body').addEventListener('load', searchUsers());

function searchUsers() {

    const searchObject = {};

    searchObject['email'] = $("#user-search-input").val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/users/search",
        data: JSON.stringify(searchObject),
        dataType: 'json',
        cache: false,
        timeout : 600000,
        success: function (data) {
            const tBody = $('#accountsTable');
            const innerhtml = buildHtmlString(data);
            tBody.empty();
            tBody.append(innerhtml);
            // for (let dto of data.dtos) {
            //     // appendTableRows(dto, tBody)
            // }
        }//,
        // error: function (e) {
        //     console.log(e.responseText);
        // }
    })

}
function buildHtmlString(data) {
    let htmlString = "";
    for (const dto of data.dtos) {
        const username = dto.username;
        const email = dto.email;
        const role = dto.role;
        htmlString +=
            '<tr style="position: relative">' +
            '<td><a class="stretched-link hyperlink-no-styling"' +
            ' href="/user/details/edit/' + dto.userId + '"></a>' + email + '</td>' +
            '<td>'+ username + '</td>' +
            '<td>'+ role + '</td></tr>'
        ;
    }
    return htmlString;
}

// function appendTableRows(dto, tBody) {
//     let username = dto.username;
//     let email = dto.email;
//     let role = dto.role;
//     tBody.append(
//         '<tr style="position: relative">' +
//         '<td><a class="stretched-link hyperlink-no-styling"' +
//         ' href="/user/details/edit/' + dto.userId + '"></a>' + email + '</td>' +
//         '<td>'+ username + '</td>' +
//         '<td>'+ role + '</td></tr>'
//     );
// }