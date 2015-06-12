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
        <h1>Hello World! step3</h1>
        
          <form method="post">
              <input name="paramNumberOfRooms" type="text" value="${requestAvailableRooms}"/>
        
            <input type="submit" value="do submit">
        </form>
    </body>
</html>
