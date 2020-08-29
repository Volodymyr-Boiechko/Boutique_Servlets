function deleteAddress(idAddress) {

    let success = false;

    $.ajax({

        url: '/userProfile/userAddresses?idAddress=' + idAddress,
        async: false,
        type: "DELETE"

    }).done(function () {

        success = true;
        alert('Адресу видалено');
        location.reload();

    }).fail(function (response) {

        success = false;

        if (response.status === 500)
            alert('Проблеми з сервером');

    });

    return success;

}