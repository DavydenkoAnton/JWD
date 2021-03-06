<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="global" var="cnt"/>

<html>
<head>
    <title><fmt:message key="global.text.messages" bundle="${cnt}"/></title>
    <link href="<c:url value="/css/messages.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/header.css"/> " rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/content.css"/> " rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/locale.css"/> " rel="stylesheet" type="text/css">
</head>
<body>

<c:set var="senders" value="${sessionScope.messageSenders}"/>
<c:set var="sender" value="${sessionScope.messageSender}"/>
<c:set var="chatMessages" value="${sessionScope.chatMessages}"/>
<c:set var="receiver" value="${sessionScope.messageReceiver}"/>


<div class="wrapper">
    <tag:header pageURL="messages.html"/>
    <tag:menu/>
    <tag:senders/>
    <div class="chat_wrapper">
        <div class="chat_sender">
            <p>${sender.name}</p>
        </div>
        <div class="chat">
            <table>
                <c:forEach items="${chatMessages}" var="chatMessage">
                    <c:if test="${chatMessage.senderId==receiver.userId}">
                        <tr class="sender_item">
                            <td>
                                <div class="sender_name">
                                        ${receiver.name}
                                </div>
                                <div class="sender_message">
                                        ${chatMessage.message}
                                </div>
                                <div class="date_message">
                                        ${chatMessage.date}
                                </div>
                                <br>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${chatMessage.senderId==sender.userId}">
                        <tr class="receiver_item">
                            <td>
                                <div class="user_name">
                                        ${sender.name}
                                </div>
                                <div class="user_message">
                                        ${chatMessage.message}
                                </div>
                                <div class="date_message">
                                        ${chatMessage.date}
                                </div>
                                <br>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
        <div class="message_sender">
            <c:if test="${not empty sender}">
                <form action="<fmt:message key="command.sendMessage" bundle="${cnt}"/>" method="post" id="form_sender">
                    <textarea name="messageText" form="form_sender"></textarea>
                    <br/>
                    <button type="submit" name="userId" value="${sender.userId}">
                        <fmt:message key="global.text.send" bundle="${cnt}"/>
                    </button>
                </form>
            </c:if>
        </div>
    </div>

</div>


<tag:footer/>


</body>
</html>