<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad-m
  Date: 7/19/2016
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="/assets/favicon.ico" type="image/x-icon" />
    <title>Title</title>
</head>
<body>
<form name="employees" method="get">
    <input type="hidden" name="department" value="${department}">
    <table border="1" width="500px">
        <thead>
        <tr>
            <td>Name</td>
            <td>Email</td>
            <td>Birthday</td>
            <td colspan="2">Action</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td>${employee.name}</td>
                <td>${employee.email}</td>
                <td>${employee.birthday}</td>
                <td><a href="/department/employee/form?id=${employee.id}">Edit</a></td>
                <td><a href="/department/employee/delete?department=${department}&id=${employee.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/department/employee/form?department=${department}">Add new employee</a>
</form>

</body>
</html>
