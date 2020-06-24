<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recover password</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/registration.css">
</head>
<body>
<div class="container">

    <h1 class="title">Відновлення паролю</h1>

    <form id="form" class="form" method="post">

        <label class="form__label">
            <b>Логін</b>
            <input id="email" type="text" required>
        </label>


        <button class="button" type="submit">Пошук</button>

    </form>

    <div id="user_code"></div>

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

    document.getElementById("form").onsubmit = function () {
        return findUser() === true;

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

        }).done(function () {

            success = true;

            alert('Знайдено користувача!');

        }).fail(function (response) {

            success = false;

            if (response.status === 403) {

                alert("Користувача з такою електронною поштою не знайдено");

            }
        });

        return success;
    }

</script>

</body>
</html>
