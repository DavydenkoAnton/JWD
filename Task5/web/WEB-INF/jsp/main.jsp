<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>

<html>
<head>
    <title><fmt:message key="global.text.home" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/header.css"/> " rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/statistic.css"/> " rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/content.css"/> " rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/locale.css"/> " rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <tag:header pageURL="main.html"/>
    <tag:pet_statistic/>
    <tag:main_content/>
</div>
<tag:footer/>
</body>
</html>

