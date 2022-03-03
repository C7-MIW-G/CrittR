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
        },
        error: function () {
            $('#accountsTable').append(
               '<tr><td>Oops, something went wrong</td></tr>'
               )
        }
    })
}

function buildHtmlString(data) {
    let htmlString = "";
    if(data.dtos.length == 0) {
        htmlString +=
            '<tr style="position: relative"><td>' + data.msg + '</td></tr>'
        return htmlString;
    }

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

function searchAnimals() {

    const searchObject = {};

    searchObject['keyword'] = $("#animal-search-input").val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/animals/search",
        data: JSON.stringify(searchObject),
        dataType: 'json',
        cache: false,
        timeout : 600000,
        success: function (data) {
            const tBody = $('#animalsTable');
            let innerhtml = '';
            const pageTitle = $('title');
            if (pageTitle[0].innerHTML == 'Animal overview'){
                innerhtml = buildHtmlStringAnimal(data);
            } else {
                innerhtml = buildHtmlStringAnimalCaretaker(data)
            }

            tBody.empty();
            tBody.append(innerhtml);
        },
        error: function () {
            $('#animalsTable').append(
                '<tr><td>Oops, something went wrong</td></tr>'
            )
        }
    })
}

function buildHtmlStringAnimal(data) {
    let htmlString = "";
    if(data.dtos.length == 0) {
        htmlString +=
            '<tr style="position: relative"><td>' + data.msg + '</td></tr>'
        return htmlString;
    }

    for (const dto of data.dtos) {
        const animalId = dto.animalId;
        const name = dto.name;
        const species = dto.species;
        const age = dto.age;
        let photo = 'data:image/jpeg;base64,'+ dto.picture;
        htmlString += '<div class="card" style="width: 18rem">' +
            '<img class="card-img-top rounded-circle" src="' + photo + '" width="5" height="240">' +
            '<div style="z-index: 2; position: relative;"><a label="Save as favourite animal" href="animals/addFavourite" type="button" class="btn btn-outline-secondary my-2" >' +
            '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" class="bi bi-heart" viewBox="0 0 16 16" fill="currentColour">' +
            '<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>' +
            '</svg></a></div>' +
               '<div class="card-body">' +
                    '<h2 class="card-title">' + name + '</h2>' +
                    '<p class="card-text">' + species + '</p>' +
                    '<p class="card-text">' + age + '</p>' +
                    '<a style="z-index: 1" href="/animals/details/' + animalId + '"' +
                    ' class="btn btn-primary stretched-link">See details</a>' +
                '</div>' +
        '</div>'
    }
    return htmlString;
}

function buildHtmlStringAnimalCaretaker(data) {
    let htmlString = "";
    if(data.dtos.length == 0) {
        htmlString +=
            '<tr style="position: relative"><td>' + data.msg + '</td></tr>'
        return htmlString;
    }

    for (const dto of data.dtos) {
        const animalId = dto.animalId;
        const name = dto.name;
        const species = dto.species;
        const age = dto.age;
        const status = dto.status;
        htmlString +=
            '<tr style="position: relative">' +
            '<td><a class="stretched-link hyperlink-no-styling"' +
            ' href="/caretaker/animals/details/' + animalId + '"></a>' + name + '</td>' +
            '<td>'+ species + '</td>' +
            '<td>'+ age + '</td>' +
            '<td>'+ status + '</td></tr>';
    }
    return htmlString;
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

