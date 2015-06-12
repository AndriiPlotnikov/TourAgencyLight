<%-- 
    Document   : showPurchaseInfo
    Created on : 09.06.2015, 15:50:54
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
        <h1><fmt:message key="purchase.title" /></h1>
        ${Purchase.id} 
        <br>
        ${Purchase.price}
        <br>
        ${Purchase.status}
        <c:if test="${Purchase.status eq 'aquired'}">
            <form method="post">
                <input type="submit" name="newStatus" value='<fmt:message key="confirm.order" />!' />
            </form>
        </c:if>
<jsp:include page="footer.jsp" />