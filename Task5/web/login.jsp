<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="global" var="cnt"/>

<html>
<head>
    <title>login</title>
</head>
<body>

<h2><fmt:message key="title.login" bundle="${cnt}"/></h2>

<form action="<fmt:message key="command.loginUser" bundle="${cnt}" />" method="post">
    <label><fmt:message key="global.login" bundle="${cnt}"/></label>
    <label>
        <input type="text" name="login" size="5">
    </label>
    <label><fmt:message key="global.password" bundle="${cnt}"/></label>
    <label>
        <input type="password" name="password" size="5">
    </label>
    <input type="submit" value="ОK"/>
</form>

</body>
</html>
