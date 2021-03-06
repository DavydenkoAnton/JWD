<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>

<html>
<head>
    <title><fmt:message key="global.text.article" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/header.css"/> " rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/articles.css"/> " rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/locale.css"/> " rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <tag:header pageURL="article.html"/>
    <c:if test="${sessionScope.role=='USER'}">
        <tag:menu/>
    </c:if>
    <c:if test="${sessionScope.role == 'ADMIN'}">
        <tag:admin_menu/>
    </c:if>
    <tag:articles_header/>
    <tag:article/>
</div>
<tag:footer/>
</body>
</html>