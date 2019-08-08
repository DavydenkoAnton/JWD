<%--
  Created by IntelliJ IDEA.
  User: Dali
  Date: 27.07.2019
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="global" var="cnt"/>
<!DOCTYPE html>
<html>
<head>
    <title>message</title>
</head>
<body>

<div>
    <form action="<fmt:message key="command.sendMessage" bundle="${cnt}"/>" method="post">
        <label>
            <input name="login" type="text" placeholder="receiver"/>
        </label><br/>
        <label>
            <input name="message" type="text" aria-setsize="100" placeholder="message"/>
        </label><br/>
        <input type="submit" value="send"/>
    </form>
</div>

</body>
</html>
