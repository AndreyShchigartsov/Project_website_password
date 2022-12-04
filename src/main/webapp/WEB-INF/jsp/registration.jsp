<%--
  Created by IntelliJ IDEA.
  User: Andrey72
  Date: 08.10.2022
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Regist</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <label for="name">name:
            <input type="text" name="name" id="name">
        </label><br>
        <label for="lastname">lastname:
            <input type="text" name="lastname" id="lastname">
        </label><br>
        <label for="password">password:
            <input type="password" name="password" id="password">
        </label><br>
        <select name="role" id="role">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select><br>
        <button type="submit">Registraiton</button>

        <c:if test="${not empty requestScope.errors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${error.message}</span><br>
                </c:forEach>
            </div>
        </c:if>
    </form>
    <a href="/login"><button type="submit">Авторизация</button></a>
</body>
</html>