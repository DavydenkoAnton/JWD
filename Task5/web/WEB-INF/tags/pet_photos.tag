<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<c:set var="article" value="${sessionScope.article}"/>
<fmt:setBundle basename="global" var="cnt"/>
<c:set var="photos" value="${sessionScope.petPhotos}" scope="session"/>
<c:set var="сount" value="0" scope="page"/>


<div class="photos_content">
    <form action="<fmt:message key="command.deletePetPhotos" bundle="${cnt}"/>" method="POST">
        <table>
            <c:forEach items="${sessionScope.petPhotos}" var="photo">
                <tr>
                    <td>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <img src="<c:url value="${photo}"/>" alt=""/>
                        <label>
                            <input type="checkbox" id="deletePhotoChBox" name="checkBoxPhotoUrl_${count}"
                                   value="${photo}" onchange="chBoxChange()">
                        </label>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="photos_content_btn_del_photo">
            <button type="submit"  id="btnPhotoDelete" name="btnPhotoDelete">
                <img src="<c:url value="/img/utils/bucket-icon.png"/>" alt="">
            </button>
        </div>
    </form>
    <c:if test="${not empty photos}">
        <tag:pet_photo_paging/>
    </c:if>
    <tag:add_pet_photo/>
</div>
<script type="text/javascript">
    function chBoxChange() {
        var chBox_1 = document.getElementsByName('checkBoxPhotoUrl_1')[0];
        var chBox_2 = document.getElementsByName('checkBoxPhotoUrl_2')[0];
        var chBox_3 = document.getElementsByName('checkBoxPhotoUrl_3')[0];
        var chBox_4 = document.getElementsByName('checkBoxPhotoUrl_4')[0];

        if (typeof chBox_1 != 'undefined') {
            if (typeof chBox_2 == 'undefined') {
                if (chBox_1.checked) {
                    document.getElementById("btnPhotoDelete").style.display = "block";
                } else {
                    document.getElementById("btnPhotoDelete").style.display = "none";
                }
            } else if (typeof chBox_3 == 'undefined') {
                if (chBox_1.checked || chBox_2.checked) {
                    document.getElementById("btnPhotoDelete").style.display = "block";
                } else {
                    document.getElementById("btnPhotoDelete").style.display = "none";
                }
            } else if (typeof chBox_4 == 'undefined') {
                if (chBox_1.checked || chBox_2.checked || chBox_3.checked) {
                    document.getElementById("btnPhotoDelete").style.display = "block";
                } else {
                    document.getElementById("btnPhotoDelete").style.display = "none";
                }
            } else {
                if (chBox_1.checked || chBox_2.checked || chBox_3.checked || chBox_4.checked) {
                    document.getElementById("btnPhotoDelete").style.display = "block";
                } else {
                    document.getElementById("btnPhotoDelete").style.display = "none";
                }
            }
        }
    }
</script>