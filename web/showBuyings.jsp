<%-- 
    Document   : showBuyings
    Created on : 08.06.2015, 21:06:09
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
        <h1><fmt:message key="purchases.title.main" /></h1>
        <jsp:include page="tables/purchases.jsp" />
<jsp:include page="footer.jsp" />