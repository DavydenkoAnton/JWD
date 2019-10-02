<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<c:set var="lang" value="${language}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="global" var="cnt"/>

<c:set var="user" value="${sessionScope.user}"/>
<c:set var="pet" value="${sessionScope.pet}"/>

<html>
<head>
    <title><fmt:message key="global.text.profile" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <tag:header/>
    <tag:menu/>

    <div class="pet_profile">
        <div class="edit_avatar">
            <c:if test="${not empty pet.avatarUrl}">
                <img src="${pet.avatarUrl}" height="150" width="150" alt="">
            </c:if>
            <c:if test="${empty pet.avatarUrl}">
                <img src="<c:url value="/img/no_img_user.png"/>" height="150" width="150" alt="">
            </c:if>

            <br>
            <br>
            <form name="sendform" enctype="multipart/form-data"
                  action="<fmt:message key="command.editPetAvatar" bundle="${cnt}"/>" method="post">
                <input type="file" name="petAvatar" accept="image/jpeg"><br>
                <input type="submit" value="<fmt:message key="global.text.upload" bundle="${cnt}"/>">
            </form>
        </div>
        <div class="edit_profile_credential">
            <div class="edit_profile_credential_item">
                <form method="post" action="<fmt:message key="command.editPetName" bundle="${cnt}"/>">
                    <input type="text" name="petName" placeholder="<c:if test="${not empty pet.name}">
                ${pet.name}
            </c:if>
            <c:if test="${empty pet.name}">
                <fmt:message key="global.text.name" bundle="${cnt}"/>
            </c:if>">
                    <button type="submit"><fmt:message key="global.text.edit" bundle="${cnt}"/></button>
                </form>
            </div>
            <div class="edit_profile_credential_item">
                <form method="post" action="<fmt:message key="command.editPetBreed" bundle="${cnt}"/>">
                    <input type="text" name="breed" placeholder="
            <c:if test="${not empty pet.breed}">
                ${pet.breed}
            </c:if>
            <c:if test="${empty pet.breed}">
                <fmt:message key="global.text.breed" bundle="${cnt}"/>
            </c:if>">
                    <button type="submit"><fmt:message key="global.text.edit" bundle="${cnt}"/></button>
                </form>
            </div>
            <div class="edit_profile_credential_item">
                <form method="post" action="<fmt:message key="command.editPetAge" bundle="${cnt}"/>">
                    <input type="number" name="age" placeholder="<c:if test="${not empty pet.age}">
                ${pet.age}
            </c:if>
            <c:if test="${empty pet.age}">
                <fmt:message key="global.text.age" bundle="${cnt}"/>
            </c:if>">
                    <button type="submit"><fmt:message key="global.text.edit" bundle="${cnt}"/></button>
                </form>
            </div>
            <div class="edit_profile_credential_item">
                <form action="editPetType.html" method="post">
                    <select name="petType">
                        <c:choose>
                            <c:when test="${not empty pet.type}">
                                <c:choose>
                                    <c:when test="${pet.type=='DOG'}">
                                        <option selected>
                                            <fmt:message key="global.text.dog" bundle="${cnt}"/>
                                        </option>
                                        <option value="<fmt:message key="attribute.text.CAT" bundle="${cnt}"/>">
                                            <fmt:message key="global.text.cat" bundle="${cnt}"/>
                                        </option>
                                        <option value="<fmt:message key="attribute.text.BIRD" bundle="${cnt}"/>">
                                            <fmt:message key="global.text.bird" bundle="${cnt}"/>
                                        </option>
                                        <option value="<fmt:message key="attribute.text.OTHER" bundle="${cnt}"/>">
                                            <fmt:message key="global.text.other" bundle="${cnt}"/>
                                        </option>
                                    </c:when>
                                    <c:when test="${pet.type=='CAT'}">
                                        <option selected>
                                            <p><fmt:message key="global.text.cat" bundle="${cnt}"/></p>
                                        </option>
                                        <option value="<fmt:message key="attribute.text.DOG" bundle="${cnt}"/>">
                                            <fmt:message key="global.text.dog" bundle="${cnt}"/>
                                        </option>
                                        <option value="<fmt:message key="attribute.text.BIRD" bundle="${cnt}"/>">
                                            <fmt:message key="global.text.bird" bundle="${cnt}"/>
                                        </option>
                                        <option value="<fmt:message key="attribute.text.OTHER" bundle="${cnt}"/>">
                                            <fmt:message key="global.text.other" bundle="${cnt}"/>
                                        </option>
                                    </c:when>
                                    <c:when test="${pet.type=='BIRD'}">
                                        <option selected>
                                            <fmt:message key="global.text.bird" bundle="${cnt}"/>
                                        </option>
                                        <option value="<fmt:message key="attribute.text.DOG" bundle="${cnt}"/>">
                                            <fmt:message key="global.text.dog" bundle="${cnt}"/>
                                        </option>
                                        <option value="<fmt:message key="attribute.text.CAT" bundle="${cnt}"/>">
                                            <fmt:message key="global.text.cat" bundle="${cnt}"/>
                                        </option>
                                        <option value="<fmt:message key="attribute.text.OTHER" bundle="${cnt}"/>">
                                            <fmt:message key="global.text.other" bundle="${cnt}"/>
                                        </option>
                                    </c:when>
                                    <c:when test="${pet.type=='OTHER'}">
                                        <option selected>
                                            <fmt:message key="global.text.other" bundle="${cnt}"/>
                                        </option>
                                        <option value="<fmt:message key="attribute.text.DOG" bundle="${cnt}"/>">
                                            <fmt:message key="global.text.dog" bundle="${cnt}"/>
                                        </option>
                                        <option value="<fmt:message key="attribute.text.CAT" bundle="${cnt}"/>">
                                            <fmt:message key="global.text.cat" bundle="${cnt}"/>
                                        </option>
                                        <option value="<fmt:message key="attribute.text.BIRD" bundle="${cnt}"/>">
                                            <fmt:message key="global.text.bird" bundle="${cnt}"/>
                                        </option>
                                    </c:when>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <option selected disabled>
                                    <fmt:message key="global.text.breed" bundle="${cnt}"/>
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                    <button type="submit"><fmt:message key="global.text.edit" bundle="${cnt}"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
<tag:footer/>

</body>
</html>
