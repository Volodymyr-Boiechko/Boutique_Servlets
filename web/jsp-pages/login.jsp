<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/login.css">
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="container" style="margin-top: 80px;">

    <h1 class="title">Вхід</h1>

    <form class="form" method="post" action="../index.jsp">

        <label>
            <b>Логін</b>
            <input class="form__input" type="text" placeholder="Введіть логін" name="name" required>
        </label>

        <label>
            <b>Пароль</b>
            <input class="form__input" type="password" placeholder="Введіть пароль" name="pass" required>
        </label>

        <div class="form__under">
            <label>
                <input type="checkbox" checked="checked" name="remember">
                Запам'ятати мене
            </label>

            <a class="form_link form_link_forget" href="#">Забули пароль?</a>

        </div>

        <button class="button" type="submit">Увійти</button>

        <a class="form_link form_link_reg" href="#">Зареєструватися</a>

    </form>

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

</body>
</html>
