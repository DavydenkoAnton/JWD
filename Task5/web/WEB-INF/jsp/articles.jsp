<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<c:set var="lang" value="${language}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="global" var="cnt"/>

<html>
<head>
    <title><fmt:message key="global.text.articles" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/header.css"/> " rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/articles.css"/> " rel="stylesheet" type="text/css">
</head>
<body>
<tag:header/>
<c:if test="${sessionScope.role=='USER'}">
    <tag:menu/>
</c:if>
<c:if test="${sessionScope.role == 'ADMIN'}">
    <tag:admin_menu/>
</c:if>
<div class="wrapper">
    <tag:articles_header/>
    <tag:articles/>
</div>
<tag:footer/>
</body>
</html>

