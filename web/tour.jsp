<%-- 
    Document   : tour
    Created on : 07.06.2015, 13:25:48
    Author     : andre
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sarief" uri="/WEB-INF/tlds/sarief_library.tld" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="language" value="${not empty param.language ? param.language : 
                               not empty language ? language : pageContext.request.locale}"
                               scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="front.i18n.ApplicationMessages" />

<jsp:include page="header.jsp" />
        
        <strong>
            <fmt:message key="tourname" />: ${Tour.name}
        </strong>
        <br>
        <strong>
            <fmt:message key="tourprice" /> #${TourPriceCalculated}
        </strong>
        <c:if test="${!empty Tour.description}">
            <br>
            <strong>
                <fmt:message key="description" />:  ${Tour.description}
            </strong>
        </c:if>
        <sarief:hide roles="client,manager,admin">
            <form method="post">
                <input type="hidden" name="HiddenTourId" value="${Tour.id}" />
                <input type="submit" name="BuyTour" value="<fmt:message key="buy" />!" />
            </form>
        </sarief:hide>
        
<jsp:include page="footer.jsp" />