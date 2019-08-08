<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="global" var="cnt"/>

<html>
<head>
    <title><fmt:message key="global.text.login" bundle="${cnt}" /></title>
</head>
<body>

<h2><fmt:message key="global.text.titleLogin" bundle="${cnt}"/></h2>

<form action="<fmt:message key="command.loginUser" bundle="${cnt}" />" method="post">
    <label><fmt:message key="global.text.login" bundle="${cnt}"/></label>
    <label>
        <input type="text" name="login" size="5">
    </label>
    <label><fmt:message key="global.text.password" bundle="${cnt}"/></label>
    <label>
        <input type="password" name="password" size="5">
    </label>
    <input type="submit" value="<fmt:message key="global.text.login" bundle="${cnt}" />"/>
</form>
<br/>
<br/>
<p><a href="<fmt:message key="command.registerPage" bundle="${cnt}"/>"><fmt:message key="global.text.register"
                                                                                 bundle="${cnt}"/></a></p>


</body>
</html>
