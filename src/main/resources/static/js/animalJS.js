let chosenSpecies = "";
let chosenStatus = null;

$('.button-column li').click(function() {
    $(this).parent().find('li.active').removeClass('active');
    $(this).addClass('active');

    let speciesContent = document.getElementById('species-list')
        .getElementsByClassName('active');

    if(speciesContent.length > 0){
            chosenSpecies = speciesContent[0].getElementsByTagName('a')[0].innerHTML;
    }

    let statusContent = document.getElementById('status-list').getElementsByClassName('active');
    if(statusContent.length > 0) {
            chosenStatus = statusContent[0].getElementsByTagName('a')[0].getAttribute('value');
        }
    searchAnimals(chosenStatus, chosenSpecies);
})

$('#all-animals-button').click(function(){
    $('.button-column li').removeClass('active');
    chosenSpecies = "";
    chosenStatus = null;
    searchAnimals(chosenStatus, chosenSpecies);
})

function searchAnimals(status, keyword) {
    const searchObject = {}
    searchObject['status'] = status;
    searchObject['keyword'] = keyword
    doAnimalSearch(searchObject);
}

function doAnimalSearch(searchObject) {
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
            } else if (pageTitle[0].innerHTML == "Account Details") {
                data.msg = "You have no favourite animals yet ...";
                data.dtos = data.dtos.filter((item) => item.favourited === true)
                innerhtml = buildHtmlStringAnimal(data);
            } else {
                innerhtml = buildHtmlStringAnimalCaretaker(data);
            }

            tBody.empty();
            tBody.append(innerhtml);
            for (const dto of data.dtos) {
                if(dto.favourited){
                    const heartId = '#heart-img-' + dto.animalId;
                    $(heartId).toggleClass("heart-img-clicked");
                }
            }
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
        htmlString += '<div class="card col-lg-5 mt-4 animal-details-info-table-header overview-card justify-content-center shadow" style="width: 18rem">' +
            '<img class="card-img rounded-circle shadow ms-3" src="' + photo + '" >' +
            '<div class="card-body">' +
            '<div style="z-index: 2; position: relative; left: 40%" class="w-25"> ' +
            '<input class="heart-img mt-2" id="heart-img-' + animalId + '" type="image" src="/assets/heart-fill.svg" alt="FavouriteHeart" onclick="favouriteToggle(' + animalId + ')"/> ' +
            '</div>' +
            '<h4 class="card-title">' + name + " the " + species + '</h4>' +
            '<a style="z-index: 1" href="/animals/details/' + animalId + '"' +
            ' class="stretched-link"></a>' +
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
