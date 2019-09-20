<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<fmt:setBundle basename="global" var="cnt"/>
<c:set var="photos" value="${sessionScope.petPhotos}" scope="session"/>

<div class="photos_content">
    <table>
        <c:forEach items="${sessionScope.petPhotos}" var="photo">
            <tr>
                <td>
                    <img src="<c:url value="${photo}"/>" alt=""/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty photos}">
    <tag:pet_photo_paging/>
    </c:if>
    <tag:add_pet_photo/>
</div>
