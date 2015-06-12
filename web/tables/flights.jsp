<%-- 
    Document   : hotels
    Created on : 01.06.2015, 9:27:00
    Author     : andre
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table>            
    <c:forEach var="Flight" items="${FlightsInfo}" >
        <tr>
            <td>
                ${Flight.company}
            </td>
            <td>
                ${Flight.id}
            </td>
            
            <td>
                here we need compareTo.
                ${Flight.departure}
            </td>
            
            <td>
                <a href="hotel/?id=${Hotel.id}">more</a> / <a href="hotelrooms/?id=${Hotel.id}">show rooms</a>
            </td>
        </tr>
    </c:forEach>
</table>