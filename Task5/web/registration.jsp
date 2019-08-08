<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="global" var="cnt"/>
<html>
<head>
    <title>registration</title>

</head>
<body>

<h2><fmt:message key="global.titleRegistration" bundle="${cnt}"/></h2>

<div>
    <form action="<fmt:message key="command.registerUser" bundle="${cnt}"/>" method="post">
        <label>
            <input name="login" type="text" placeholder="login"/>
        </label><br/>
        <input name="password" type="text" placeholder="password"/><br/>
        <input name="name" type="text" placeholder="name"/><br/>
        <input name="email" type="text" placeholder="email"/><br/>
        <input name="phoneNumber" type="number" placeholder="phone"/><br/>
        <input name="age" type="number" placeholder="age"/><br/>
        <input type="submit" value="ok"/>
    </form>
</div>

</body>
</html>
