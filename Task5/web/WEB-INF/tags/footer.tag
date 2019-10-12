<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="global" var="cnt"/>

<div class="footer">
    <fmt:message key="attribute.text.petbook" bundle="${cnt}"/>
</div>
