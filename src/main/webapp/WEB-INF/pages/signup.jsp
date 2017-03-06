<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Phone Book</title>
</head>
<body>
<div align="center">
    <form:form action="/signup" method="POST" modelAttribute="customuser">
        Fill form for singing up!<br>
        Login:<br/><form:input type="text" path="login"/><br>
        <c:if test="${param.error ne null}">
            Only english symbols allowed, minimum length 3 symbols, without special characters<br><br>
        </c:if>
        Password:<br/><form:input type="password" path="password"/><br>
        <c:if test="${param.error ne null}">
            Minimum length 5 symbols<br><br>
        </c:if>
        Full name : <br/><form:input type="text" path="fio"/><br>
        <c:if test="${param.error ne null}">
            Minimum length 5 symbols<br><br>
        </c:if>

        <input type="submit"/>

        <c:if test="${param.error ne null}">
            <p>Wrong input of login or password!</p>
        </c:if>
    </form:form>
</div>
</body>