<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setBundle basename="global" var="cnt"/>

<div class="pet_statistic">
    <div>
        <p><fmt:message key="global.text.usersStatistic" bundle="${cnt}"/> </p>
    </div>
    <div >
        <p><fmt:message key="global.text.dogs" bundle="${cnt}"/> ${sessionScope.dogPreferValue} %</p>
    </div>
    <div>
        <p><fmt:message key="global.text.cats" bundle="${cnt}"/> ${sessionScope.catPreferValue} %</p>
    </div>
    <div>
        <p><fmt:message key="global.text.birds" bundle="${cnt}"/> ${sessionScope.birdPreferValue} %</p>
    </div>
    <div>
        <p><fmt:message key="global.text.other" bundle="${cnt}"/> ${sessionScope.otherPreferValue} %</p>
    </div>
</div>


