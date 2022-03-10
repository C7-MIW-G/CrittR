const animalTextInput = $('#animalInput');
const animalListInput = $('#AnimalNameInput')

animalTextInput.focusin(function() {
    const input = $('#AnimalNameInput');
    animalListInput.css('display', 'block');
    filterAnimalBox();
})

function filterAnimalBox() {
    const searchObject = {};
    searchObject['keyword'] = animalTextInput.val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/animals/search",
        data: JSON.stringify(searchObject),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            let innerhtml = buildOptions(data)
            animalListInput.empty();
            animalListInput.append(innerhtml);
        }
    })
}

function buildOptions(data) {
    let optionString = "";
    for (const dto of data.dtos) {
        optionString += '<option value="' + dto.animalId + '">' + dto.name + ' (' + dto.species + ')</option>'
    }
    return optionString;
}
