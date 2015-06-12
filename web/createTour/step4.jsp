<%-- 
    Document   : step1
    Created on : 04.06.2015, 14:45:26
    Author     : andre
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World! this is 4-th step</h1>
        
<form method="post">
<table>            
    <c:forEach var="event" items="${requestAvailableEvents}" >
        <tr>
            <td>
                <input type="checkbox" name="paramChosenEvents" value="${event.id}"/>
            </td>
            <td>
                ${event.type}
            </td>
            <td>
                ${event.name}
            </td>
            
            <td>
                ${event.price}
            </td>
            
            <td>
                ${event.comment}
            </td>
        </tr>
    </c:forEach>
</table>
            <input type="submit" value="do submit">
        </form>

    </body>
</html>
