<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="global" var="cnt"/>
<!DOCTYPE html>
<html>
<head>
    <title>User</title>
</head>
<body>

<p><a href="<fmt:message key="command.mainPage" bundle="${cnt}"/>"><fmt:message key="global.text.home"
                                                                                bundle="${cnt}"/></a></p>
<p><a href="<fmt:message key="command.logoutUser" bundle="${cnt}"/>"><fmt:message key="global.text.logout"
                                                                                  bundle="${cnt}"/></a></p>
<p><a href="<fmt:message key="command.rightMessage" bundle="${cnt}"/>"><fmt:message key="global.text.rightMessage"
                                                                                  bundle="${cnt}"/></a></p>
</body>
</html>
