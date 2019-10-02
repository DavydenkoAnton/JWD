<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<c:set var="lang" value="${language}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="global" var="cnt"/>

<c:set var="article" value="${sessionScope.adminArticle}"/>

<div class="admin_add_article_wrapper">
    <form action="<fmt:message key="command.editArticleCommand" bundle="${cnt}"/>" method="post" id="form_sender">
        <div class="admin_article_add_title">
            <p><fmt:message key="global.text.title" bundle="${cnt}"/></p>
            <textarea name="adminArticleTitle" form="form_sender">
${article.title}
            </textarea>
        </div>
        <div class="admin_article_add_description">
            <p><fmt:message key="global.text.description" bundle="${cnt}"/></p>
            <textarea name="adminArticleDescription" form="form_sender">
                ${article.description}
            </textarea>
        </div>
        <div class="admin_article_add_text">
            <p><fmt:message key="global.text.text" bundle="${cnt}"/></p>
            <textarea name="adminArticleText" form="form_sender">
                ${article.text}
            </textarea>
        </div>
        <div class="admin_article_add_type">
            <select name="adminPetType" form="form_sender">
                <c:choose>
                    <c:when test="${article.petType=='DOG'}">
                        <option name="adminPetType">DOG</option>
                        <option name="adminPetType">CAT</option>
                        <option name="adminPetType">BIRD</option>
                        <option name="adminPetType">OTHER</option>
                    </c:when>
                    <c:when test="${article.petType=='CAT'}">
                        <option name="adminPetType">CAT</option>
                        <option name="adminPetType">DOG</option>
                        <option name="adminPetType">BIRD</option>
                        <option name="adminPetType">OTHER</option>
                    </c:when>
                    <c:when test="${article.petType=='BIRD'}">
                        <option name="adminPetType">BIRD</option>
                        <option name="adminPetType">DOG</option>
                        <option name="adminPetType">CAT</option>
                        <option name="adminPetType">OTHER</option>
                    </c:when>
                    <c:when test="${article.petType=='OTHER'}">
                        <option name="adminPetType">OTHER</option>
                        <option name="adminPetType">DOG</option>
                        <option name="adminPetType">BIRD</option>
                        <option name="adminPetType">CAT</option>
                    </c:when>
                </c:choose>
            </select>
        </div>
        <button type="submit" name="adminArticleId" value="${article.id}">
            <fmt:message key="global.text.create" bundle="${cnt}"/>
        </button>
    </form>
</div>