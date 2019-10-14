<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>

<c:set var="adminUsers" value="${adminUsers}" scope="session"/>
<c:set var="adminPets" value="${adminPets}" scope="session"/>
<c:set var="adminAdmins" value="${adminAdmins}" scope="session"/>
<c:set var="searchUserValue" value="${searchUserValue}" scope="session"/>

<div class="users_content">
    <div class="users_content_header">
        <form action="<fmt:message key="command.pagingFirstAdminUsers" bundle="${cnt}"/>"
              method="post">
            <input type="text" required placeholder="<fmt:message key="global.text.search" bundle="${cnt}"/>"
                   name="<fmt:message key="attribute.text.searchUserValue" bundle="${cnt}"/>"
            <c:if test="${not empty searchUserValue}">
                   value="${searchUserValue}"
            </c:if>
            >
            <button type="submit">
                <fmt:message key="global.text.ok" bundle="${cnt}"/>
            </button>
        </form>
    </div>
    <div class="users_content_body">
        <div class="users_content_body_header">
            <p><fmt:message key="global.text.id" bundle="${cnt}"/></p>
            <p><fmt:message key="global.text.name" bundle="${cnt}"/></p>
            <p><fmt:message key="global.text.breed" bundle="${cnt}"/></p>
            <p><fmt:message key="global.text.type" bundle="${cnt}"/></p>
            <p><fmt:message key="global.text.role" bundle="${cnt}"/></p>
        </div>
        <div class="users_content_body_item">
            <c:if test="${not empty adminUsers}">
                <table>
                    <c:forEach items="${adminUsers}" var="user">
                        <tr>
                            <td>
                                <div class="users_content_body_item_cred">
                                    <c:forEach items="${adminPets}" var="pet">
                                        <c:if test="${pet.userId==user.id}">
                                            <c:set var="petName" value=" ${pet.name}" scope="session"/>
                                            <c:set var="petBreed" value=" ${pet.breed}" scope="session"/>
                                            <c:set var="petType" value=" ${pet.type}" scope="session"/>
                                        </c:if>
                                    </c:forEach>
                                    <div class="users_content_body_item_cred_atom">
                                        <p>${user.id}</p>
                                    </div>
                                    <div class="users_content_body_item_cred_atom">
                                        <c:choose>
                                            <c:when test="${not empty petName}">
                                                <p>${petName}</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p>-</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="users_content_body_item_cred_atom">
                                        <c:choose>
                                            <c:when test="${not empty petBreed}">
                                                <p>${petBreed}</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p>-</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="users_content_body_item_cred_atom">
                                        <c:choose>
                                            <c:when test="${not empty petType}">
                                                <p>${petType}</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p>-</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="users_content_body_item_cred_atom">
                                        <p> ${user.role} </p>
                                    </div>
                                    <div class="users_content_body_item_cred_atom">
                                        <form action="<fmt:message key="command.changeUserRole" bundle="${cnt}"/>"
                                              method="post">
                                            <button type="submit" name="userId" value="${user.id}">
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
    <div class="users_content_paging">
        <div class="users_content_paging_prev">
            <a href="<fmt:message key="command.pagingPrevAdminUsers" bundle="${cnt}"/>">
                <fmt:message key="global.text.prev" bundle="${cnt}"/>
            </a>
        </div>
        <div class="users_content_paging_next">
            <a href="<fmt:message key="command.pagingNextAdminUsers" bundle="${cnt}"/>">
                <fmt:message key="global.text.next" bundle="${cnt}"/>
            </a>
        </div>
    </div>
</div>