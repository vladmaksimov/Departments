<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="department" scope="request" type="com.maksimov.models.Department"/>
<jsp:useBean id="employees" scope="request" type="java.util.List"/>

<c:set var="urlMain" value="${pageContext.request.contextPath}/" />
<c:set var="urlEdit" value="${pageContext.request.contextPath}/department/employee/form" />
<c:set var="urlDelete" value="${pageContext.request.contextPath}/department/employee/delete" />

<c:url value="${urlMain}" var="main"/>

<c:url value="${urlEdit}" var="add">
    <c:param name="department" value="${department.id}"/>
</c:url>

<html>
<head>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/favicon.ico"/>" rel="shortcut icon" type="image/x-icon"/>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="${pageContext.request.contextPath}/assets/css/main.css"/>" rel="stylesheet" type="text/css">
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

                <c:url value="${urlEdit}" var="edit">
                    <c:param name="department" value="${department.id}"/>
                    <c:param name="id" value="${employee.id}"/>
                </c:url>
                
                <c:url value="${urlDelete}" var="delete">
                    <c:param name="department" value="${department.id}"/>
                    <c:param name="id" value="${employee.id}"/>
                </c:url>
                
                <tr>
                    <td>${employee.name}</td>
                    <td>${employee.email}</td>
                    <td>${employee.birthday}</td>
                    <td><a class="btn btn-default" href="${edit}">Edit</a></td>
                    <td><a class="btn btn-default" href="${delete}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <a class="btn btn-success" href="${add}">Add Employee</a>
            <a class="btn btn-info" href="${main}">Back</a>
        </div>
    </form>
</div>
</body>
</html>
