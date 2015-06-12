<%-- 
    Document   : register
    Created on : 28.05.2015, 13:05:34
    Author     : andre
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>
        <h1>Hello World! ${checkMe}</h1>
        <jsp:include page="forms/registerForm.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>