<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>
<c:set var="error" value="${error}" scope="session"/>

<html>
<head>
    <title><fmt:message key="global.text.login" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/header.css"/> " rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/form.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/locale.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <tag:header pageURL="login.html"/>
    <div class="form-wrapper">
        <img src="<c:url value="/img/no_img_user.png"/>" alt=""/>
        <h5><fmt:message key="global.text.login" bundle="${cnt}"/></h5>
        <form method="post" action="<fmt:message key="command.loginUser" bundle="${cnt}"/>">
            <div class="form-wrapper_item">
                <p><c:if test="${not empty error.login}">${error.login}</c:if></p>
                <input type="text" name="login" required maxlength="17" autofocus
                       placeholder="<fmt:message key="global.text.login" bundle="${cnt}"/>">
            </div>
            <div class="form-wrapper_item">
                <p><c:if test="${not empty error.password}">${error.password}</c:if></p>
                <input type="password" name="password" required maxlength="17"
                       placeholder="<fmt:message key="global.text.password" bundle="${cnt}"/>">
            </div>
            <button type="submit"><fmt:message key="global.text.login" bundle="${cnt}"/></button>
        </form>
        <c:set var="authorized" value="${sessionScope.authorized}" scope="session"/>
        <c:if test="${empty authorized}">
            <form method="post" action="<fmt:message key="command.registerPage" bundle="${cnt}"/>">
                <button type="submit" id="goToRegistrationBtn" onClick="cleanError()"><fmt:message
                        key="global.text.register" bundle="${cnt}"/></button>
            </form>
        </c:if>
    </div>
</div>
<tag:footer/>
</body>
</html>
<script type="text/javascript">
    function cleanError() {
        document.getElementById('goToRegistrationBtn').onclick = function () {
            ${error.clean()}
        }
    }
</script>
