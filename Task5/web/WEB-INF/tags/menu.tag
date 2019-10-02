<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="lang" value="${language}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="global" var="cnt"/>

<div class="menu">
    <div class="menu_item">
        <img src="<c:url value="/img/home.png"/>" alt="">
        <div class="menu_item_a">
            <a href="<fmt:message key="command.userPage" bundle="${cnt}"/>"><fmt:message key="global.text.myPage" bundle="${cnt}"/></>
        </div>
    </div>
    <div class="menu_item">
        <img src="<c:url value="/img/email.png"/>" alt="">
        <div class="menu_item_a">
            <a href="<fmt:message key="command.messages" bundle="${cnt}"/>"><fmt:message key="global.text.messages"
                                                                                         bundle="${cnt}"/></a>
        </div>
    </div>
    <div class="menu_item">
        <img src="<c:url value="/img/instagram-logo.png"/>" alt="">
        <div class="menu_item_a">
            <a href="<fmt:message key="command.photos" bundle="${cnt}"/>"><fmt:message key="global.text.photos"
                                                                                       bundle="${cnt}"/></a>
        </div>
    </div>
    <div class="menu_item">
        <img src="<c:url value="/img/settings-gears.png"/>" alt="">
        <div class="menu_item_a">
            <a href="<fmt:message key="command.profile" bundle="${cnt}"/>"><fmt:message key="global.text.settings"
                                                                                        bundle="${cnt}"/></a>
        </div>
    </div>
    <div class="menu_item">
        <img src="<c:url value="/img/runner.png"/>" alt="">
        <div class="menu_item_a">
            <a href="<fmt:message key="command.logoutUser" bundle="${cnt}"/>"><fmt:message key="global.text.logout"
                                                                                           bundle="${cnt}"/></a>
        </div>
    </div>
</div>