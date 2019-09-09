<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setBundle basename="global" var="cnt"/>
<div class="menu">
    <a href="<fmt:message key="command.userPage" bundle="${cnt}"/>"><fmt:message key="global.text.myPage"
                                                                                 bundle="${cnt}"/></a>
    <br>
    <br>
    <a href="<fmt:message key="command.messages" bundle="${cnt}"/>"><fmt:message key="global.text.messages"
                                                                                bundle="${cnt}"/></a>
    <br>
    <br>
    <a href="<fmt:message key="command.profile" bundle="${cnt}"/>"><fmt:message key="global.text.settings"
                                                                                bundle="${cnt}"/></a>
    <br>
    <br>
    <a href="<fmt:message key="command.adminUsers" bundle="${cnt}"/>"><fmt:message key="global.text.users"
                                                                                   bundle="${cnt}"/></a>
    <br>
    <br>
    <a href="<fmt:message key="command.adminAdmins" bundle="${cnt}"/>"><fmt:message key="global.text.admin"
                                                                                   bundle="${cnt}"/></a>
    <br>
    <br>
    <a href="<fmt:message key="command.adminArticles" bundle="${cnt}"/>"><fmt:message key="global.text.articles"
                                                                                   bundle="${cnt}"/></a>
    <br>
    <br>
    <a href="<fmt:message key="command.logoutUser" bundle="${cnt}"/>"><fmt:message key="global.text.logout"
                                                                                   bundle="${cnt}"/></a>
</div>