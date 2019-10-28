<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>
<c:set var="error" value="${error}" scope="session"/>

<html>
<head>
    <title><fmt:message key="global.text.registration" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/header.css"/> " rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/form.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/locale.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <tag:header pageURL="registration.html"/>
    <div class="form-wrapper">
        <img src="<c:url value="/img/utils/no_img_user.png"/>" alt=""/>
        <h5><fmt:message key="global.text.register" bundle="${cnt}"/></h5>
        <form method="post" action="<fmt:message key="command.registerUser" bundle="${cnt}"/>">
            <div class="form-wrapper_item">
                <p><c:if test="${not empty error.newLogin}">${error.newLogin}</c:if></p>
                <input type="text" name="<fmt:message key="attribute.text.newLogin" bundle="${cnt}"/>"
                       maxlength="17" autofocus placeholder="<fmt:message key="global.text.login" bundle="${cnt}"/>">
            </div>
            <div class="form-wrapper_item">
                <p><c:if test="${not empty error.newPassword}">${error.newPassword}</c:if></p>
                <input type="password" name="<fmt:message key="attribute.text.newPassword" bundle="${cnt}"/>"
                       maxlength="17" placeholder="<fmt:message key="global.text.password" bundle="${cnt}"/>">
            </div>
            <div class="form-wrapper_item">
                <p><c:if test="${not empty error.newPasswordRepeat}">${error.newPasswordRepeat}</c:if></p>
                <input type="password" name="<fmt:message key="attribute.text.newPasswordRepeat" bundle="${cnt}"/>"
                       maxlength="17" placeholder="<fmt:message key="global.text.password" bundle="${cnt}"/>">
            </div>
            <button type="submit"><fmt:message key="global.text.register" bundle="${cnt}"/></button>
        </form>
        <form method="post" action="<fmt:message key="command.loginPage" bundle="${cnt}"/>">
            <button type="submit" id="goToLoginBtn" onClick="cleanError()"><fmt:message key="global.text.back"
                                                                                        bundle="${cnt}"/></button>
        </form>
    </div>
</div>
<tag:footer/>
</body>
</html>
<script type="text/javascript">
    function cleanError() {
        document.getElementById('goToLoginBtn').onclick = function () {
            ${error.clean()}
        }
    }
</script>