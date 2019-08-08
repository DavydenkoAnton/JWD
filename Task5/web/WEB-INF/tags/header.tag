<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="lang" value="${language}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="global" var="cnt"/>

<html>
<head>
    <title><fmt:message key="global.header" bundle="${cnt}"/></title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <div class="header">
        <fmt:message key="global.header" bundle="${cnt}"/>
    </div>
    <div>
        ${userHello}<br>
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
        <br>
    </div>
    <div class="menu">

        <p><a href="<fmt:message key="command.mainPage" bundle="${cnt}"/>"><fmt:message key="global.text.home"
                                                                                        bundle="${cnt}"/></a></p>

        <p><a href="<fmt:message key="command.loginPage" bundle="${cnt}"/>"><fmt:message key="global.text.login"
                                                                                         bundle="${cnt}"/></a></p>

        <p><a href="<fmt:message key="command.logoutUser" bundle="${cnt}"/>"><fmt:message key="global.text.logout"
                                                                                          bundle="${cnt}"/></a></p>

        <p><a href="<fmt:message key="command.userPage" bundle="${cnt}"/>"><fmt:message key="global.text.cabinet"
                                                                                        bundle="${cnt}"/></a></p>
    </div>
    <div class="content">
        <table>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.name}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div>
        <div class="pagRight"><p><a href="<fmt:message key="command.paggingUsersPrev" bundle="${cnt}"/>"><fmt:message key="global.text.paggingUsersPrev"
                                                                                                             bundle="${cnt}"/></a></p></div>
        <div class="pagLeft"><p><a href="<fmt:message key="command.paggingUsersNext" bundle="${cnt}"/>"><fmt:message key="global.text.paggingUsersNext"
                                                                                                             bundle="${cnt}"/></a></p></div>
    </div>
</div>
</body>
<div class="footer">
    <fmt:message key="global.header" bundle="${cnt}"/>
</div>
</html>