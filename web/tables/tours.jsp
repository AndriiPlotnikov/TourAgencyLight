<%-- 
    Document   : tours
    Created on : 10.06.2015, 11:05:56
    Author     : andre
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="language" value="${not empty param.language ? param.language : 
                               not empty language ? language : pageContext.request.locale}"
                               scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="front.i18n.ApplicationMessages" />
<table>
<c:forEach var="Tour" items="${Tours}" >
    <tr>
        <td>
            ${Tour.name}
        </td>
        <td>
            ${Tour.departure.company}
        </td>
        <td>
            ${Tour.departure.departure}
        </td>
        <td>
            ${Tour.price}
        </td>
        <td>
            ${Tour.minPrice}
        </td>
        <td>
            ${Tour.placesLeft}
        </td>
        <td>
            <a href="/TourAgencyLight/admin/tours/tour/?id=${Tour.id}"><fmt:message key="tour.more" /></a>
        </td>
    </tr>
</c:forEach>
</table>
