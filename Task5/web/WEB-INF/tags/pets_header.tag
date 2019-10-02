<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="lang" value="${language}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="global" var="cnt"/>

<div class="articles_header">
    <form action="<fmt:message key="command.getPetsByType" bundle="${cnt}"/>" method="post">
        <button type="submit" name="<fmt:message key="attribute.text.petType" bundle="${cnt}"/>"
                value="<fmt:message key="attribute.text.dog" bundle="${cnt}"/>">
            <fmt:message key="global.text.dogs" bundle="${cnt}"/>
        </button>
    </form>
    <form action="<fmt:message key="command.getPetsByType" bundle="${cnt}"/>" method="post">
        <button type="submit" name="<fmt:message key="attribute.text.petType" bundle="${cnt}"/>"
                value="<fmt:message key="attribute.text.cat" bundle="${cnt}"/>">
            <fmt:message key="global.text.cats" bundle="${cnt}"/>
        </button>
    </form>
    <form action="<fmt:message key="command.getPetsByType" bundle="${cnt}"/>" method="post">
        <button type="submit" name="<fmt:message key="attribute.text.petType" bundle="${cnt}"/>"
                value="<fmt:message key="attribute.text.bird" bundle="${cnt}"/>">
            <fmt:message key="global.text.birds" bundle="${cnt}"/>
        </button>
    </form>
    <form action="<fmt:message key="command.getPetsByType" bundle="${cnt}"/>" method="post">
        <button type="submit" name="<fmt:message key="attribute.text.petType" bundle="${cnt}"/>"
                value="<fmt:message key="attribute.text.other" bundle="${cnt}"/>">
            <fmt:message key="global.text.others" bundle="${cnt}"/>
        </button>
    </form>
</div>