<%-- 
    Document   : authenticate
    Created on : 10.06.2015, 14:26:09
    Author     : andre
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sarief" uri="/WEB-INF/tlds/sarief_library.tld" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="front.i18n.ApplicationMessages" />

<form method="post">
    <input type="text" name="authorizationLogin" />
    <input type="password" name="authorizationPassword" />
    <input type="submit" value="<fmt:message key="login" />" />
</form>
