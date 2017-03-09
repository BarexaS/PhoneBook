<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Phone Book</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<div class="container" style="width: 20%; margin-top: 10%;">
    <div align="center">
        <form:form action="/signup" method="POST" modelAttribute="customuser" class="form-signin">
            Fill form for singing up!<br>
            Login:<br/><form:input type="text" path="login" class="form-control"/>
            <c:if test="${param.error ne null}">
                Only english symbols allowed, minimum length 3 symbols, without special characters<br><br>
            </c:if>
            Password:<br/><form:input type="password" path="password" class="form-control"/>
            <c:if test="${param.error ne null}">
                Minimum length 5 symbols<br><br>
            </c:if>
            Full name : <br/><form:input type="text" path="fio" class="form-control"/>
            <c:if test="${param.error ne null}">
                Minimum length 5 symbols<br><br>
            </c:if>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

            <c:if test="${param.error ne null}">
                <p>Wrong input of login or password!</p>
            </c:if>
        </form:form>
    </div>
</div>
</body>