let chosenSpecies = "";
let chosenStatus = null;
const animalSearch = $('#animal-search-input');

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
    resetFilterCriteria()
    searchAnimals(chosenStatus, chosenSpecies);
})

animalSearch.focus(function() {
    resetFilterCriteria();
    $('.filter-column li').removeClass('active');
})

animalSearch.on('input', function (){
    searchAnimals(null, animalSearch.val());
})

$('#animal-overview-body').on('load', searchAnimals(null, ""));

function resetFilterCriteria() {
    chosenStatus = null;
    chosenSpecies = "";
}

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
            const pageTitle = $('title');
            tBody.empty();
            if (pageTitle[0].innerHTML == 'Animal overview'){
                tBody.append(buildHtmlStringAnimal(data));
            } else if (pageTitle[0].innerHTML == "Account Details") {
                data.msg = "You have no favourite animals yet ...";
                data.dtos = data.dtos.filter((item) => item.favourited === true)
                tBody.append(buildHtmlStringAnimal(data));
            } else {
                tBody.append(buildHtmlStringAnimalCaretaker(data))
            }
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
        $('.animal-overview-body #heart-img-' + dto.animalId).toggleClass("heart-img-clicked");
        $('.animal-overview-body #heart-outline-' + dto.animalId).toggleClass('heart-img-clicked');
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
        `<div class="position-relative">` +
        `<img class="card-img rounded-circle shadow block" src="data:image/jpeg;base64,${dto.picture}">` +
        `<div class="w-25 heart-button" style="position: absolute; top:0; right:0;">`+
        `<label>` +
        `<input class="heart-img mt-2" id="heart-img-${dto.animalId}" type="image" src="/assets/heart-fill.svg" alt="FavouriteHeart" onclick="favouriteToggle(${dto.animalId})"/> ` +
        `<div class="heart-overlay"><img class="heart-img mt-2 heart-outline" id="heart-outline-${dto.animalId}" src="/assets/heart.svg" alt="heart outline"/></div>` +
        `</label>` +
        `</div>` +
        `</div>` +
        `<div class="card-body">` +
        `<h4 class="card-title pt-3">${dto.name} the ${dto.species} </h4>` +
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
       case 0 : $('#btn-incoming').addClass('btn-secondary');
           break;
       case 1 : $('#btn-present').addClass('btn-secondary');
            break;
       case 2 : $('#btn-relocated').addClass('btn-secondary')
           break;
       case 3 : $('#btn-deceased').addClass('btn-secondary');
            break;
   }
}
