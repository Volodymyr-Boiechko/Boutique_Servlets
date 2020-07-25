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

                <div class="profile__block__title">Добавити нову адресу</div>
                <div class="profile__block__descr">
                    Будь ласка, введіть адресу, на яку повинна бути здійснена доставка
                </div>

                <form id="form" method="post" style="margin-top: 40px;">

                    <label class="info__form__label" style="margin-top: 20px;">
                        <div class="info__form__label_text">Країна:</div>
                        <input id="countryAdd" type="text">
                    </label>

                    <label class="info__form__label">
                        <div class="info__form__label_text">Місто:</div>
                        <input id="cityAdd" type="text">
                    </label>

                    <label class="info__form__label">
                        <div class="info__form__label_text">Вулиця:</div>
                        <input id="streetAdd" type="text">
                    </label>

                    <label class="info__form__label">
                        <div class="info__form__label_text">Поштовий індекс:</div>
                        <input id="postCodeAdd" type="text">
                    </label>

                    <button id="button" class="info__form__button active_button" type="submit">Зберегти адресу</button>

                </form>

            </div>

        </div>

    </div>

</div>
<div class="overlay" id="overlayUpdateUser">
    <div class="modalUser" id="updateUser">
        <div class="modalUser__close">&times;</div>
        <div class="modalUser__subtitle">Добавлення адреси</div>
        <p class="modalUser__descr">Нову адресу успішно добавлено до вашого профілю</p>
    </div>
</div>
<jsp:include page="../../components/footer.jsp"/>
<script>

    $(document).ready(function () {

        $('.modalUser__close').on('click', function () {
            window.location.href = "${pageContext.request.contextPath}/userProfile/userAddresses"
        });
    });

    document.getElementById("form").onsubmit = function () {
        return validate();
    }

    function validate() {

        let success = false;

        $.ajax({

            url: "/userProfile/userAddresses/addAddress",
            async: true,
            type: "POST",
            data: {

                country: document.getElementById("countryAdd").value,
                city: document.getElementById("cityAdd").value,
                street: document.getElementById("streetAdd").value,
                postCode: document.getElementById("postCodeAdd").value

            }

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

</script>
</body>
</html>
