<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>

<c:set var="error" value="${error}" scope="session"/>

<div class="admin_settings_wrapper">
    <div class="admin_settings_block">
        <form method="post" action="<fmt:message key="command.editAdminLogin" bundle="${cnt}"/> ">
            <div class="admin_settings_block_item">
                <p><c:if test="${not empty error.login}">${error.login}</c:if></p>
                <input type="password" name="<fmt:message key="attribute.text.login" bundle="${cnt}"/>"
                       maxlength="17" placeholder="<fmt:message key="attribute.text.login" bundle="${cnt}"/>" required autofocus>
            </div>
            <div class="admin_settings_block_item">
                <p><c:if test="${not empty error.newLogin}">${error.newLogin}</c:if></p>
                <input type="password" name="<fmt:message key="attribute.text.newLogin" bundle="${cnt}"/>"
                       maxlength="17" placeholder="new login">
            </div>
            <div class="admin_settings_block_item">
                <p><c:if test="${not empty error.newLoginRepeat}">${error.newLoginRepeat}</c:if></p>
                <input type="password" name="<fmt:message key="attribute.text.newLoginRepeat" bundle="${cnt}"/>"
                       maxlength="17" placeholder="repeat login">
            </div>
            <button type="submit">
                <fmt:message key="global.text.edit" bundle="${cnt}"/>
            </button>
        </form>
    </div>
    <div class="admin_settings_block">
        <form method="post" action="<fmt:message key="command.editAdminPassword" bundle="${cnt}"/> ">
            <div class="admin_settings_block_item">
                <p><c:if test="${not empty error.password}">${error.password}</c:if></p>
                <input type="password" name="<fmt:message key="attribute.text.password" bundle="${cnt}"/>"
                       maxlength="17" placeholder="<fmt:message key="attribute.text.password" bundle="${cnt}"/>">
            </div>
            <div class="admin_settings_block_item">
                <p><c:if test="${not empty error.newPassword}">${error.newPassword}</c:if></p>
                <input type="password" name="<fmt:message key="attribute.text.newPassword" bundle="${cnt}"/>"
                       maxlength="17" placeholder="new password">
            </div>
            <div class="admin_settings_block_item">
                <p><c:if test="${not empty error.newPasswordRepeat}">${error.newPasswordRepeat}</c:if></p>
                <input type="password" name="<fmt:message key="attribute.text.newPasswordRepeat" bundle="${cnt}"/>"
                       maxlength="17" placeholder="repeat password">
            </div>
            <button type="submit">
                <fmt:message key="global.text.edit" bundle="${cnt}"/>
            </button>
        </form>
    </div>
</div>

