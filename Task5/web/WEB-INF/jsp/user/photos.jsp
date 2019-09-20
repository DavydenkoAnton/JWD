<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<fmt:setBundle basename="global" var="cnt"/>

<c:set var="lang" value="${language}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="global" var="cnt"/>


<html>
<head>
    <title><fmt:message key="global.text.photos" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/content.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <tag:header/>
    <c:choose>
        <c:when test="${sessionScope.role=='USER'}">
            <tag:menu/>
        </c:when>
        <c:when test="${sessionScope.role=='ADMIN'}">
            <tag:admin_menu/>
        </c:when>
    </c:choose>
    <tag:pet_photos/>
</div>
<tag:footer/>

</body>
</html>
