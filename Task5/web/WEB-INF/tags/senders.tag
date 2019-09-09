<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<link href="${pageContext.request.contextPath}/css/messages.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">

<fmt:setBundle basename="global" var="cnt"/>
<c:set var="senders" value="${sessionScope.messageSenders}"/>
<c:set var="sender" value="${sessionScope.messageSender}"/>
<div class="sender">
    <table>
        <c:forEach items="${senders}" var="sender">
            <tr>
                <td>
                    <form action="getChatMessages.html" method="post">
                        <button class="sender_btn" type="submit" name="messageSenderId" value="${sender.userId}">${sender.name}</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>