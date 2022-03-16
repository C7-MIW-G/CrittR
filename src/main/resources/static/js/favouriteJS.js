function favouriteToggle(animalId){
    const requestBody = {
        animalId: animalId
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/animals/favourite",
        data: JSON.stringify(requestBody),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function(data){
            if(!data.loggedIn) {
            window.location.href = '/login';
            } else {
                const heartId = "#heart-img-" + animalId;
                $(heartId).toggleClass("heart-img-clicked");
                const heartOutline = '#heart-outline-' + animalId;
                $(heartOutline).toggleClass('heart-img-clicked');
            }
        },
        error: function () {
            console.log("something went wrong")
        }
    })
}