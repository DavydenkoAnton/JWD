<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="lang" value="${language}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="global" var="cnt"/>

<c:set var="adminUsers" value="${adminUsers}"/>
<c:set var="adminPets" value="${adminPets}"/>
<c:set var="adminAdmins" value="${adminAdmins}"/>

<div class="users_content">
    <div class="users_content_items">
        <div class="users_content_items_body">
            <c:if test="${not empty adminUsers}">
                <table>
                    <c:forEach items="${adminUsers}" var="user">
                        <tr>
                            <td>
                                <div class="users_content_item">
                                    <c:forEach items="${adminPets}" var="pet">
                                        <c:if test="${pet.userId==user.id}">
                                            <div class="users_content_item_credential">
                                                <p><fmt:message key="global.text.id" bundle="${cnt}"/></p>
                                                <p>${pet.userId}</p>
                                            </div>
                                            <div class="users_content_item_credential">
                                                <p><fmt:message key="global.text.name" bundle="${cnt}"/></p>
                                                <p>${pet.name}</p>
                                            </div>
                                            <div class="users_content_item_credential">
                                                <p><fmt:message key="global.text.breed" bundle="${cnt}"/></p>
                                                <p>${pet.breed}</p>
                                            </div>
                                            <div class="users_content_item_credential">
                                                <p><fmt:message key="global.text.petType" bundle="${cnt}"/></p>
                                                <p>${pet.type}</p>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                    <div class="users_content_item_credential">
                                        <p><fmt:message key="global.text.role" bundle="${cnt}"/></p>
                                        <p> ${user.role} </p>
                                    </div>
                                    <div class="users_content_item_credential_action">
                                        <form action="<fmt:message key="command.changeUserRole" bundle="${cnt}"/>"
                                              method="post">
                                            <button type="submit"
                                                    name="<fmt:message key="attribute.text.userId" bundle="${cnt}"/>"
                                                    value="${user.id}">
                                                <c:choose>
                                                    <c:when test="${user.role=='USER'}">
                                                        <fmt:message key="global.text.block" bundle="${cnt}"/>
                                                    </c:when>
                                                    <c:when test="${user.role=='GUEST'}">
                                                        <fmt:message key="global.text.unblock" bundle="${cnt}"/>
                                                    </c:when>
                                                </c:choose>
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
</div>