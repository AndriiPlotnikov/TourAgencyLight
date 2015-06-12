<%-- 
    Document   : editUserInfo
    Created on : 06.06.2015, 17:40:29
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
<jsp:include page="header.jsp" />
        <h1><fmt:message key="edit.user.info.title" /></h1>
        ${user.name} <br>
        ${user.role} <br>
        <form method="post">
            <input type="number" name="discount" value="${user.discount}" min="0" max="100"/>
            <input type="submit" />
        </form>
<jsp:include page="footer.jsp" />