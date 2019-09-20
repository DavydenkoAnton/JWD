<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setBundle basename="global" var="cnt"/>

<div class="add_pet_photo_content">
    <form name="sendform" enctype="multipart/form-data"
          action="<fmt:message key="command.addPetPhoto" bundle="${cnt}"/>" method="post">
        <input type="file" name="petPhoto" accept="image/jpeg"><br>
        <input type="submit" value="<fmt:message key="global.text.upload" bundle="${cnt}"/>">
    </form>
</div>