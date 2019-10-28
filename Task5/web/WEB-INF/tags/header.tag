<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ attribute name="pageURL" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>

<div class="header">
    <tag:locale pageURL="${pageURL}"/>
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
    <img src="<c:url  value = "/img/utils/header_logo.jpg"/>" alt="">
</div>



