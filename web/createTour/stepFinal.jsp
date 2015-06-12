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
        <h1>Hello World! Succes!</h1>
        <form method="post">
            name it!!! <input type="text" name="tourName"/>
        ${attrFromFlight.id} + ${attrFromFlight.departure} <br>
        ${attrComebackFlights.id} + ${attrComebackFlights.departure} <br>
        ${attrNumberOfRooms} rooms available!<br>
        <c:forEach var="event" items="${attrChosenEvents}" >
                <br>
                ${event.type}
                <br>
                ${event.name}
                <br>
                ${event.price}
                <br>
                ${event.comment}
            
        </c:forEach>
                
                    <input type="submit" name="accept" value="accept into db!" />
                    <input type="submit" name="clear" value="clear this" />
                </form>
    </body>
</html>
