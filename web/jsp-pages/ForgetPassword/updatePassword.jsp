<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String email = (String) session.getAttribute("email");
%>

<p><%=email%></p>

</body>
</html>
