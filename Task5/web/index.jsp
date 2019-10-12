<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>redirecting</title>

</head>
<body>
<c:choose>
    <c:when test="${not empty cookie.language.value}">
        <fmt:setLocale value="${cookie.language.value}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>
<c:redirect url="main.html"/>
</body>
</html>
