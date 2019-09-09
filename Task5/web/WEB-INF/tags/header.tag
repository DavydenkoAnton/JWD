<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setBundle basename="global" var="cnt"/>

<div class="header">
    <c:if test="${empty sessionScope.role }">
        <a href="<fmt:message key="command.loginPage" bundle="${cnt}"/>">
            <fmt:message key="global.text.login" bundle="${cnt}"/></a>
    </c:if>
    <c:if test="${sessionScope.role=='USER' || sessionScope.role == 'ADMIN'}">
        <a href="<fmt:message key="command.userPage" bundle="${cnt}"/>">
            <fmt:message key="global.text.myPage" bundle="${cnt}"/></a>
    </c:if>

    <a href="<fmt:message key="command.petsPage" bundle="${cnt}"/>">
        <fmt:message key="global.text.pets" bundle="${cnt}"/></a>
    <a href="<fmt:message key="command.articlesPage" bundle="${cnt}"/>">
        <fmt:message key="global.text.articles" bundle="${cnt}"/></a>
    <a href="<fmt:message key="command.mainPage" bundle="${cnt}"/>">
        <fmt:message key="global.text.home" bundle="${cnt}"/></a>
    <img src="<c:url  value = "/img/header_logo.jpg"/>" alt="">
</div>



