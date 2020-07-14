<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Чоловічий одяг</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,400;0,500;0,700;1,300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/header.css">
</head>
<body>

<%
    String user = (String) session.getAttribute("username");
    String userShow = "", loginShow = "";
    if (user == null) {
        userShow = "none";
        loginShow = "block";
    } else {
        userShow = "block";
        loginShow = "none";
    }
%>

<header class="header">

    <div class="container">

        <div class="row">

            <div class="col-md-3">

                <div class="header__gender">

                    <a href="${pageContext.request.contextPath}/men" class="header__gender_element header__gender_left">
                        чоловіки
                    </a>
                    <span>|</span>
                    <a href="${pageContext.request.contextPath}/woman" class="header__gender_element">
                        жінки
                    </a>

                </div>

            </div>

            <div class="col-md-4 offset-md-2">
                <a href="${pageContext.request.contextPath}/">
                    <img class="header__logo" src="../../img/header/logo.png" alt="logo">
                </a>
            </div>

            <div class="col-md-2 offset-md-1">
                <div class="header__icons">
                    <div id="profile" class="header__icons_icon">
                        <img class="header__icons_icon_img" src="../../img/header/profile.png" alt="profile">
                        <div id="dropDown" class="header__profileBlock">

                            <div id="close">&times;</div>

                            <div class="header__profileBlock__user" style="display: <%=userShow%>;">
                                <div class="header__profileBlock__user_text">Привіт <%=user%></div>
                                <a href="${pageContext.request.contextPath}/logout/<%=user%>">Вийти</a>
                            </div>

                            <div class="header__profileBlock__login" style="display: <%=loginShow%>;">
                                <a href="${pageContext.request.contextPath}/login">Увійти</a>
                                <span>|</span>
                                <a href="${pageContext.request.contextPath}/registration/">Зареєструватись</a>
                            </div>

                            <a class="header__profileBlock__descr" href="${pageContext.request.contextPath}/profile">
                                <img class="header__profileBlock__descr_img" src="../../img/header/profileblack.png" alt="profile">
                                <div class="header__profileBlock__descr_text">Особистий кабінет</div>
                            </a>

                            <a class="header__profileBlock__descr" href="${pageContext.request.contextPath}/orders">
                                <img class="header__profileBlock__descr_img" src="../../img/header/box.png" alt="box">
                                <div class="header__profileBlock__descr_text">Мої замовлення</div>
                            </a>
                        </div>
                    </div>
                    <a class="header__icons_icon" href="${pageContext.request.contextPath}/favorite">
                        <img class="header__icons_icon_img" src="../../img/header/favorite.png" alt="favorite">
                    </a>
                    <a class="header__icons_icon" href="${pageContext.request.contextPath}/basket">
                        <img class="header__icons_icon_img" src="../../img/header/basket.png" alt="basket">
                    </a>
                </div>

            </div>

        </div>


    </div>

</header>
<script>

    let menu = document.querySelector('#profile');
    let menu__wrapper = menu.querySelector('#dropDown');
    let close = menu__wrapper.querySelector('#close');

    menu.addEventListener('mouseover', () => {
        menu__wrapper.classList.add('active');
        menu__wrapper.style.cursor = 'pointer';
    });

    menu.addEventListener('mouseout', () => {
        menu__wrapper.classList.remove('active');
    });

    close.addEventListener('click', () => {
        menu__wrapper.classList.remove('active');
        menu__wrapper.style.cursor = 'default';
    });

</script>

</body>
</html>