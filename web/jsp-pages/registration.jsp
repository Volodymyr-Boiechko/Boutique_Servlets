<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/registration.css">
</head>
<body>

<div class="container" style="margin-top: 80px;">

    <h1 class="title">Реєстрація</h1>

    <form class="form" method="post" action="login.jsp">

        <label class="form__label">
            <b>Логін</b>
            <input type="text" required>
        </label>

        <label class="form__label">
            <b>Пароль</b>
            <input type="password" required>
        </label>

        <label class="form__label">
            <b>Підтвердження</b>
            <input type="password" required>
        </label>

        <label class="form__label">
            <b>Дата<br>народження</b>
            <input class="long birth" type="date">
        </label>

        <label class="form__label">
            <b>Електронна<br>пошта</b>
            <input class="long" type="text">
        </label>

        <button class="button" type="submit">Зареєструватись</button>

        <div class="form__under">
            Вже зареєстровані?
            <a href="${pageContext.request.contextPath}/login">Увійти</a>
        </div>

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
