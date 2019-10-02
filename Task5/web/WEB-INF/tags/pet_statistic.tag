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

<div class="pet_statistic">

    <h3><fmt:message key="global.text.usersStatistic" bundle="${cnt}"/></h3>
    <div class="pet_statistic_item">
        <h4><fmt:message key="global.text.dogs" bundle="${cnt}"/></h4>
        <h5>${sessionScope.dogPreferValue} %</h5>
    </div>
    <div class="pet_statistic_item">
        <h4><fmt:message key="global.text.cats" bundle="${cnt}"/></h4>
        <h5> ${sessionScope.catPreferValue} %</h5>
    </div>
    <div class="pet_statistic_item">
        <h4><fmt:message key="global.text.birds" bundle="${cnt}"/></h4>
        <h5> ${sessionScope.birdPreferValue} %</h5>
    </div>
    <div class="pet_statistic_item">
        <h4><fmt:message key="global.text.other" bundle="${cnt}"/></h4>
        <h5> ${sessionScope.otherPreferValue} %</h5>
    </div>
</div>


