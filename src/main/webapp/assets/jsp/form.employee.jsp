<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="/assets/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/assets/css/main.css"/>
    <title>Title</title>
</head>
<body>
<div class="container">
    <form action="/department/employee/put" method="post">
        <input type="hidden" name="department" value="${department}">
        <input type="hidden" name="id" value="${employee.id}">

        <div class="form-group">
            <label for="name">Email address</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="<c:out value="${employee.name}"/>">
            <div class="error-validation">
                <c:forEach var="error" items="${errors.name}">
                    <div>${error}</div>
                </c:forEach>
            </div>
        </div>
        <div class="form-group">
            <label for="email">Password</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="<c:out value="${employee.email}"/>">
            <div class="error-validation">
                <c:forEach var="error" items="${errors.email}">
                    <div>${error}</div>
                </c:forEach>
            </div>
        </div>
        <div class="form-group">
            <label for="birthday">Password</label>
            <input type="date" class="form-control" id="birthday" name="birthday" placeholder="Birthday" value="<c:out value="${employee.birthday}"/>">
            <div class="error-validation">
                <c:forEach var="error" items="${errors.birthday}">
                    <div>${error}</div>
                </c:forEach>
            </div>
        </div>

        <button type="submit" class="btn btn-default">Save</button>
    </form>
</div>
</body>
</html>
