<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<c:set var="lang" value="${language}" scope="session"/>
<c:set var="article" value="${sessionScope.article}"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="global" var="cnt"/>

<div class="article_wrapper">
    <div class="article_title">
        <p>${article.title}</p>
    </div>
    <div class="article_text">
        <p>${article.text}</p>
    </div>
</div>