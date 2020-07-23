<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User orders</title>
    <link rel="shortcut icon" href="">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,400;0,500;0,700;1,300&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
</head>
<body>
<jsp:include page="../components/header.jsp"/>
<%

    String username = (String) session.getAttribute("username");
    Integer id = (Integer) session.getAttribute("idPerson");

%>
<div class="profile">

    <div class="container">

        <div class="profile__wrapper">

            <jsp:include page="../components/navProfile.jsp"/>

            <div class="profile__block info">



            </div>

        </div>

    </div>

</div>
<jsp:include page="../components/footer.jsp"/>
</body>
</html>
