<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>PetBook</title>

</head>
<body>

<div  style="background-color: #2aabd2;
text-align: center;font-palette: light;
font-size:20px;height: 70px;padding-bottom: 20px;font-family: Baskerville,serif;" >PetBook</div>

<div>
    <form action="PetBook?command=add_user" method="post">
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
<h3>users info</h3>
<table>
    <th>name</th>
    <th>login</th>
    <th>email</th>
    <th>phone number</th>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.phoneNumber}</td>
        </tr>
    </c:forEach>
</table>
<div  style="background-color: #2aabd2;
text-align: center;font-palette: light;
font-size:20px;height: 70px;padding-bottom: 20px;font-family: Baskerville,serif;" >PetBook</div>
</body>
</html>
