<%--
  Created by IntelliJ IDEA.
  User: Andrey72
  Date: 01.11.2022
  Time: 3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
StorageAdmin <br>
<c:forEach var="user" items="${requestScope.usersDtoList}">
    <form action="/admin/storage/${user.username}" method="post">
        <input type="text" name="id" value="${user.id}">
        <input type="text" name="login"  value="${user.username}" disabled>
        <button type="submit">перейти</button>
    </form>
<%--    <a href="/admin/storage/${user.username}">${user.username}</a><br>--%>
</c:forEach>
</body>
</html>
