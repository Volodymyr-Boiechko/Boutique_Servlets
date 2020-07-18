<%@ page import="com.boiechko.service.interfaces.PersonService" %>
<%@ page import="com.boiechko.service.implementations.PersonServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Чоловічий одяг</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,400;0,500;0,700;1,300&display=swap"
          rel="stylesheet">
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

    <div style="height: 75px;" class="container">

        <div class="row">

            <div class="col-md-3">

                <div class="header__gender">

                    <%--В ПЕРСПЕКТИВІ ДОБАВИТИ ЩЕ ЖІНОЧИЙ ОДЯГ--%>
                    <%--<a href="${pageContext.request.contextPath}/men" class="header__gender_element header__gender_left">
                        чоловіки
                    </a>
                    <span>|</span>
                    <a href="${pageContext.request.contextPath}/woman" class="header__gender_element">
                        жінки
                    </a>--%>

                    <p class="header__gender_element header__gender_left">
                        Чоловічий одяг
                    </p>

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
                                <div class="header__profileBlock__user_text">Привіт <%=user%>
                                </div>
                                <a href="${pageContext.request.contextPath}/logout/<%=user%>">Вийти</a>
                            </div>

                            <div class="header__profileBlock__login" style="display: <%=loginShow%>;">
                                <a href="${pageContext.request.contextPath}/login">Увійти</a>
                                <span>|</span>
                                <a href="${pageContext.request.contextPath}/registration/">Зареєструватись</a>
                            </div>

                            <a class="header__profileBlock__descr" href="${pageContext.request.contextPath}/profile">
                                <img class="header__profileBlock__descr_img" src="../../img/header/profileblack.png"
                                     alt="profile">
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

    <div class="subheader">

        <div class="container">

            <ul id="list" class="subheader__list">

                <%--НОВИНКИ--%>
                <li class="subheader__list__element">

                    <p class="subheader__list__element_text">Новинки</p>
                    <div class="subheader__list__dropdown">

                        <ul class="subheader__list__dropdown__list">

                            <li class="subheader__list__dropdown__list__elem">

                                <h2 class="subheader__list__dropdown__list__elem_title">Нове</h2>

                                <ol class="subheader__list__dropdown__list__elem_links">

                                    <li><a href="/new">Дивитись все</a></li>
                                    <li><a href="/clothes?filter=new">Одяг</a></li>
                                    <li><a href="/footwear?filter=new">Взуття</a></li>
                                    <li><a href="/accessories?filter=new">Аксесуари</a></li>

                                </ol>

                            </li>

                            <li class="subheader__list__dropdown__list__elem">

                                <h2 class="subheader__list__dropdown__list__elem_title">Нові підбірки</h2>

                                <ul class="subheader__list__dropdown__list__elem_images">

                                    <c:forEach items="${newestProducts}" var="product">

                                        <a href="/brands/${product.brand}">

                                            <img src="${product.image}" alt="${product.name}">
                                            <p>${product.brand}</p>
                                            <div class="hover"></div>

                                        </a>

                                    </c:forEach>

                                </ul>

                            </li>


                        </ul>

                    </div>

                </li>

                <%--ОДЯГ--%>
                <li class="subheader__list__element">

                    <p class="subheader__list__element_text">Одяг</p>

                    <div class="subheader__list__dropdown">

                        <ul class="subheader__list__dropdown__list ">

                            <li class="subheader__list__dropdown__list__elem subheader__list__dropdown__list__elem_clothes">

                                <h2 class="subheader__list__dropdown__list__elem_title">Сортувати за типом продукту</h2>

                                <ol class="subheader__list__dropdown__list__elem_links subheader__list__dropdown__list__elem_links_columns">

                                    <c:forEach items="${clothesTypes}" var="product">

                                        <li>
                                            <a href="/clothes/${product.name}">${product.name}</a>
                                        </li>

                                    </c:forEach>

                                </ol>

                            </li>

                        </ul>

                    </div>

                </li>

                <%--ВЗУТТЯ--%>
                <li class="subheader__list__element">

                    <p class="subheader__list__element_text">Взуття</p>

                    <div class="subheader__list__dropdown">

                        <ul class="subheader__list__dropdown__list">

                            <li class="subheader__list__dropdown__list__elem">

                                <h2 class="subheader__list__dropdown__list__elem_title">Сортувати за типом продукту</h2>

                                <ol class="subheader__list__dropdown__list__elem_links">

                                    <li>
                                        <a href="/clothes/${product.name}">Дивитись все</a>
                                    </li>

                                    <c:forEach items="${shoes}" var="product">

                                        <li>
                                            <a href="/shoes/${product.name}">${product.name}</a>
                                        </li>

                                    </c:forEach>


                                </ol>

                            </li>

                            <li class="subheader__list__dropdown__list__elem">

                                <h2 class="subheader__list__dropdown__list__elem_title">Сортувати за брендом</h2>

                                <ol class="subheader__list__dropdown__list__elem_links">


                                    <c:forEach items="${newestProducts}" var="product">

                                        <li>
                                            <a href="/shoes/${product.name}">
                                                <img src="${product.image}">
                                                <div>${product.brand}</div>
                                            </a>
                                        </li>

                                    </c:forEach>


                                </ol>

                            </li>

                            <li class="subheader__list__dropdown__list__elem">

                                <ul class="subheader__list__dropdown__list__elem_images">

                                    <c:forEach items="${newestProducts}" var="product">

                                        <a href="/brands/${product.brand}">

                                            <img src="${product.image}" alt="${product.name}">
                                            <p>${product.brand}</p>
                                            <div class="hover"></div>

                                        </a>

                                    </c:forEach>

                                </ul>

                            </li>


                        </ul>

                    </div>

                </li>

                <%--АКСЕСУАРИ--%>
                <li class="subheader__list__element">

                    <p class="subheader__list__element_text">Аксесуари</p>

                    <div class="subheader__list__dropdown">

                        <ul class="subheader__list__dropdown__list">

                            <li class="subheader__list__dropdown__list__elem">

                                <h2 class="subheader__list__dropdown__list__elem_title">Сортувати за типом продукту</h2>

                                <ol class="subheader__list__dropdown__list__elem_links">

                                    <li>
                                        <a href="/clothes/${product.name}">Дивитись все</a>
                                    </li>

                                    <c:forEach items="${shoes}" var="product">

                                        <li>
                                            <a href="/shoes/${product.name}">${product.name}</a>
                                        </li>

                                    </c:forEach>


                                </ol>

                            </li>

                            <li class="subheader__list__dropdown__list__elem">

                                <h2 class="subheader__list__dropdown__list__elem_title">Сортувати за брендом</h2>

                                <ol class="subheader__list__dropdown__list__elem_links">


                                    <c:forEach items="${newestProducts}" var="product">

                                        <li>
                                            <a href="/shoes/${product.name}">
                                                <img src="${product.image}">
                                                <div>${product.brand}</div>
                                            </a>
                                        </li>

                                    </c:forEach>


                                </ol>

                            </li>

                            <li class="subheader__list__dropdown__list__elem">

                                <ul class="subheader__list__dropdown__list__elem_images">

                                    <c:forEach items="${newestProducts}" var="product">

                                        <a href="/brands/${product.brand}">

                                            <img src="${product.image}" alt="${product.name}">
                                            <p>${product.brand}</p>
                                            <div class="hover"></div>

                                        </a>

                                    </c:forEach>

                                </ul>

                            </li>

                        </ul>

                    </div>

                </li>

                <%--СПОРТИВНИЙ ОДЯГ--%>
                <li class="subheader__list__element width">

                    <p class="subheader__list__element_text">Спортивний одяг</p>

                    <div class="subheader__list__dropdown">

                        <ul class="subheader__list__dropdown__list">

                            <li class="subheader__list__dropdown__list__elem">

                                <h2 class="subheader__list__dropdown__list__elem_title">Сортувати за типом продукту</h2>

                                <ol class="subheader__list__dropdown__list__elem_links">

                                    <li>
                                        <a href="/clothes/${product.name}">Дивитись все</a>
                                    </li>

                                    <c:forEach items="${shoes}" var="product">

                                        <li>
                                            <a href="/shoes/${product.name}">${product.name}</a>
                                        </li>

                                    </c:forEach>


                                </ol>

                            </li>

                            <li class="subheader__list__dropdown__list__elem">

                                <h2 class="subheader__list__dropdown__list__elem_title">Сортувати за брендом</h2>

                                <ol class="subheader__list__dropdown__list__elem_links">


                                    <c:forEach items="${newestProducts}" var="product">

                                        <li>
                                            <a href="/shoes/${product.name}">
                                                <img src="${product.image}">
                                                <div>${product.brand}</div>
                                            </a>
                                        </li>

                                    </c:forEach>


                                </ol>

                            </li>

                            <li class="subheader__list__dropdown__list__elem">

                                <ul class="subheader__list__dropdown__list__elem_images">

                                    <c:forEach items="${newestProducts}" var="product">

                                        <a href="/brands/${product.brand}">

                                            <img src="${product.image}" alt="${product.name}">
                                            <p>${product.brand}</p>
                                            <div class="hover"></div>

                                        </a>

                                    </c:forEach>

                                </ul>

                            </li>


                        </ul>

                    </div>

                </li>

                <%--БРЕНДИ--%>
                <li class="subheader__list__element">

                    <p class="subheader__list__element_text">Бренди</p>

                    <div class="subheader__list__dropdown">

                        <ul class="subheader__list__dropdown__list">

                            <li class="subheader__list__dropdown__list__elem">

                                <h2 class="subheader__list__dropdown__list__elem_title">Сортувати за типом продукту</h2>

                                <ol class="subheader__list__dropdown__list__elem_links">

                                    <li>
                                        <a href="/clothes/${product.name}">Дивитись все</a>
                                    </li>

                                    <c:forEach items="${shoes}" var="product">

                                        <li>
                                            <a href="/shoes/${product.name}">${product.name}</a>
                                        </li>

                                    </c:forEach>


                                </ol>

                            </li>

                            <li class="subheader__list__dropdown__list__elem">

                                <h2 class="subheader__list__dropdown__list__elem_title">Сортувати за брендом</h2>

                                <ol class="subheader__list__dropdown__list__elem_links">


                                    <c:forEach items="${newestProducts}" var="product">

                                        <li>
                                            <a href="/shoes/${product.name}">
                                                <img src="${product.image}">
                                                <div>${product.brand}</div>
                                            </a>
                                        </li>

                                    </c:forEach>


                                </ol>

                            </li>

                            <li class="subheader__list__dropdown__list__elem">

                                <ul class="subheader__list__dropdown__list__elem_images">

                                    <c:forEach items="${newestProducts}" var="product">

                                        <a href="/brands/${product.brand}">

                                            <img src="${product.image}" alt="${product.name}">
                                            <p>${product.brand}</p>
                                            <div class="hover"></div>

                                        </a>

                                    </c:forEach>

                                </ul>

                            </li>


                        </ul>

                    </div>

                </li>


            </ul>

        </div>

    </div>

</header>
<script>

    let menu = document.querySelector('#profile');
    let menu__wrapper = menu.querySelector('#dropDown');
    let close = menu__wrapper.querySelector('#close');

    let list = document.querySelector('#list');
    let elements = list.querySelectorAll('.subheader__list__element');

    menu.addEventListener('mouseover', () => {
        menu__wrapper.classList.add('active_profile');
        menu__wrapper.style.cursor = 'pointer';
    });

    menu.addEventListener('mouseout', () => {
        menu__wrapper.classList.remove('active_profile');
    });

    close.addEventListener('click', () => {
        menu__wrapper.classList.remove('active_profile');
        menu__wrapper.style.cursor = 'default';
    });

    for (let i = 0; i < elements.length; i++) {

        let element = elements[i];
        let text = element.querySelector('.subheader__list__element_text');
        let dropdown = element.querySelector('.subheader__list__dropdown');

        //dropdown.classList.add('active_dropdown');

        element.addEventListener('mouseover', () => {
            element.classList.add('active_subheader');
            element.style.cursor = 'pointer';
            text.classList.add('active_text');
            dropdown.classList.add('active_dropdown');
        });

        element.addEventListener('mouseout', () => {
            element.classList.remove('active_subheader');
            text.classList.remove('active_text');
            dropdown.classList.remove('active_dropdown');
        });

    }

</script>

</body>
</html>