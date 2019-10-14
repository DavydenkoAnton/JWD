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
    <title><fmt:message key="global.text.article" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/articles.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/locale.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <tag:header pageURL="editArticle.html"/>
    <tag:admin_menu/>
    <tag:admin_edit_article/>
</div>
<tag:footer/>
</body>
</html>
