<%--
  Created by IntelliJ IDEA.
  User: Andrey72
  Date: 02.11.2022
  Time: 2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
AdminStorageUser
<c:forEach var="storage" items="${requestScope.storages}">
    Hello
     <span>${storage.password}</span>
     <span>${storage.website}</span>
     <span>${storage.comment}</span>
</c:forEach>
<%--<c:if test="${not empty requestScope.errors}">--%>
<%--    <div style="color: red">--%>
<%--        <c:forEach var="error" items="${requestScope.errors}">--%>
<%--            <span>${error.message}</span><br>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>
<%--</c:if>--%>
</body>
</html>
