<%-- 
    Document   : room
    Created on : 02.06.2015, 15:07:17
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <strong>
            this is room #${RoomInfo.price}
        </strong>
        <strong>
            description: ${RoomInfo.comment}
        </strong>
        <a href="#"> check this out! reservations, ftw!! резервации
        </a>
    </body>
</html>
