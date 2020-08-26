<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Bag</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,400;0,500;0,700;1,300&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bag.css">
</head>
<body>
<jsp:include page="components/header.jsp"/>
<c:choose>

    <c:when test="${not empty username}">

        <c:if test="${shoppingBag.size() == 0}">

            <div class="headerShoppingBag">

                <div class="container">

                    <img src="${pageContext.request.contextPath}/img/header/basketBlack.png" alt="basket">
                    <div class="headerShoppingBag__title">
                        Ваша корзина пуста
                    </div>
                    <div class="headerShoppingBag__descr">
                        Додавайте в корзину товари, які хочете придбати.
                    </div>
                    <div class="headerShoppingBag__links">

                        <a class="headerShoppingBag__links_link" href="${pageContext.request.contextPath}/favorite">
                            Переглянути улюблені
                        </a>

                    </div>
                    <a class="headerShoppingBag__link" href="${pageContext.request.contextPath}/">Розпочати шопінг</a>

                </div>

            </div>

        </c:if>

        <c:if test="${shoppingBag.size() != 0}">

            <div class="shoppingBag">

                <div class="container">

                    <div class="row">

                        <div class="col-md-7">

                            <div class="shoppingBag__bag">

                                <div class="shoppingBag__bag_title">Моя корзина</div>

                                <c:forEach items="${shoppingBag}" var="product">

                                    <div class="shoppingBag__bag__block" id="${product.idProduct}">

                                        <div href="${pageContext.request.contextPath}/manClothes/productItem?idProduct=${product.idProduct}"
                                           class="shoppingBag__bag__block__img">

                                            <img src="${pageContext.request.contextPath}/${product.image}"
                                                 alt="${product.typeName}">

                                        </div>

                                        <div class="shoppingBag__bag__block__text">

                                            <div class="shoppingBag__bag__block__text_price">${product.price} грн.</div>
                                            <div class="shoppingBag__bag__block__text_descr">${product.description}</div>

                                            <div class="shoppingBag__bag__block__text__char">

                                                <c:if test="${not empty product.model}">

                                                    <div class="shoppingBag__bag__block__text__char_element">${product.model}</div>

                                                </c:if>

                                                <c:if test="${not empty product.color}">

                                                    <div class="shoppingBag__bag__block__text__char_element">${product.color}</div>

                                                </c:if>

                                                <c:if test="${not empty product.size}">

                                                    <div class="shoppingBag__bag__block__text__char_element">${product.size}</div>

                                                </c:if>

                                                <%request.setAttribute("count", new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)));%>
                                                <label class="shoppingBag__bag__block__text__char_count">
                                                    К-сть:
                                                    <select class="select">

                                                        <c:forEach items="${count}" var="element">
                                                            <option value="${element}">${element}</option>
                                                        </c:forEach>

                                                    </select>
                                                </label>

                                            </div>

                                            <button onclick="if (addToFavorite(${product.idProduct}) === true) location.reload();" class="shoppingBag__bag__block__text__button" >

                                                <div class="shoppingBag__bag__block__text__button__img">

                                                    <img id="favorite" src="${pageContext.request.contextPath}/img/other/favorite.png"
                                                         alt="favorite">

                                                </div>

                                                <div class="shoppingBag__bag__block__text__button_text">Добавити в
                                                    улюблені
                                                </div>

                                            </button>

                                        </div>

                                        <button onclick="deleteFromShoppingBag(${product.idProduct})" class="shoppingBag__bag__block__delete">&times;</button>

                                    </div>

                                </c:forEach>

                                <div class="shoppingBag__bag_totalPrice">
                                    Всього <span class="totalPrice"></span>
                                </div>

                            </div>

                        </div>

                        <div class="col-md-5">

                            <div class="shoppingBag__bag">

                                <div class="shoppingBag__bag_title">Разом</div>

                                <div class="shoppingBag__bag__block shoppingBag__bag__block_price">

                                    <div class="shoppingBag__bag__block__textTotal">

                                        <div class="shoppingBag__bag__block__textTotal_el">
                                            Всього<span class="totalPrice"></span>
                                        </div>
                                        <div class="shoppingBag__bag__block__textTotal_el">
                                            Доставка<span>Безкоштовно</span>
                                        </div>

                                    </div>

                                    <div class="shoppingBag__bag__block_divider"></div>

                                    <button onclick="makeOrder()" class="shoppingBag__bag__block__button">Зробити замовлення</button>

                                </div>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </c:if>

    </c:when>
    <c:otherwise>

        <div class="headerShoppingBag">

            <div class="container">

                <img src="${pageContext.request.contextPath}/img/header/basketBlack.png" alt="basket">
                <div class="headerShoppingBag__title">
                    Ваша корзина пуста
                </div>
                <div class="headerShoppingBag__descr">
                    Увійдіть, щоб переглянути Вашу корзину і здійснювати покупки!
                </div>
                <div class="headerShoppingBag__links">

                    <a class="headerShoppingBag__links_link" href="${pageContext.request.contextPath}/login">Увійти</a>
                    <a class="headerShoppingBag__links_link" href="${pageContext.request.contextPath}/registration/">Зареєструватись</a>

                </div>

            </div>

        </div>

    </c:otherwise>

</c:choose>

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

    let selectElement = [];

    setInterval(function () {

        let prices = document.querySelectorAll('.totalPrice');

        for(let i = 0; i < prices.length; i++) prices[i].innerText = formPrice() + " грн.";

    }, 600);

    function formPrice() {

        let array = <%=request.getAttribute("prices")%>;
        let price = 0;

        let elements = document.querySelectorAll('.select');
        let copyArray = [];

        for(let i = 0; i < elements.length; i++) {

            copyArray.push(elements[i].value);
        }

        selectElement = copyArray;

        for (let i = 0; i < array.length; i++) price += array[i] * selectElement[i];

        return price.toString();

    }

    function deleteFromShoppingBag(idProduct) {

        let success = false;

        $.ajax({

            url: '/shoppingBag?idProduct=' + idProduct,
            async: false,
            type: 'DELETE'

        }).done(function () {

            success = true;
            alert('Продукт видалено з корзини');
            location.reload();

        }).fail(function () {

            success = false;

            if (response.status === 500)
                alert("Проблеми з сервером");

        });

        return success;

    }


    function makeOrder() {

        let success = false;

        let dateObject = new Date();
        let date = dateObject.toLocaleString("sv-SE");

        $.ajax({

            url: '/makeOrder',
            type: 'POST',
            data_type: 'json',
            async: false,
            data: {
                json: selectElement,
                totalPrice: formPrice(),
                dateOrder: date
            }

        }).done(function () {

            success = true
            alert('Ваше замовлення прийнято. Лист з замовленням прийде Вам на пошту');
            location.reload();

        }).fail(function () {

            success = false;

        });

        return success;
    }

</script>
</body>
</html>