<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="pageURL" %>

<fmt:setBundle basename="global" var="cnt"/>

<div class="locale">
    <form name="formLocale" id="form" action="<fmt:message key="command.changeLocale" bundle="${cnt}"/>" method="POST">
        <input type="hidden" name="<fmt:message key="attribute.text.pageURL" bundle="${cnt}"/>" value="${pageURL}">
        <div>
            <select  name="<fmt:message key="attribute.text.language" bundle="${cnt}"/>"
                    onchange="document.formLocale.submit();">
                <c:choose>
                    <c:when test="${not empty cookie.language.value}">
                        <c:choose>
                            <c:when test="${cookie.language.value=='en'}">
                                <option selected value="en">en</option>
                                <option value="ru">ru</option>
                                <option value="de">de</option>
                            </c:when>
                            <c:when test="${cookie.language.value=='ru'}">
                                <option selected value="ru">ru</option>
                                <option value="en">en</option>
                                <option value="de">de</option>
                            </c:when>
                            <c:when test="${cookie.language.value=='de'}">
                                <option selected value="de">de</option>
                                <option value="en">en</option>
                                <option value="ru">ru</option>
                            </c:when>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <option value="ru">ru4</option>
                        <option value="en">en4</option>
                        <option value="de">de4</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </div>
        <br><br>
    </form>
</div>
