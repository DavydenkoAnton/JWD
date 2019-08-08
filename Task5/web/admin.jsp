<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="global" var="cnt"/>
<!DOCTYPE html>
<html>
<head>
    <title>admin</title>
</head>
<body>

<p><a href="<fmt:message key="command.mainPage" bundle="${cnt}"/>"><fmt:message key="global.text.home"
                                                                                bundle="${cnt}"/></a></p>
<br/>
<p><a href="<fmt:message key="command.logoutUser" bundle="${cnt}"/>"><fmt:message key="global.text.logout"
                                                                                  bundle="${cnt}"/></a></p>
<form action="<fmt:message key="command.deleteUser" bundle="${cnt}" />" method="post">
    <label><fmt:message key="global.text.login" bundle="${cnt}"/></label>
    <label>
        <input type="text" name="login" size="5">
    </label>
    <input type="submit" value="<fmt:message key="global.text.delete" bundle="${cnt}" />"/>
</form>

</body>
</html>
