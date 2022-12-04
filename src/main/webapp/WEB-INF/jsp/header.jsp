<%--
  Created by IntelliJ IDEA.
  User: Andrey72
  Date: 30.10.2022
  Time: 2:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>header</h1>
<c:if test="${not empty sessionScope.user}">
  <form action="${pageContext.request.contextPath}/logout" method="post">
    <button type="submit">Logout</button>
  </form>
</c:if>
<div id="locale">
    <form action="${pageContext.request.contextPath}/locale" method="post">
        <button type="submit" name="lang" value="ru_RU">RU</button>
        <button type="submit" name="lang" value="en_US">EN</button>
    </form>
</div>

    <fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang :(param.lang != null ? param.lang : 'ru_RU')}"/>
<%--    <fmt:setLocale value="ru_RU"/>--%>
    <fmt:setBundle basename="translations"/>
</body>
</html>
