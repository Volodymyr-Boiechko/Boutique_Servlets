<%@ page import="com.boiechko.entity.Product" %>
<%@ page import="com.boiechko.enums.PersonType" %>
<%@ page import="com.boiechko.service.implementations.PersonServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Clothes</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,400;0,500;0,700;1,300&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/clothes.css">
</head>
<body>
<jsp:include page="components/header.jsp"/>
<%
    if (session.getAttribute("favoriteId") == null)
        session.setAttribute("favoriteId", new ArrayList<Product>());
    else if (session.getAttribute("shoppingBag") == null)
        session.setAttribute("shoppingBag", new ArrayList<Product>());

    int number = Integer.parseInt((String) session.getAttribute("count")) + 1;

%>
<c:if test="${count + 1 != 0}">

    <div class="clothes">

        <div class="container">

            <div>

                <div class="row">

                    <c:forEach items="${clothes}" begin="0" end="${count}" var="product">

                        <div class="block col-md-4" id="${product.idProduct}">

                            <a href="${pageContext.request.contextPath}/manClothes/productItem?idProduct=${product.idProduct}">

                                <div class="clothes__block">

                                    <div class="clothes__block__img">

                                        <img class="clothes__block__img_main"
                                             src="${pageContext.request.contextPath}/${product.image}"
                                             alt="${product.productName}">

                                        <div class="hover"></div>

                                    </div>

                                    <div class="clothes__block__text">

                                        <div class="clothes__block__text_title">
                                                ${product.description}
                                        </div>

                                        <div class="clothes__block__text_price">
                                                ${product.price} грн.
                                        </div>

                                    </div>

                                </div>
                            </a>

                            <button onclick="addToFavorite(${product.idProduct})" class="clothes__block__img__favorite">

                                <img class="clothes__block__img__favorite_img" id="favorite" src="${pageContext.request.contextPath}/img/other/favorite.png"
                                     alt="favorite">

                            </button>

                        </div>

                    </c:forEach>

                    <%
                        if (session.getAttribute("username") != null) {
                            request.setAttribute("person", new PersonServiceImpl().getPersonByCredentials("username", (String) session.getAttribute("username")));
                            request.setAttribute("personType", PersonType.ADMIN);
                        }
                    %>

                    <c:if test="${not empty username}">

                        <c:set var="show" scope="request" value="${person.personType.equals(personType)}"/>

                        <c:if test="${show}">

                            <div class="col-md-4">

                                <div class="clothes__block" style="border: 0.5px grey solid;">

                                    <button name="addButton" class="clothes__block__addButton" id="addButton">

                                        <img src="${pageContext.request.contextPath}/img/other/add.jpg" alt="add">

                                    </button>

                                </div>

                            </div>

                        </c:if>

                    </c:if>

                </div>

            </div>

            <div class="clothes__more">

                <div class="clothes__more_title">Ви переглянули <%=number%> із ${clothes.size()} товарів</div>

                <c:set var="number" scope="request" value="<%=number%>"/>
                <c:if test="${clothes.size() != number}">

                    <button onclick="morePages()" class="clothes__more__downloadMore">Загрузити ще</button>

                </c:if>

            </div>

        </div>

    </div>

</c:if>


<div class="overlay" id="addOverlay">
    <div class="modalw modalw_call modalw_addProduct" id="add">
        <div class="modalw__close modalw__close_call">&times;</div>
        <form enctype="multipart/form-data" id="addForm" class="feed-form" method="post" accept-charset="UTF-8">

            <ul>
                <div class="feed-form__title">Заповніть поля для добавлення<br> товару на сайт</div>
                <li class="top">
                    <label>
                        <input class="input" id="type" placeholder="Тип товару" type="text">
                    </label>
                </li>

                <li>
                    <label>
                        <input class="input" id="product" placeholder="Підтип товару" type="text">
                    </label>
                </li>

                <li>
                    <label>
                        <input class="input" id="sex" placeholder="Стать(чоловік/жінка)" type="text">
                    </label>
                </li>

                <li>
                    <label>
                        <input class="input" id="brand" placeholder="Бренд товару" type="text">
                    </label>
                </li>

                <li>
                    <label>
                        <input class="input" id="model" placeholder="Модель товару(не обов'язково)" type="text">
                    </label>
                </li>

                <li>
                    <label>
                        <input class="input" id="size" placeholder="Розмір товару(не обов'язково)" type="text">
                    </label>
                </li>

                <li>
                    <label>
                        <input class="input" id="color" placeholder="Колір товару(не обов'язково)" type="text">
                    </label>
                </li>

                <li>
                    <label>
                        <input class="input" id="image" placeholder="Зображення" name="imageData" type="file">
                    </label>
                </li>

                <li>
                    <label>
                        <input class="input" id="destination" placeholder="Де повинен зберігатись файл" type="text">
                    </label>
                </li>

                <li>
                    <label>
                        <input class="input" id="price" placeholder="Ціна товару" type="text">
                    </label>
                </li>

                <li style="height: 85px;">
                    <label>
                        <textarea class="input input_com" id="description" placeholder="Опис товару"></textarea>
                    </label>
                </li>

                <li>
                    <button type="submit" id="button" class="modalw__button">Добавити до бази</button>
                </li>

            </ul>

        </form>
    </div>
    <div class="modalw modalw_mini" id="thanks">
        <div class="modalw__close">&times;</div>
        <div class="modalw__subtitle">Продукт добавлено</div>
    </div>
</div>

<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/addToFavorite.js"></script>
<script>

    let array = <%=session.getAttribute("favoriteId")%>;

    setInterval(function () {

        let blocks = document.querySelectorAll('.block');

        for (let i = 0; i < blocks.length; i++) {

            let id = blocks[i].getAttribute('id');

            for (let j = 0; j < array.length; j++) {

                if (parseInt(id) === array[j]) {

                    let img = blocks[i].querySelector('#favorite');
                    img.src = "${pageContext.request.contextPath}/img/other/favoriteFull.png";
                    break;

                }
            }
        }
    }, 1)

    $(document).ready(function () {

        $('#addButton').on('click', function () {
            $('#addOverlay, #add').fadeIn('slow');
        });

        $('.modalw__close').on('click', function () {
            $('#addOverlay, #add, #thanks').fadeOut('slow');
            location.reload();
        });

    });

    document.getElementById("addForm").onsubmit = function () {
        return addToDataBase();
    }

    function addToDataBase() {

        let success = false;

        $.ajax({

            url: "/manClothes",
            async: true,
            type: "POST",
            processData: false,
            cache: false,
            contentType: false,
            data: formData()
        }).done(function () {

            success = true;

            $('#add').fadeOut('fast');
            $('#thanks').fadeIn('slow');
            $('#addForm').trigger('reset');

        }).fail(function (response) {

            success = false;
            if (response.status === 500)
                alert("Помилка на сервері");
            else if (response.status === 501)
                alert("Не вдалось зберегти зображення");

        });

        return success;

    }

    function formData() {

        let formData = new FormData();

        let image = document.getElementById('image');
        let file = image.files[0];

        formData.append("image", file);
        formData.append("type", document.getElementById('type').value);
        formData.append("product", document.getElementById('product').value);
        formData.append("sex", document.getElementById('sex').value);
        formData.append("brand", document.getElementById('brand').value);
        formData.append("model", document.getElementById('model').value);
        formData.append("size", document.getElementById('size').value);
        formData.append("color", document.getElementById('color').value);
        formData.append("destination", document.getElementById('destination').value);
        formData.append("price", document.getElementById('price').value);
        formData.append("description", document.getElementById('description').value);

        return formData;

    }

    function morePages() {

        let page = ${page} + 1;

        let href = window.location.href.replace('page=${page}', 'page=' + page);

        location.href = href;

    }

    document.addEventListener("DOMContentLoaded", function() {
        let scrollPos = localStorage.getItem('scrollPos');
        if (scrollPos) window.scrollTo(0, scrollPos);
    });

    window.onbeforeunload = function(e) {
        localStorage.setItem('scrollPos', window.scrollY);
    };

</script>
</body>
</html>