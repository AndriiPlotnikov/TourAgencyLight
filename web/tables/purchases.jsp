<%-- 
    Document   : Purchases
    Created on : 10.06.2015, 12:09:01
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
    <c:forEach var="Purhase" items="${BuyingAttribute}" >
        <tr>
            <td>
                ${Purhase.id}
            </td>
            <td>
                ${Purhase.client.name}
            </td>
            <td>
                ${Purhase.status}
            </td>
            
            <td>
                <a href="order/?id=${Purhase.id}"><fmt:message key="purchase.more" /></a>
            </td>
        </tr>
    </c:forEach>
</table>
