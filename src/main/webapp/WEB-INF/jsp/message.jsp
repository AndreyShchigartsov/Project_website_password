<%--
  Created by IntelliJ IDEA.
  User: Andrey72
  Date: 15.11.2022
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Message</h1>
<c:forEach var="user" items="${requestScope.usersDtoListExceptForMyself}">
    <form action="/user/message/${user.username}" method="post">
        <input type="text" name="id" value="${user.id}">
        <input type="text" name="login"  value="${user.username}">
        <button type="submit">перейти</button>
    </form>
    <%--    <a href="/admin/storage/${user.username}">${user.username}</a><br>--%>
</c:forEach>
</body>
</html>
