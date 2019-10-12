<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>

<c:set var="article" value="${sessionScope.article}"/>

<div class="article_wrapper">
    <div class="article_title">
        <p>${article.title}</p>
    </div>
    <div class="article_text">
        <p>${article.text}</p>
    </div>
</div>