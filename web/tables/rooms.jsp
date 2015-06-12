<%-- 
    Document   : rooms
    Created on : 01.06.2015, 17:49:04
    Author     : andre
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table>            
    <c:forEach var="Room" items="${HotelRooms}" >
        <tr>
            <td>
                ${Room.roomNumber}
            </td>
            <td>
                ${Room.price}
            </td>
            <td>
                ${Room.comment}
            </td>
            
        </tr>
    </c:forEach>
</table>
