<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<fmt:setBundle basename="global" var="cnt"/>
<c:set var="senders" value="${sessionScope.messageSenders}"/>
<c:set var="sender" value="${sessionScope.messageSender}"/>
<div class="sender">
    <table>
        <c:forEach items="${senders}" var="sender">
            <tr>
                <td>
                    <form action="<fmt:message key="command.getChatMessages" bundle="${cnt}"/>" method="post">
                        <button class="btn-link" type="submit"
                                name="<fmt:message key="attribute.text.userId" bundle="${cnt}"/>"
                                value="${sender.userId}">
                            ${sender.name}
                        </button>
                    </form>

                </td>
            </tr>
        </c:forEach>
    </table>

</div>