<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<c:set var="article" value="${sessionScope.article}"/>
<fmt:setBundle basename="global" var="cnt"/>
<c:set var="photos" value="${sessionScope.petPhotos}" scope="session"/>
<c:set var="count" value="0" scope="page"/>


<div class="photos_content">
    <form action="<fmt:message key="command.deletePetPhotos" bundle="${cnt}"/>" method="POST">
        <table>
            <c:forEach items="${sessionScope.petPhotos}" var="photo">
                <tr>
                    <td>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <img src="<c:url value="${photo}"/>" alt=""/>
                        <label>
                            <input type="checkbox" name="checkBoxPhotoUrl_${count}" value="${photo}">
                        </label>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <button type="submit" name="btnPhotoDelete" >delete</button>
    </form>
    <c:if test="${not empty photos}">
        <tag:pet_photo_paging/>
    </c:if>
    <tag:add_pet_photo/>
</div>
