<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>
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

    <div class="edit_avatar">
        <c:set var="pet" value="${sessionScope.pet}"/>
        <c:if test="${not empty pet.avatarURL}">
            <img src="${pet.avatarURL}" height="150" width="150" alt="">
        </c:if>
        <c:if test="${empty pet.avatarURL}">
            <img src="../../../img/utils/no_img_user.png" height="150" width="150" alt="">
        </c:if>

        <br>
        <br>
        <form name="sendform" enctype="multipart/form-data"
              action="<fmt:message key="command.editPetAvatar" bundle="${cnt}"/>" method="post">
            <input type="file" name="userAvatar" accept="image/jpeg"><br>
            <input type="submit" value="<fmt:message key="global.text.upload" bundle="${cnt}"/>">
        </form>
    </div>

    <div class="edit_credential">
        <c:if test="${not empty pet.name}">
            <fmt:message key="${pet.name}" bundle="${cnt}"/>
        </c:if>
        <c:if test="${empty pet.name}">
            <fmt:message key="global.text.name" bundle="${cnt}"/>
        </c:if>
        <form method="post" action="<fmt:message key="command.editPetName" bundle="${cnt}"/>">
            <input type="text" name="pet_name">
            <button type="submit"><fmt:message key="global.text.edit" bundle="${cnt}"/></button>
        </form>
    </div>
    <div class="edit_credential">
        <c:if test="${not empty pet.breed}">
            <fmt:message key="${pet.breed}" bundle="${cnt}"/>
        </c:if>
        <c:if test="${empty pet.breed}">
            <fmt:message key="global.text.breed" bundle="${cnt}"/>
        </c:if>
        <form method="post" action="<fmt:message key="command.editPetBreed" bundle="${cnt}"/>">
            <input type="text" name="pet_breed">
            <button type="submit"><fmt:message key="global.text.edit" bundle="${cnt}"/></button>
        </form>
    </div>
</div>
<tag:footer/>

</body>
</html>
