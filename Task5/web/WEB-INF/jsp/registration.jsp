<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<fmt:setBundle basename="global" var="cnt"/>

<html>
<head>
    <title><fmt:message key="global.text.registration" bundle="${cnt}"/></title>
    <link href="<c:url value="../../css/header.css"/> " rel="stylesheet" type="text/css">
    <link href="<c:url value="../../css/form.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="../../css/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>


<div class="wrapper">
    <tag:header/>
    <div id="form-wrapper">
        <img src="<c:url value="../../img/no_img_user.png"/>" alt=""/>
        <h5><fmt:message key="global.text.register" bundle="${cnt}"/></h5>
        <form method="post" action="<fmt:message key="command.registerUser" bundle="${cnt}"/>">
            //TODO required input attr

            <input type="text" name="<fmt:message key="global.text.login" bundle="${cnt}"/>"
                   placeholder="<fmt:message key="global.text.login" bundle="${cnt}"/>">

            <input type="password" name="<fmt:message key="global.text.password" bundle="${cnt}"/>"
                   placeholder="<fmt:message key="global.text.password" bundle="${cnt}"/>"><br>

            <button type="submit"><fmt:message key="global.text.register" bundle="${cnt}"/></button>
        </form>
        <form method="post" action="<fmt:message key="command.loginPage" bundle="${cnt}"/>">
            <button type="submit"><fmt:message key="global.text.back" bundle="${cnt}"/></button>
        </form>
    </div>
    <tag:footer/>
</div>
</body>
</html>
