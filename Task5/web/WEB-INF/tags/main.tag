

<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="lang" value="${language}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="global" var="cnt"/>


<div class="container">
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
    </div>
    <div>
        <div class="pagLeft">next</div>
        <div class="pagRight">prev</div>
    </div>
</div>