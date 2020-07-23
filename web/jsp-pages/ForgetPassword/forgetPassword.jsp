<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recover password</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,400;0,500;0,700;1,300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/forgetPassword.css">
</head>
<body>
<div class="container" style="margin-top: 80px;">

    <h1 class="title">Відновлення паролю</h1>

    <form id="form" class="form" method="post">

        <label id="emailLabel">
            Введіть адресу вашої електронної пошти
            <input id="email" type="text">
        </label>

        <button class="button button_forget" type="submit">Пошук</button>

    </form>

    <div id="validate" style="display: none;">

        <label class="text">
            Введіть отриманий код підтвердження облікового запису
            <input id="code" type="text">
        </label>

        <button class="button button_forget" onclick="validate()">Продовжити</button>

    </div>


</div>

<div class="overlay">
    <div class="modalw modalw_mini" id="info">
        <div class="modalw__close">&times;</div>
        <div class="modalw__subtitle">Перевірте пошту</div>
        <div class="modalw__descr">
            Код підтвердження надіслано на
            <div id="email_text"></div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<script>

    $(document).ready(function () {

        $('.modalw__close').on('click', function () {
            $('.overlay, #thanks').fadeOut('slow');
        });
    });

    let verificationCode;

    document.getElementById("form").onsubmit = function () {

        return findUser();
    }

    function findUser() {

        let email = document.getElementById("email").value;
        let success = false;

        $.ajax({

            url: "/forget",
            async: true,
            type: "POST",
            data: {

                email: email

            }

        }).done(function (code) {

            success = true;

            verificationCode = code;

            $('.overlay, #info').fadeIn('slow');

            document.getElementById("email_text").innerText = document.getElementById("email").value;
            document.getElementById("form").style.display = "none";
            document.getElementById("validate").style.display = "flex";

        }).fail(function (response) {

            success = false;

            if (response.status === 403) {

                alert("Користувача з такою електронною поштою не знайдено");

            }
        });

        return success;
    }

    function validate() {
        let enteredCode = document.getElementById("code").value;

        if (verificationCode !== enteredCode) {

            alert("WRONG");

        } else {
            window.location.href = "${pageContext.request.contextPath}/forget/updatePassword";
        }
    }

</script>
</body>
</html>
