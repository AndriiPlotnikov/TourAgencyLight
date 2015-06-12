<%-- 
    Document   : header
    Created on : 08.06.2015, 13:05:49
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sarief" uri="/WEB-INF/tlds/sarief_library.tld" %>

<c:set var="language" value="${not empty param.language ? param.language : 
                               not empty language ? language : pageContext.request.locale}"
                               scope="session" />

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="front.i18n.ApplicationMessages" />

<!DOCTYPE html>
<html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tour agency</title>
    </head>
    <body>
        <div id="header">
            <sarief:hide roles="manager,admin">
                <div id="admin-nav-menu">    
                    <a href="/TourAgencyLight/admin/settings/orders/"><fmt:message key="adminmenu.orders" /></a>
                    <a href="/TourAgencyLight/admin/users/"><fmt:message key="adminmenu.users" /></a>
                </div>
            </sarief:hide>
            <sarief:hide roles="guest">
                <jsp:include page="forms/authenticate.jsp"></jsp:include>
            </sarief:hide>
            
            <sarief:hide roles="client,manager,admin">
                <form method="post">
                    <input type="submit" name="authorizationLogout" value="<fmt:message key="logout" />" />
                </form>
            </sarief:hide>
            
            <form method="post">
                <select id="language" name="language" onchange="submit()">
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                    <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
                </select>
            </form>
                <div id="main-nav-menu">
                    <a href="/TourAgencyLight/admin/tours/"><fmt:message key="menu.tours" /></a>
                </div>
        </div>
