<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<c:set var="articles" value="${sessionScope.articles}"/>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>

<div class="articles_wrapper">

    <c:if test="${not empty articles}">
        <table>
            <c:forEach var="article" items="${articles}">
                <tr>
                    <td>
                        <div class="article_item">
                            <div class="article_title">
                                <form action="<fmt:message key="command.articlePage" bundle="${cnt}"/>" method="post">
                                    <button type="submit"
                                            name="<fmt:message key="attribute.text.articleTitle" bundle="${cnt}"/>"
                                            value="${article.title}">
                                            ${article.title}
                                    </button>
                                </form>
                            </div>
                            <div class="article_description">
                                <p>${article.description}</p>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>