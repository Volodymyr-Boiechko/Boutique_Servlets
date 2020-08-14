<%@ page import="com.boiechko.service.implementations.ProductServiceImpl" %>
<%@ page import="com.boiechko.service.interfaces.ProductService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Newest clothes</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,400;0,500;0,700;1,300&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/clothes.css">
</head>
<body>

<%
    ProductService productService = new ProductServiceImpl();

    //request.setAttribute("newest", productService.getNewest().stream().limit(10).collect(Collectors.toList()));
    request.setAttribute("newest", productService.getNewest());

%>

<jsp:include page="../components/header.jsp"/>

<div class="clothes">

    <div class="container">

        <div class="row">

            <c:forEach items="${newest}" var="product">

                <div class="block col-md-4" id="${product.idProduct}">

                    <a href="${pageContext.request.contextPath}/new/${product.idProduct}">

                        <div class="clothes__block">

                            <div class="clothes__block__img">

                                <img class="clothes__block__img_main"
                                     src="${pageContext.request.contextPath}${product.image}"
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

                        <img id="favorite" src="${pageContext.request.contextPath}/img/other/favorite.png"
                             alt="favorite">

                    </button>

                </div>

            </c:forEach>

        </div>

    </div>

</div>

<jsp:include page="../components/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
<script>

    let array = <%=session.getAttribute("favoriteId")%>;

    function addToFavorite(idProduct) {

        let success = false;

        $.ajax({

            url: "/favorite",
            async: true,
            method: "POST",
            data: {

                idProduct: idProduct

            }
        }).done(function (status) {

            success = true;
            let item = document.getElementById(idProduct);

            let img = item.querySelector('#favorite');

            if (status === "add") {

                alert("Продукт додано до улюблених");
                img.src = "${pageContext.request.contextPath}/img/other/favoriteFull.png";
            } else if (status === "remove") {

                alert("Продукт видалено з улюблених");
                img.src = "${pageContext.request.contextPath}/img/other/favorite.png";

                let index = array.indexOf(idProduct);
                if (index > -1) {
                    array.splice(index, idProduct);
                }

            }

        }).fail(function (response) {

            success = false;

            if (response.status === 401)
                alert("Потрібно увійти");
            else if (response.status === 500)
                alert("Проблеми з сервером");

        });

        return success;

    }

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


</script>

</body>
</html>
