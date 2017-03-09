<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:url value="/j_spring_security_check" var="loginUrl" />
        <form class="form-signin" action="${loginUrl}" method="POST">
            <h2 class="form-signin-heading">Please sign in</h2>
            Login:<br/>
            <input type="text" name="j_login" class="form-control" placeholder="Login">
            Password:<br/>
            <input type="password" name="j_password" class="form-control" placeholder="Password"><br>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
        <form action="/signup" method="get">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign UP</button>
        </form>
    </div>
</div>
</body>
</html>