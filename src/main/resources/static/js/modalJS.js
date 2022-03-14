const modal = document.getElementById("addFactModal");

$("#addFactButton").click(function(){
    modal.style.display = "block";
})

$(".close-modal-x").click(function() {
    modal.style.display = "none";
})

$(".modal-cancel-btn").click(function() {
    modal.style.display = "none";
})

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}