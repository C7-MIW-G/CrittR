const modal = document.getElementById("addFactModal");
const btn = document.getElementById("addFactButton");
const closeButton = document.getElementsByClassName("close-modal-x")[0];
const cancelButton = document.getElementsByClassName("modal-cancel-btn")[0];

btn.onclick = function() {
    modal.style.display = "block";
}

closeButton.onclick = function() {
    modal.style.display = "none";
}

cancelButton.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}