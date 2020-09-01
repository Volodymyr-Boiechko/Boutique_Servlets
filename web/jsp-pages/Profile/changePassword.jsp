<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change password</title>
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
<jsp:include page="../components/header.jsp"/>
<div class="profile">

    <div class="container">

        <div class="profile__wrapper">

            <jsp:include page="../components/navProfile.jsp"/>

            <div class="profile__block info" style="min-height: 500px;">

                <div class="profile__block__title">Змінити пароль</div>
                <div class="profile__block__descr">
                    Ви в будь-який момент можете змінити ваш пароль, щоб забезпечити безпеку вашого профілю
                </div>

                <form id="form" method="post">

                    <label class="info__form__label" style="margin-top: 40px;">
                        <div class="info__form__label_text">Поточний пароль:</div>
                        <input id="currentPassword" type="password">
                    </label>

                    <label class="info__form__label">
                        <div class="info__form__label_text">Новий пароль:</div>
                        <input id="newPassword" type="password">
                    </label>

                    <button id="button" disabled class="info__form__button" type="submit">Зберегти пароль</button>

                </form>

            </div>

        </div>

    </div>

</div>
<div class="overlay" id="overlayUpdateUser">
    <div class="modalUser" id="updateUser">
        <div class="modalUser__close">&times;</div>
        <div class="modalUser__subtitle">Оновлення паролю</div>
        <p class="modalUser__descr">Ваш пароль успішно оновлено</p>
    </div>
</div>
<jsp:include page="../components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>


    $(document).ready(function () {

        $('.modalUser__close').on('click', function () {
            window.location.reload();
        });
    });

    document.getElementById("form").onsubmit = function () {
        return validate();
    }

    function validate() {

        let success = false;
        let idPerson = ${person.idPerson};

        $.ajax({

            url: "/userProfile/changePassword",
            async: true,
            type: "POST",
            data: {

                currentPassword: document.getElementById('currentPassword').value,
                newPassword: document.getElementById('newPassword').value,
                idPerson: idPerson

            }

        }).done(function () {

            success = true;
            $('#overlayUpdateUser, #updateUser').fadeIn('slow');

        }).fail(function (response) {

            success = false;

            if (response.status === 401)
                alert("Неправильно введений теперішній пароль!")
            else if (response.status === 500)
                alert("Помилка на сервері!");

        });

        return success;

    }

    function checkEmpty() {

        let currentPassword = document.getElementById('currentPassword').value;
        let newPassword = document.getElementById('newPassword').value;

        /*Додати валідацію*/
        if (currentPassword === newPassword)
            return true;

        return currentPassword === "" || newPassword === "";

    }

    setInterval(function () {

        let button = document.querySelector("#button");

        if (checkEmpty() === true) {

            $("button").attr("disabled", "disabled");
            button.classList.remove("active_button");

        } else {
            $("button").removeAttr("disabled");
            button.classList.add("active_button");
        }

    }, 1)


</script>
</body>
</html>
