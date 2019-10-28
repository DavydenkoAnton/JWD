<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>

<div class="menu">
    <div class="menu_item">
        <img src="<c:url value="/img/utils/home.png"/>" alt="">
        <div class="menu_item_a">
            <a href="<fmt:message key="command.userPage" bundle="${cnt}"/>">
                <fmt:message key="global.text.myPage" bundle="${cnt}"/>
            </a>
        </div>
    </div>
    <div class="menu_item">
        <img src="<c:url value="/img/utils/settings-gears.png"/>" alt="">
        <div class="menu_item_a">
            <a href="<fmt:message key="command.adminArticles" bundle="${cnt}"/>">
                <fmt:message key="global.text.articles" bundle="${cnt}"/>
            </a>
        </div>
    </div>
    <div class="menu_item">
        <img src="<c:url value="/img/utils/settings-gears.png"/>" alt="">
        <div class="menu_item_a">
            <a href="<fmt:message key="command.pagingFirstAdminUsers" bundle="${cnt}"/>">
                <fmt:message key="global.text.users" bundle="${cnt}"/>
            </a>
        </div>
    </div>
    <div class="menu_item">
        <img src="<c:url value="/img/utils/settings-gears.png"/>" alt="">
        <div class="menu_item_a">
            <a href="<fmt:message key="command.adminSettingsPage" bundle="${cnt}"/>">
                <fmt:message key="global.text.settings" bundle="${cnt}"/>
            </a>
        </div>
    </div>
    <div class="menu_item">
        <img src="<c:url value="/img/utils/runner.png"/>" alt="">
        <div class="menu_item_a">
            <a href="<fmt:message key="command.logoutUser" bundle="${cnt}"/>">
                <fmt:message key="global.text.logout" bundle="${cnt}"/>
            </a>
        </div>
    </div>
</div>