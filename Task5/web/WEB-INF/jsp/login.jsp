<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<fmt:setBundle basename="global" var="cnt"/>

<html>
<head>
    <title><fmt:message key="global.text.login" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/header.css"/> " rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/form.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <tag:header/>
    <div id="form-wrapper">
        <img src="<c:url value="/img/no_img_user.png"/>" alt=""/>
        <h5><fmt:message key="global.text.login" bundle="${cnt}"/></h5>
        <form method="post" action="<fmt:message key="command.loginUser" bundle="${cnt}"/>">
            <input type="text" name="login" required autofocus placeholder="login">
            <input type="password" name="password" required placeholder="password"><br>
            <button type="submit"><fmt:message key="global.text.login" bundle="${cnt}"/></button>
        </form>
        <form method="post" action="<fmt:message key="command.registerPage" bundle="${cnt}"/>">
            <button type="submit"><fmt:message key="global.text.register" bundle="${cnt}"/></button>
        </form>
    </div>

</div>
<tag:footer/>
</body>
</html>
