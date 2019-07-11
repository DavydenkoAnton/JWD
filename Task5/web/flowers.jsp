<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>flower</title>
</head>
<body>
<section>
    <h3>users info</h3>

    <c:forEach items="${flowers}" var="f">
        <tr>
            <td>${f.toString()}</td><br/>
        </tr>
    </c:forEach>

    </tr>
</section>
</body>
</html>
