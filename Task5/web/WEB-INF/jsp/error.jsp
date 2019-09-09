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
    <title><fmt:message key="global.text.admin" bundle="${cnt}"/></title>
</head>
<body>
<div class="wrapper">
    <p style="width: 200px; height: 300px; margin-left: auto; margin-right: auto; padding-top: 40px;">
        <fmt:message key="global.text.error" bundle="${cnt}"/>
    </p>
</div>
</body>
</html>
