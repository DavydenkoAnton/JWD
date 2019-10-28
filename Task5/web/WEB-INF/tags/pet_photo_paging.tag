<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setBundle basename="global" var="cnt"/>

<div class="paging_pet_content">
    <div class="paging_pet_photo_prev">
        <form action="<fmt:message key="command.pagingPetPhotoPrev" bundle="${cnt}"/>" method="post">
            <button type="submit" name="<fmt:message key="attribute.text.pagingPhotoBtn" bundle="${cnt}"/>"
                    value="<fmt:message key="global.text.prev" bundle="${cnt}"/>">
                <img src="<c:url value="/img/utils/back-arrow.png"/>" alt="">
            </button>
        </form>
    </div>
    <div class="paging_pet_photo_next">
        <form action="<fmt:message key="command.pagingPetPhotoNext" bundle="${cnt}"/>" method="post">
            <button type="submit" name="<fmt:message key="attribute.text.pagingPhotoBtn" bundle="${cnt}"/>"
                    value="<fmt:message key="attribute.text.next" bundle="${cnt}"/>">
                <img src="<c:url value="/img/utils/forward-arrow.png"/>" alt="">
            </button>
        </form>
    </div>
</div>