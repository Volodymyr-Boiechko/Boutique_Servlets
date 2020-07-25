<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
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

            <div class="profile__block info">

                <div class="profile__block__title">Моя інформація</div>
                <div class="profile__block__descr">Ви можете переглядати і змінювати свої особисті дані</div>

                <form id="form" method="post">

                    <label class="info__form__label" style="margin-top: 20px;">
                        <div class="info__form__label_text">Ім'я:</div>
                        <input id="firstName" type="text" value="${person.firstName}">
                    </label>

                    <label class="info__form__label">
                        <div class="info__form__label_text">Прізвище:</div>
                        <input id="surname" type="text" value="${person.surname}">
                    </label>

                    <label class="info__form__label">
                        <div class="info__form__label_text">По-батькові:</div>
                        <input id="lastName" type="text" value="${person.lastName}">
                    </label>

                    <label class="info__form__label">
                        <div class="info__form__label_text">Дата народження:</div>
                        <input id="date" type="date" value="${person.birthDate}">
                    </label>

                    <label class="info__form__label">
                        <div class="info__form__label_text">Електронна пошта:</div>
                        <input id="email" type="text" value="${person.email}">
                    </label>

                    <label class="info__form__label">
                        <div class="info__form__label_text">Номер телефону:</div>
                        <input id="phoneNumber" type="text" value="${person.phoneNumber}">
                    </label>

                    <button id="button" disabled class="info__form__button" type="submit">Зберегти зміни</button>

                </form>

            </div>

        </div>

    </div>

</div>
<div class="overlay" id="overlayUpdateUser">
    <div class="modalUser" id="updateUser">
        <div class="modalUser__close">&times;</div>
        <div class="modalUser__subtitle">Оновлення інформації</div>
        <p class="modalUser__descr">Ваш профіль успішно оновлено</p>
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
        let user = ${person.idPerson};

        $.ajax({

            url: "/userProfile/userInfo",
            async: true,
            type: "POST",
            data: {

                firstName: document.getElementById("firstName").value,
                surname: document.getElementById('surname').value,
                lastName: document.getElementById('lastName').value,
                birthDate: document.getElementById('date').value,
                email: document.getElementById('email').value,
                phoneNumber: document.getElementById('phoneNumber').value,
                id: user

            }

        }).done(function () {

            success = true;
            $('#overlayUpdateUser, #updateUser').fadeIn('slow');

        }).fail(function (response) {

            success = false;

            if (response.status === 500)
                alert("Помилка на сервері");


        });

        return success;

    }

    function checkChanges() {

        let firstName = document.getElementById("firstName").value;
        let surname = document.getElementById('surname').value;
        let lastName = document.getElementById('lastName').value;
        let birthDate = document.getElementById('date').value;
        let email = document.getElementById('email').value;
        let phoneNumber = document.getElementById('phoneNumber').value;

        /*Додати валідацію якщо пустий емейл*/
        if (email === "") {

            return false;
        }

        let firstNameAttribute = "${person.firstName}";
        let surnameAttribute = "${person.surname}";
        let lastNameAttribute = "${person.lastName}";
        let birthDateAttribute = "${person.birthDate}";
        let emailAttribute = "${person.email}";
        let phoneNumberAttribute = "${person.phoneNumber}";

        return !(firstName === firstNameAttribute && surname === surnameAttribute &&
            lastName === lastNameAttribute && birthDate === birthDateAttribute &&
            email === emailAttribute && phoneNumber === phoneNumberAttribute);

    }

    setInterval(function () {

        let button = document.querySelector("#button");

        if (checkChanges() === true) {

            $("button").removeAttr("disabled");
            button.classList.add("active_button");

        } else {
            $("button").attr("disabled", "disabled");
            button.classList.remove("active_button");

        }

    }, 1)

</script>
>
</body>
</html>