<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<fmt:setBundle basename="global" var="cnt"/>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>

<c:set var="article" value="${sessionScope.article}"/>

<html>
<head>
    <title><fmt:message key="global.text.myPage" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/content.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/locale.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <tag:header pageURL="user.html"/>
    <tag:menu/>

    <div class="pet_content">
        <div class="pet_content_ava">
            <c:set var="pet" value="${sessionScope.pet}"/>
            <c:if test="${empty pet.avatarUrl}">
                <img src="<c:url value="/img/utils/no_img_user.png"/>" height="128" width="128" alt="">
            </c:if>
            <c:if test="${not empty pet.avatarUrl}">
                <img src="${pet.avatarUrl}" height="128" width="128" alt="">
            </c:if>
            <p>${pet.name}</p>
        </div>
        <div class="pet_content_credentials">
            <div class="pet_content_credentials_item">
                <h3><fmt:message key="global.text.age" bundle="${cnt}"/></h3>
                <h4>${pet.age}</h4>
            </div>
            <div class="pet_content_credentials_item">
                <h3><fmt:message key="global.text.breed" bundle="${cnt}"/></h3>
                <h4>${pet.breed}</h4>
            </div>
        </div>
    </div>
</div>
<tag:footer/>
</body>
</html>
