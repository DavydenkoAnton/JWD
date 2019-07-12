<%@page import="by.davydenko.petbook.entity.User" %>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PetBook</title>
</head>
<body>

<td><a href="PetBook?action=get_users">get users</a></td>

<section>
    <h3>users info</h3>

    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <br/>
        </tr>
    </c:forEach>

    </tr>
</section>


</body>
</html>
