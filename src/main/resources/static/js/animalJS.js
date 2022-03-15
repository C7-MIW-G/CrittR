let chosenSpecies = "";
let chosenStatus = null;

function setChosenSpecies(element) {
    let clickedSpecies = element.find('a').html();
    if (chosenSpecies !== clickedSpecies) {
        chosenSpecies = clickedSpecies;
    } else {
        chosenSpecies = "";
       element.removeClass('active')
    }
}

function setChosenStatus(element) {
    let clickedStatus = element.find('a').attr('value');
    if (chosenStatus !== clickedStatus) {
        chosenStatus = clickedStatus;
    } else {
        chosenStatus = null;
        element.removeClass('active');
    }
}

$('#species-list li').click(function() {
    $('#species-list').find('li.active').removeClass('active');
    $(this).addClass('active')

    setChosenSpecies($(this));
    searchAnimals(chosenStatus, chosenSpecies);
})

$('#status-list li').click(function() {
    $('#status-list').find('li.active').removeClass('active');
    $(this).addClass('active');

    setChosenStatus($(this));
    searchAnimals(chosenStatus, chosenSpecies);
})

$('#all-animals-button').click(function(){
    $('.filter-column li').removeClass('active');
    chosenSpecies = "";
    chosenStatus = null;
    searchAnimals(chosenStatus, chosenSpecies);
})

$('#animal-search-input').focus(function() {
    $('.filter-column li').removeClass('active');
})

function searchAnimals(status, keyword) {
    const searchObject = {
        status: status,
        keyword: keyword
    }

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
            setHearts(data);
        },
        error: function () {
            $('#animalsTable').append(
                '<tr><td>Oops, something went wrong</td></tr>'
            )
        }
    })
}

function setHearts(data) {
    data.dtos.forEach(dto => {
        if(dto.favourited){
        const heartId = '#heart-img-' + dto.animalId;
        $(heartId).toggleClass("heart-img-clicked");
        const heartOutline = '#heart-outline-' + dto.animalId;
        $(heartOutline).toggleClass('heart-img-clicked');
    }})
}

function buildHtmlStringAnimal(data) {
    let htmlString = "";
    if(data.dtos.length == 0) {
        htmlString +=
            '<tr style="position: relative"><td>' + data.msg + '</td></tr>'
        return htmlString;
    }

    data.dtos.forEach(dto => htmlString +=
        `<div class="card mt-4 overview-card justify-content-center shadow">` +
        `<img class="card-img rounded-circle shadow" src="data:image/jpeg;base64,${dto.picture}">` +
        `<div class="card-body">` +
        `<div style="z-index: 2; position: relative; left: 40%" class="w-25">`+
        `<label>` +
        `<input class="heart-img mt-2" id="heart-img-${dto.animalId}" type="image" src="/assets/heart-fill.svg" alt="FavouriteHeart" onclick="favouriteToggle(${dto.animalId})"/> ` +
        `<div class="heart-overlay"><img class="heart-img mt-2 heart-outline" id="heart-outline-${dto.animalId}" src="/assets/heart.svg" alt="heart outline"/></div>` +
        `</label>` +
        `</div>` +
        `<h4 class="card-title">${dto.name} the ${dto.species} </h4>` +
        `<a style="z-index: 1" href="/animals/details/${dto.animalId}"` +
        `class="stretched-link"></a>` +
        `</div>` +
        `</div>`);

    return htmlString;
}

function buildHtmlStringAnimalCaretaker(data) {
    let htmlString = "";
    if(data.dtos.length == 0) {
        htmlString +=
            '<tr style="position: relative"><td>' + data.msg + '</td></tr>'
        return htmlString;
    }

    data.dtos.forEach(dto => htmlString +=
    `<tr style="position: relative">` +
    `<td><a class="stretched-link hyperlink-no-styling"` +
    ` href="/caretaker/animals/details/${dto.animalId}"></a>${dto.species}</td>` +
    `<td>${dto.name}</td>` +
    `<td>${dto.age}</td>` +
    `<td>${dto.status}</td></tr>`)

    return htmlString;
}

function setButtonColours(animalStatus) {
   switch(animalStatus){
       case 0 : $('#btn-incoming').addClass('btn-primary');
           break;
       case 1 : $('#btn-present').addClass('btn-primary');
            break;
       case 2 : $('#btn-relocated').addClass('btn-primary')
           break;
       case 3 : $('#btn-deceased').addClass('btn-primary');
            break;
   }
}
