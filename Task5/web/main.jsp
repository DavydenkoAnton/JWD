<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${language}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="global" var="cnt"/>

<!DOCTYPE html>

<html>

<head>
    <meta charset="utf-8">
    <title><fmt:message key="global.header" bundle="${cnt}"/></title>
    <link href="${pageContext.request.contextPath}/WEB-INF/css/style.css" rel="stylesheet" type="text/css">
</head>


<div id="wrapper">
    <header>
        <fmt:message key="global.header" bundle="${cnt}"/>
    </header>
</div>
<body>


<div>
    <form action="<fmt:message key="command.loginPage" bundle="${cnt}"/>" method="post">
        <input type="submit" value="submit"><br/>
    </form>
</div>


<div>
    <form action="<fmt:message key="command.changeLocale" bundle="${cnt}"/>" method="post">
        <h3><fmt:message key="global.languageChoose" bundle="${cnt}"/></h3>
        <label>
            <input type="radio" name="localeValue" value="en">
        </label> <fmt:message key="global.languageEn" bundle="${cnt}"/><br/>
        <label>
            <input type="radio" name="localeValue" value="ru">
        </label> <fmt:message key="global.languageRu" bundle="${cnt}"/><br/>
        <input type="submit" value="submit"><br/>
    </form>
</div>


<div>
    <form action="<fmt:message key="command.loginUser" bundle="${cnt}"/>" method="post">
        <label>
            <input name="login" type="text" placeholder="login"/>
        </label><br/>
        <input name="password" type="text" placeholder="password"/><br/>
        <input name="name" type="text" placeholder="name"/><br/>
        <input name="email" type="text" placeholder="email"/><br/>
        <input name="phoneNumber" type="number" placeholder="phone"/><br/>
        <input name="age" type="number" placeholder="age"/><br/>
        <input type="submit" value="ok"/>
    </form>
</div>
<h3>users info</h3>
<table>
    <th>name</th>
    <th>login</th>
    <th>email</th>
    <th>phone number</th>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.phoneNumber}</td>
        </tr>
    </c:forEach>
</table>
</body>
<footer>
    PetBook
</footer>
</html>
