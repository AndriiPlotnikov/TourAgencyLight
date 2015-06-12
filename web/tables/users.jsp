<%-- 
    Document   : rooms
    Created on : 01.06.2015, 17:49:04
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
    <tr>
        <th>
            <fmt:message key="user.name" />
        </th>
        <th>
            <fmt:message key="user.role" />
        </th>
        <th>
            <fmt:message key="user.discount" />,%
        </th>
        <th>
        </th>
    </tr>
    <c:forEach var="user" items="${users}" >
        
        <tr>
            <td>
                ${user.name}
            </td>
            <td>
                ${user.role}
            </td>
            <td>
                ${user.discount}%
            </td>
            <td>
                <a href="edit/?id=${user.id}"><fmt:message key="user.more" /></a>
            </td>
            
        </tr>
    </c:forEach>
</table>