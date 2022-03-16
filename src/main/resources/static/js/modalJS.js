// Add fact modal scripts

const modal = document.getElementById("modal");

$("#modalButton").click(function(){
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

// // Delete account confirmation modal scripts
//
// const deleteAccModal = document.getElementById("deleteAccountModal");
//
// $("#deleteAccountButton").click(function(){
//     deleteAccModal.style.display = "block";
// })
//
//
// $(".close-delconfirm-modal-x").click(function() {
//     deleteAccModal.style.display = "none";
// })
//
// $(".delconfirm-modal-cancel-button").click(function() {
//     deleteAccModal.style.display = "none";
// })
//
// window.onclick = function(event) {
//     if (event.target == deleteAccModal) {
//         deleteAccModal.style.display = "none";
//     }
// }