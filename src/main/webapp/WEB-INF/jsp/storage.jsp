<%--
  Created by IntelliJ IDEA.
  User: Andrey72
  Date: 29.10.2022
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
Хранилище паролей
<form action="${pageContext.request.contextPath}/user/storage" method="post">
    <label for="passwordId">password:
        <input type="password" name="password" id="passwordId">
    </label><br>
    <label for="websiteId">website:
        <input type="text" name="website" id="websiteId">
    </label><br>
    <label for="commentId">comment:
        <input type="text" name="comment" id="commentId">
    </label><br>

    <button type="submit">Save password</button>
</form>
    <c:forEach var="storage" items="${requestScope.storages}">
            <form action="${pageContext.request.contextPath}/user/delete" method="post">
                <input type="text" name="id" value="${storage.id}">
                <input type="text" name="password" value="${storage.password}" disabled>
                <input type="text" name="website" value="${storage.website}" disabled>
                <input type="text" name="comment" value="${storage.comment}" disabled>
                <button type="submit">Удалить</button>
            </form>
    </c:forEach>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
<form action="${pageContext.request.contextPath}/user/message" method="get">

    <button type="submit">Сообщения</button>
</form>
</body>
</html>
