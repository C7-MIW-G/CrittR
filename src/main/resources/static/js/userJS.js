let chosenRole = null;

$('#role-list li').click(function (){
    $('#role-list').find('li.active').removeClass('active');
    $(this).addClass('active');

   setChosenRole($(this));
    searchUsers(chosenRole);
})

function setChosenRole(element) {
    if(chosenRole !== element.find('a').attr('data-id')){
        chosenRole = element.find('a').attr('data-id');
    } else {
        chosenRole = null;
        element.removeClass('active');
    }
}

$('#all-users-button').click(function (){
    $('#role-list li').removeClass('active');
    searchUsers(null);
})

$('#user-search-input').focus(function(){
    $('#role-list li').removeClass('active');
})

function searchUsers(role) {
    const searchObject = {
        email: $('#user-search-input').val(),
        role: role
    };

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
                '<tr><td>Oops, something went wrong</td></tr>')
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