<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User addresses</title>
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
<jsp:include page="../../components/header.jsp"/>
<div class="profile">

    <div class="container">

        <div class="profile__wrapper">

            <jsp:include page="../../components/navProfile.jsp"/>

            <div class="address">

                <div class="address__block address__block_top">


                    <div class="profile__block__title">Адресна книга</div>

                    <a href="${pageContext.request.contextPath}/userProfile/userAddresses/addAddress">
                        <button class="address__button">Добавити нову адресу</button>
                    </a>

                </div>

                <c:forEach items="${addresses}" var="address">

                    <div class="address__block">

                        <div class="address__block__info">
                            <div>${person.firstName} ${person.surname}</div>
                            <div>${address.street}</div>
                            <div>${address.city}</div>
                            <div>${address.country}</div>
                            <div>${address.postCode}</div>
                            <div>${person.phoneNumber}</div>
                        </div>

                        <div class="address__block__icons">

                            <a class="address__block__icons__href"
                               href="${pageContext.request.contextPath}/userProfile/userAddresses/editAddress/${address.idAddress}">

                                <div class="address__block__icons__href_text">Змінити</div>
                                <img class="address__block__icons__href_icon"
                                     src="${pageContext.request.contextPath}/img/profile/change.png" alt="change">

                            </a>

                            <a style="margin-top: 12px;" class="address__block__icons__href"
                               href="${pageContext.request.contextPath}/userProfile/userAddresses/deleteAddress/${address.idAddress}">

                                <div class="address__block__icons__href_text">Видалити</div>
                                <img class="address__block__icons__href_icon"
                                     src="${pageContext.request.contextPath}/img/profile/delete.png" alt="delete">

                            </a>

                        </div>

                    </div>

                </c:forEach>

            </div>


        </div>

    </div>

</div>
<jsp:include page="../../components/footer.jsp"/>
</body>
</html>
