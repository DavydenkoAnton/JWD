<form action="<fmt:message key="command.changeLocale" bundle="${cnt}"/>" method="post">
    <h3><fmt:message key="global.languageChoose" bundle="${cnt}"/></h3>
    <label>
        <input type="radio" name="localeValue" value="en">
    </label> <fmt:message key="global.languageEn" bundle="${cnt}"/><br/>
    <label>
        <input type="radio" name="localeValue" value="ru">
    </label> <fmt:message key="global.languageRu" bundle="${cnt}"/><br/>
    <input type="submit" value="submit"><br/>
</form>
<br>

<div class="menu">

    <p><a href="<fmt:message key="command.mainPage" bundle="${cnt}"/>"><fmt:message key="global.text.home"
                                                                                    bundle="${cnt}"/></a></p>

    <p><a href="<fmt:message key="command.loginPage" bundle="${cnt}"/>"><fmt:message key="global.text.login"
                                                                                     bundle="${cnt}"/></a></p>

    <p><a href="<fmt:message key="command.logoutUser" bundle="${cnt}"/>"><fmt:message key="global.text.logout"
                                                                                      bundle="${cnt}"/></a></p>

    <p><a href="<fmt:message key="command.userPage" bundle="${cnt}"/>"><fmt:message key="global.text.cabinet"
                                                                                    bundle="${cnt}"/></a></p>
</div>
<div class="content">
    <table>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.name}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    <div class="pagRight"><p><a href="<fmt:message key="command.paggingUsersPrev" bundle="${cnt}"/>"><fmt:message
            key="global.text.paggingUsersPrev"
            bundle="${cnt}"/></a></p></div>
    <div class="pagLeft"><p><a href="<fmt:message key="command.paggingUsersNext" bundle="${cnt}"/>"><fmt:message
            key="global.text.paggingUsersNext"
            bundle="${cnt}"/></a></p></div>
</div>