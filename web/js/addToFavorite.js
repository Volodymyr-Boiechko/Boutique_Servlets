function addToFavorite(idProduct) {

    let success = false;

    $.ajax({

        url: "/favorite",
        async: true,
        method: "POST",
        data: {

            idProduct: idProduct

        }
    }).done(function (status) {

        success = true;
        let item = document.getElementById(idProduct);

        let img = item.querySelector('#favorite');

        if (status === "add") {

            alert("Продукт додано до улюблених");
            img.src = window.location.origin + "/img/other/favoriteFull.png";
        } else if (status === "remove") {

            alert("Продукт видалено з улюблених");
            img.src = "../img/other/favorite.png";

            let index = array.indexOf(idProduct);
            if (index > -1) {
                array.splice(index, idProduct);
            }

        }

    }).fail(function (response) {

        success = false;

        if (response.status === 401)
            alert("Потрібно увійти");
        else if (response.status === 500)
            alert("Проблеми з сервером");

    });

    return success;

}