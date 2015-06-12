<%-- 
    Document   : showTours
    Created on : 31.05.2015, 16:15:49
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
<h1><fmt:message key="listOfTours" /></h1>
<div id="choose-tour-type">
<form method="post">
    <select id="tour-type" name="tourType" onchange="submit()">
        <option value="any" ${tourType == 'any' ? 'selected' : ''}><fmt:message key="anyTourType" /></option>
        <option value="sightseeing" ${tourType == 'sightseeing' ? 'selected' : ''}><fmt:message key="sightseeing" /></option>
        <option value="vacation" ${tourType == 'vacation' ? 'selected' : ''}><fmt:message key="vacation" /></option>
    </select>
</form>

</div>
<jsp:include page="tables/tours.jsp" />
<jsp:include page="footer.jsp" />
        

