<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
    <c:when test="${not empty cookie.language.value}">
        <fmt:setLocale value="${cookie.language.value}"/>

    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>

<c:set var="pageURL" value="http://localhost:8080/pb/main.html" scope="request"/>

<fmt:setBundle basename="global" var="cnt"/>

<div class="main_content">
    <img src="<c:url value="/img/main-banner.png"/>" alt=""/>
    <h3>Welcome!</h3>
    <p>
        Bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
    </p>
    <p>
        Bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
        bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla
    </p>
</div>