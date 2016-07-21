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
    <link rel="shortcut icon" href="/assets/favicon.ico" type="image/x-icon"/>
    <title>Department</title>
</head>
<body>
<form method="get" name="department">
    <table border="1" width="500px">
        <thead>
        <tr>
            <td>id</td>
            <td>name</td>
            <td colspan="3">actions</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="department" items="${departments}">
            <tr>
                <td>${department.id}</td>
                <td>${department.name}</td>
                <td><a type="button" href="/department/form?id=${department.id}">Edit</a></td>
                <td><a type="button" href="/department/delete?id=${department.id}">delete</a></td>
                <td><a type="button" href="/department/employees?id=${department.id}">Show employees</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/department/form">Add new</a>
</form>

</body>
</html>
