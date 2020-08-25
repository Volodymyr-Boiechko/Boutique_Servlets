function addToShoppingBag(idProduct) {

    let success = false;

    $.ajax({

        url: '/shoppingBag',
        async: true,
        type: 'POST',
        data: {
            idProduct: idProduct

        }

    }).done(function () {

        alert('Продукт додано в корзину');
        success = true;
        location.reload();

    }).fail(function (response) {

        success = false;

        if (response.status === 401)
            alert('Потрібно увійти!');
        else if (response.status === 403)
            alert('Продукт вже доданий в корзину!');
        else if (response.status === 500)
            alert('Помилка на сервері');


    });

    return success;

}