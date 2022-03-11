const animalListInput = $('#AnimalNameInput')

animalListInput.select2({
    placeholder: 'Leave empty if unknown',
    allowClear: true,
    readonly: false,
    tags: true
})


function filterAnimalBox() {
    const searchObject = {};
    searchObject['keyword'] = "";

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/animals/search",
        data: JSON.stringify(searchObject),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            for (const dto of data.dtos) {
                    let option = new Option(dto.name + ' (' + dto.species + ')', dto.animalId, false, false)
                    animalListInput.append(option).trigger('change');
            }
        }
    })
}


