<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>

<c:set var="articles" value="${sessionScope.adminArticles}"/>

<div class="admin_articles_wrapper">
    <div class="admin_articles_adder">
        <form method="post" action="<fmt:message key="command.addArticlePage" bundle="${cnt}"/> ">
            <button type="submit">
                <fmt:message key="global.text.create" bundle="${cnt}"/>
            </button>
        </form>
    </div>
    <div class="admin_articles_table">
        <c:if test="${not empty articles}">
            <table>
                <c:forEach var="article" items="${articles}">
                    <tr>
                        <td>
                            <div class="admin_article_item">
                                <div class="admin_article_title">
                                    <p>${article.title}</p>
                                </div>
                                <div class="admin_article_action">
                                    <form method="post"
                                          action="<fmt:message key="command.editArticlePage" bundle="${cnt}"/> ">
                                        <button type="submit" name="articleId" value="${article.id}">
                                            <fmt:message key="global.text.edit" bundle="${cnt}"/>
                                        </button>
                                    </form>
                                    <form method="post"
                                          action="<fmt:message key="command.deleteArticle" bundle="${cnt}"/> ">
                                        <button type="submit" name="articleId" value="${article.id}">
                                            <fmt:message key="global.text.delete" bundle="${cnt}"/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
