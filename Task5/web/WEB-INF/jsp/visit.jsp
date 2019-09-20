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
    <title><fmt:message key="global.text.profile" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css">
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
    <c:set var="pet" value="${sessionScope.pet}" scope="session"/>
    <c:if test="${not empty pet}">
        <p>${pet.name}</p>
    </c:if>
    <div class="content">
        <c:set var="pet" value="${sessionScope.pet}" />
        <c:if test="${empty pet.avatarUrl}">
            <img src="<c:url value="/img/no_img_user.png"/>" height="128" width="128" alt="">
        </c:if>
        <c:if test="${not empty pet.avatarUrl}">
            <img src="${pet.avatarUrl}" height="128" width="128" alt="">
            ${pet.name}<br>
            ${pet.age}<br>
            ${pet.breed}<br>
        </c:if>
        <br>
        <form action="<fmt:message key="command.sendMessage" bundle="${cnt}"/>" method="post" id="form_sender">
            <textarea name="messageText" form="form_sender"></textarea>
            <br/>
            <button type="submit" name="userId" value="${pet.userId}">
                <fmt:message key="global.text.send" bundle="${cnt}"/>
            </button>
        </form>
        <c:set var="friend" value="${sessionScope.friend}" scope="session"/>
        <c:choose>
            <c:when test="${friend}">
                <form action="<fmt:message key="command.removeFromFriends" bundle="${cnt}"/>" method="post">
                    <button class="btn-link" type="submit"
                            name="<fmt:message key="attribute.text.userId" bundle="${cnt}"/>"
                            value="${pet.userId}">
                        <p><fmt:message key="global.text.removeFromFriends" bundle="${cnt}"/></p>
                    </button>
                </form>
            </c:when>
            <c:otherwise>
                <form action="<fmt:message key="command.addToFriends" bundle="${cnt}"/>" method="post">
                    <button class="btn-link" type="submit"
                            name="<fmt:message key="attribute.text.userId" bundle="${cnt}"/>"
                            value="${pet.userId}">
                        <p><fmt:message key="global.text.addToFriends" bundle="${cnt}"/></p>
                    </button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<tag:footer/>

</body>
</html>
