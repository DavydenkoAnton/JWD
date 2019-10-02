<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="lang" value="${language}" scope="session"/>
<c:choose>
    <c:when test="${not empty lang}">
        <fmt:setLocale value="${lang}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="ru"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="global" var="cnt"/>

<div class="header">
    <c:if test="${empty sessionScope.role || sessionScope.role == 'GUEST'}">
        <a href="<fmt:message key="command.loginPage" bundle="${cnt}"/>">
            <fmt:message key="global.text.login" bundle="${cnt}"/></a>
    </c:if>
    <c:if test="${sessionScope.role=='USER' || sessionScope.role == 'ADMIN'}">
        <a href="<fmt:message key="command.userPage" bundle="${cnt}"/>">
            <fmt:message key="global.text.myPage" bundle="${cnt}"/></a>
    </c:if>

    <a href="<fmt:message key="command.getAllPets" bundle="${cnt}"/>">
        <fmt:message key="global.text.pets" bundle="${cnt}"/></a>
    <a href="<fmt:message key="command.articlesPage" bundle="${cnt}"/>">
        <fmt:message key="global.text.articles" bundle="${cnt}"/></a>
    <a href="<fmt:message key="command.mainPage" bundle="${cnt}"/>">
        <fmt:message key="global.text.home" bundle="${cnt}"/></a>
    <img src="<c:url  value = "/img/header_logo.jpg"/>" alt="">
</div>



