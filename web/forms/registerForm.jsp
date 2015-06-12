<%-- 
    Document   : registerForm
    Created on : 03.06.2015, 10:32:41
    Author     : andre
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="" method="post">
    login: <input type="text" name="login" /><br>
    password: <input type="password" name="password" /><br>
    confirm password: <input type="password" name="confirm" /><br>
    assign role: <select name="role">
        <c:forEach var="role" items="${roles}" >
            <option value="${role.id}">${role.name}</option>
        </c:forEach>
    </select><br>
    <input type="submit" />
</form>