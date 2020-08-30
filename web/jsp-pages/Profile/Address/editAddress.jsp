<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add address</title>
    <link rel="shortcut icon" href="">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,400;0,500;0,700;1,300&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userProfile.css">
</head>
<body>
<jsp:include page="../../components/header.jsp"/>
<div class="profile">

    <div class="container">

        <div class="profile__wrapper">

            <jsp:include page="../../components/navProfile.jsp"/>

            <div class="profile__block info" style="min-height: 700px;">

                <div class="profile__block__title">Змінити адресу</div>
                <div class="profile__block__descr">
                    Оновіть вашу адресу, і в подальшому всі ярлики для доставки будуть автоматично відображати зазначену нижче адресу
                </div>

                <form id="form" method="post" style="margin-top: 40px;">

                    <label class="info__form__label" style="margin-top: 20px;">
                        <div class="info__form__label_text">Країна:</div>
                        <input id="countryEdit" type="text" value="${address.country}">
                    </label>

                    <label class="info__form__label">
                        <div class="info__form__label_text">Місто:</div>
                        <input id="cityEdit" type="text" value="${address.city}">
                    </label>

                    <label class="info__form__label">
                        <div class="info__form__label_text">Вулиця:</div>
                        <input id="streetEdit" type="text" value="${address.street}">
                    </label>

                    <label class="info__form__label">
                        <div class="info__form__label_text">Поштовий індекс:</div>
                        <input id="postCodeEdit" type="text" value="${address.postCode}">
                    </label>

                    <button onclick="validateAddress()" id="button" disabled class="info__form__button" type="submit">Зберегти зміни</button>

                    <c:if test="${canDelete}">
                        <button onclick="deleteAddress(${address.idAddress})" class="info__form__button active_button delete">Видалити адресу</button>
                    </c:if>

                </form>

            </div>

        </div>

    </div>

</div>
<div class="overlay" id="overlayUpdateUser">
    <div class="modalUser" id="updateUser">
        <div class="modalUser__close">&times;</div>
        <div class="modalUser__subtitle">Оновлення адреси</div>
        <p class="modalUser__descr">Вашу адресу успішно оновлено</p>
    </div>
</div>
<jsp:include page="../../components/footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/deleteAddress.js"></script>
<script>

    $(document).ready(function () {

        $('.modalUser__close').on('click', function () {
            window.location.href = "${pageContext.request.contextPath}/userProfile/userAddresses"
        });
    });

    function validateAddress() {

        let success = false;

        $.ajax({

            url: "/userProfile/userAddresses/editAddress" +
                "?country=" + document.getElementById("countryEdit").value +
                "&city=" + document.getElementById("cityEdit").value +
                "&street=" + document.getElementById("streetEdit").value +
                "&postCode=" + document.getElementById("postCodeEdit").value +
                "&id=${address.idAddress}",

            async: false,
            type: "PUT",

        }).done(function () {

            success = true;
            $('#overlayUpdateUser, #updateUser').fadeIn('slow');

        }).fail(function (response) {

            success = false;
            if (response.status === 500)
                alert("Не вдалось додати нову адресу, проблеми на сервері");

        });

        return success;

    }

    function checkChanges() {

        let country = document.getElementById("countryEdit").value;
        let city = document.getElementById('cityEdit').value;
        let street = document.getElementById('streetEdit').value;
        let postCOde = document.getElementById('postCodeEdit').value;

        let countryAttribute = "${address.country}";
        let cityAttribute = "${address.city}";
        let streetAttribute = "${address.street}";
        let postCodeAttribute = "${address.postCode}";

        return !(country === countryAttribute && city === cityAttribute &&
            street === streetAttribute && postCOde === postCodeAttribute);

    }

    setInterval(function () {

        let button = document.querySelector("#button");

        if (checkChanges() === true) {

            $("#button").removeAttr("disabled");
            button.classList.add("active_button");

        } else {
            $("#button").attr("disabled", "disabled");
            button.classList.remove("active_button");

        }

    }, 1)

</script>
</body>
</html>
