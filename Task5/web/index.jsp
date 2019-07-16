<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>PetBook</title>
</head>
<body>

<c:redirect url="PetBook?command=start_page" />

<!--
<td><a href="PetBook?action=get_users">get users</a></td>





    <h3>users info</h3>
<table>
    <th>name</th>
    <th>login</th>
    <th>email</th>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
        </tr>
    </c:forEach>
</table>
    </tr>


<form action="PetBook?action=get_users" method="post">
    Fname:<input name="fname" type="text" id="fname" placeholder="type first name"/>
    <input type="submit" value="ok"/>
</form>

-->
</body>
</html>
