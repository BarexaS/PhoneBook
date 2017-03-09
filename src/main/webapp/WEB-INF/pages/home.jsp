<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Phone Book</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h3><a href="/">Contacts List</a></h3>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li>
                        <button type="button" id="add_contact" class="btn btn-default navbar-btn" data-toggle="modal"
                                data-target="#addInfoModal">Add Contact
                        </button>
                    </li>
                    <li style="margin-left: 12px;">
                        <button type="button" id="delete_contact" class="btn btn-default navbar-btn">Delete Marked
                            Contacts
                        </button>
                    </li>
                </ul>
                <form class="navbar-form navbar-left" role="search" action="/search" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="pattern" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Search</button>
                    <button type="button" id="viewall" class="btn btn-default navbar-btn">View All</button>
                </form>
                <button type="button" id="logout" class="btn btn-default navbar-btn" style="float: right">Logout
                </button>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>


    <table class="table table-striped">
        <thead>
        <tr>
            <td></td>
            <td>Last Name</td>
            <td>First Name</td>
            <td>Middle Name</td>
            <td>Mobile Number</td>
            <td>Home Number</td>
            <td>Address</td>
            <td>Email</td>
            <td>Edit</td>
        </tr>
        </thead>
        <c:forEach items="${notes}" var="s">
            <tr>
                <td><input type="checkbox" name="toDelete[]" value="${s.id}" id="checkbox_${s.id}"/></td>
                <td><c:out value="${s.lastName}"/></td>
                <td><c:out value="${s.firstName}"/></td>
                <td><c:out value="${s.middleName}"/></td>
                <td><c:out value="${s.mobileNumb}"/></td>
                <td><c:out value="${s.homeNumb}"/></td>
                <td><c:out value="${s.address}"/></td>
                <td><c:out value="${s.email}"/></td>
                <td><a href="/edit_${s.id}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
            </tr>
        </c:forEach>
    </table>
    <div id="addInfoModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content" style="text-align: center">
                <div class="modal-header">
                    <button class="close" type="button" data-dismiss="modal">×</button>
                    <h2 class="modal-title">Adding Info</h2>
                </div>
                <div class="modal-body">
                    <c:url value="/addInfo" var="updateUrl"/>
                    <form:form action="${updateUrl}" method="POST" modelAttribute="phoneinfo">
                        <div class="input-group">
                            <span class="input-group-addon">!</span>
                            <form:input type="text" class="form-control" path="lastName" placeholder="Last Name"/><br/>
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
                        <form:input type="text" class="form-control" path="homeNumb" placeholder="Home Number - +380(66)1234567"/>
                        Input number as a template - +380(66)1234567<br/><br/>
                        <form:input type="text" class="form-control" path="address" placeholder="Home Address"/><br/>
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

<script>
    $('#logout').click(function () {
        window.location.href = '/logout';
    });
    $('#viewall').click(function () {
        window.location.href = '/';
    });

    $('#delete_contact').click(function () {
        var data = {'toDelete[]': []};
        $(":checked").each(function () {
            data['toDelete[]'].push($(this).val());
        });
        $.post("/deleteInfo", data, function (data, status) {
            window.location.reload();
        });
    });
</script>
</body>
</html>