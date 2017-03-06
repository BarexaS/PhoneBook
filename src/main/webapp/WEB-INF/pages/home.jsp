<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Phone Book</title>
</head>
<body>
<div align="center">
    <c:forEach var="s" items="${notes}">
        <c:out value="${s.lastName}" /><br/>
        <c:out value="${s.firstName}" /><br/>
        <c:out value="${s.middleName}" /><br/>
        <c:out value="${s.mobileNumb}" /><br/>
        <c:out value="${s.homeNumb}" /><br/>
        <c:out value="${s.address}" /><br/>
        <c:out value="${s.email}" /><br/>
    </c:forEach>

    <c:url value="/addInfo" var="updateUrl" />
    <form:form action="${updateUrl}" method="POST" modelAttribute="phoneinfo" >
        Last Name:<br/><form:input type="text" path="lastName" /><br/>
        Required, at least 4 symbols<br/><br/>
        First Name:<br/><form:input type="text" path="firstName" /><br/>
        Required, at least 4 symbols<br/><br/>
        Middle Name:<br/><form:input type="text" path="middleName" /><br/>
        Required, at least 4 symbols<br/><br/>
        Mobile Number:<br/><form:input type="text" path="mobileNumb" /><br/>
        Required, input number as a template -  +380(66)1234567<br/><br/>
        Home Number:<br/><form:input type="text" path="homeNumb" /><br/>
        Home Address:<br/><form:input type="text" path="address" /><br/>
        Email:<br/><form:input type="text" path="email" /><br/>
        <input type="submit" value="Update" />
    </form:form>

    <c:url value="/logout" var="logoutUrl" />
    <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>
</div>
</body>
</html>