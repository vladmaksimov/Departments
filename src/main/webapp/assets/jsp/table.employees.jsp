<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="/assets/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/main.css">
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="form-header">
        <span>Employees of: ${department.name}</span>
    </div>
    <form name="employees" method="get">
        <input type="hidden" name="department" value="${department.id}">
        <table class="table table-striped table-employee">
            <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Birthday</th>
                <th class="column-action" colspan="2">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.name}</td>
                    <td>${employee.email}</td>
                    <td>${employee.birthday}</td>
                    <td><a class="btn btn-default" href="/department/employee/form?id=${employee.id}">Edit</a></td>
                    <td><a class="btn btn-default"
                           href="/department/employee/delete?department=${department.id}&id=${employee.id}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <a class="btn btn-success" href="/department/employee/form?department=${department.id}">Add Employee</a>
            <a class="btn btn-info" href="/">Back</a>
        </div>
    </form>
</div>
</body>
</html>
