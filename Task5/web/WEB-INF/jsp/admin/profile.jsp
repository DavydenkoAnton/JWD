<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<fmt:setBundle basename="global" var="cnt"/>

<c:set var="user" value="${sessionScope.user}"/>


<html>
<head>
    <title><fmt:message key="global.text.profile" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <tag:header/>
    <tag:admin_menu/>

    <div class="admin_profile">
        <div class="edit_avatar">
            <c:if test="${not empty user.avatarURL}">
                <img src="${user.avatarURL}" height="150" width="150" alt="">
            </c:if>
            <c:if test="${empty user.avatarURL}">
                <img src="<c:url value="/img/no_img_user.png"/>" height="150" width="150" alt="">
            </c:if>

            <br>
            <br>
            <form name="sendform" enctype="multipart/form-data"
                  action="<fmt:message key="command.editUserAvatar" bundle="${cnt}"/>" method="post">
                <input type="file" name="userAvatar" accept="image/jpeg"><br>
                <input type="submit" value="<fmt:message key="global.text.upload" bundle="${cnt}"/>">
            </form>
        </div>

        <div class="edit_credential">
            <c:if test="${not empty user.name}">
                ${user.name}
            </c:if>
            <c:if test="${empty user.name}">
                <fmt:message key="global.text.name" bundle="${cnt}"/>
            </c:if>
            <form method="post" action="<fmt:message key="command.editUserName" bundle="${cnt}"/>">
                <input type="text" name="name">
                <button type="submit"><fmt:message key="global.text.edit" bundle="${cnt}"/></button>
            </form>
        </div>

    </div>
</div>
<tag:footer/>

</body>
</html>
