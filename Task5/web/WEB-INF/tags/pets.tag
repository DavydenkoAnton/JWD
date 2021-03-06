<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setBundle basename="global" var="cnt"/>

<div class="pets_content">
    <table>
        <c:forEach items="${sessionScope.pets}" var="pet">
            <tr>
                <td>
                    <c:if test="${not empty pet.name}">
                        <div class="pets_content_item">
                            <c:choose>
                                <c:when test="${not empty pet.avatarUrl}">
                                    <img src="${pet.avatarUrl}" alt=""/>
                                </c:when>
                                <c:otherwise>
                                    <img src="<c:url value="/img/utils/no_img_user.png"/>" alt=""/>
                                </c:otherwise>
                            </c:choose>
                            <div class="pets_content_item_credentials">
                                <form action="<fmt:message key="command.visitPage" bundle="${cnt}"/>" method="post">
                                    <button class="btn-link" type="submit"
                                            name="<fmt:message key="attribute.text.userId" bundle="${cnt}"/>"
                                            value="${pet.userId}">
                                            ${pet.name}
                                    </button>
                                </form>
                            </div>
                        </div>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>