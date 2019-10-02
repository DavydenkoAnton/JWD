<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<c:set var="lang" value="${language}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="global" var="cnt"/>

<c:set var="articles" value="${sessionScope.adminArticles}"/>

<div class="admin_add_article_wrapper">
    <form action="<fmt:message key="command.addArticleCommand" bundle="${cnt}"/>" method="post" id="form_sender">
        <div class="admin_article_add_title">
            <p><fmt:message key="global.text.title" bundle="${cnt}"/></p>
            <textarea name="adminArticleTitle" form="form_sender"></textarea>
        </div>
        <div class="admin_article_add_description">
            <p><fmt:message key="global.text.description" bundle="${cnt}"/></p>
            <textarea name="adminArticleDescription" form="form_sender"></textarea>
        </div>
        <div class="admin_article_add_text">
            <p><fmt:message key="global.text.text" bundle="${cnt}"/></p>
            <textarea name="adminArticleText" form="form_sender"></textarea>
        </div>
        <div class="admin_article_add_type">
            <select name="adminPetType">
                <option value="DOG">DOG</option>
                <option value="CAT">CAT</option>
                <option value="BIRD">BIRD</option>
                <option value="OTHER">OTHER</option>
            </select>
        </div>
        <button type="submit">
            <fmt:message key="global.text.create" bundle="${cnt}"/>
        </button>
    </form>
</div>