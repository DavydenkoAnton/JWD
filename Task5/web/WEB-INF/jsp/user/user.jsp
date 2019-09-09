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
    <title><fmt:message key="global.text.myPage" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <tag:header/>
    <tag:menu/>

    <div class="content">
        <c:set var="pet" value="${sessionScope.pet}" />
        <c:if test="${empty pet.avatarURL}">
            <img src="<c:url value="/img/no_img_user.png"/>" height="128" width="128" alt="">
        </c:if>
        <c:if test="${not empty pet.avatarURL}">
            <img src="${pet.avatarURL}" height="128" width="128" alt="">
            ${pet.name}
            ${pet.age}
            ${pet.breed}
        </c:if>
    </div>
</div>
<tag:footer/>
</body>
</html>
