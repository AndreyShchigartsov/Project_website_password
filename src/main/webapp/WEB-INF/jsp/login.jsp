<%--
  Created by IntelliJ IDEA.
  User: Andrey72
  Date: 29.10.2022
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@include file="header.jsp"%>
<%--<fmt:setLocale value="ru_RU"/>--%>
<%--<fmt:setBundle basename="translations"/>--%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
  <label for="name">
    <input type="text" name="name" id="name" ><fmt:message key="page.login.username"/>
  </label><br>
  <label for="password">
    <input type="text" name="password" id="password" ><fmt:message key="page.login.password"/>
  </label><br>
  <button type="submit">Login</button>
</form>
  <br>
  <a href="/registration">
    <button type="button">Registration</button>
  </a>
  <c:if test="${param.error != null}">
    <div style="color: red">
      <span>Email or password is not correct</span>
    </div>
  </c:if>

</body>
</html>
