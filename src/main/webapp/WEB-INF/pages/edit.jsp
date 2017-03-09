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
<div class="container">
    <div align="center">
        <div id="addInfoModal" class="modal-open">
            <div class="modal-dialog">
                <div class="modal-content" style="text-align: center">
                    <div class="modal-header">
                        <h2 class="modal-title">Editing Info</h2>
                    </div>
                    <div class="modal-body">
                        <c:url value="/editInfo" var="editUrl"/>
                        <form:form action="${editUrl}" method="POST" modelAttribute="phoneinfo">
                            <form:input type="text" path="id" hidden="true" />
                            <div class="input-group">
                                <span class="input-group-addon">!</span>
                                <form:input type="text" class="form-control" path="lastName"
                                            placeholder="Last Name"/><br/>
                            </div>
                            At least 4 symbols<br/><br/>
                            <div class="input-group">
                                <span class="input-group-addon">!</span>
                                <form:input type="text" class="form-control" path="firstName"
                                            placeholder="First Name"/><br/>
                            </div>
                            At least 4 symbols<br/><br/>
                            <div class="input-group">
                                <span class="input-group-addon">!</span>
                                <form:input type="text" class="form-control" path="middleName"
                                            placeholder="Middle Name"/><br/>
                            </div>
                            At least 4 symbols<br/><br/>
                            <div class="input-group">
                                <span class="input-group-addon">!</span>
                                <form:input type="text" class="form-control" path="mobileNumb"
                                            placeholder="Mobile Number - +380(66)1234567"/>
                            </div>
                            Input number as a template - +380(66)1234567<br/><br/>
                            <form:input type="text" class="form-control" path="homeNumb"
                                        placeholder="Home Number - +380(66)1234567"/>
                            Input number as a template - +380(66)1234567<br/><br/>
                            <form:input type="text" class="form-control" path="address"
                                        placeholder="Home Address"/><br/>
                            <form:input type="text" class="form-control" path="email" placeholder="Email"/><br/>
                            <button class="btn btn-default" type="submit">Submit</button>
                        </form:form>
                    </div>
                    <div class="modal-footer">
                        ! - required fields
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>