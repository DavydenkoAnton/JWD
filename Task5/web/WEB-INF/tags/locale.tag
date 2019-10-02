<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setBundle basename="global" var="cnt"/>


<div class="locale">
    <form name="form1" action="<fmt:message key="command.changeLocale" bundle="${cnt}"/>" method="POST">
        <input type="hidden" name="action">
        <div>
            <select name="localeValue" onchange="document.form1.submit();">
                <c:choose>
                    <c:when test="${not empty lang}">
                        <c:choose>
                            <c:when test="${lang=='en'}">
                                <option selected value="en">en</option>
                                <option value="ru">ru</option>
                                <option value="de">de</option>
                            </c:when>
                            <c:when test="${lang=='ru'}">
                                <option selected value="ru">ru</option>
                                <option value="en">en</option>
                                <option value="de">de</option>
                            </c:when>
                            <c:when test="${lang=='de'}">
                                <option selected value="de">de</option>
                                <option value="en">en</option>
                                <option value="ru">ru</option>
                            </c:when>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <option selected value="ru">ru</option>
                        <option value="en">en</option>
                        <option value="de">de</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </div>
        <br><br>
    </form>
</div>