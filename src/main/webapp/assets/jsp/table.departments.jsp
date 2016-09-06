<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="departments" scope="request" type="java.util.List"/>

<c:set var="urlAdd" value="${pageContext.request.contextPath}/department/form" />
<c:set var="urlAddEmployee" value="${pageContext.request.contextPath}/department/employee/form/clear" />
<c:set var="urlDelete" value="${pageContext.request.contextPath}/department/delete" />
<c:set var="urlEmployee" value="${pageContext.request.contextPath}/department/employees" />

<c:url value="${urlAdd}" var="add"/>
<c:url value="${urlAddEmployee}" var="addEmployee"/>

<html>
<head>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/favicon.ico"/>" rel="shortcut icon" type="image/x-icon"/>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/css/main.css"/>" rel="stylesheet" type="text/css"/>
    <title>Department</title>
</head>
<body>
<div class="container">
    <div class="form-header">
        <span>Department List:</span>
    </div>
    <form method="get" name="department">
        <div>
            <table class="table table-striped table-department">
                <thead>
                <tr>
                    <th class="column-id">id</th>
                    <th class="column-name">name</th>
                    <th class="column-action" colspan="3">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="department" items="${departments}">

                    <c:url value="${urlAdd}" var="edit">
                        <c:param name="id" value="${department.id}"/>
                    </c:url>

                    <c:url value="${urlDelete}" var="delete">
                        <c:param name="id" value="${department.id}"/>
                    </c:url>

                    <c:url value="${urlEmployee}" var="employee">
                        <c:param name="id" value="${department.id}"/>
                    </c:url>

                    <tr>
                        <td>${department.id}</td>
                        <td>${department.name}</td>
                        <td><a class="btn btn-default" href="${edit}">Edit</a></td>
                        <td><a class="btn btn-default" href="${delete}">delete</a></td>
                        <td><a class="btn btn-default" href="${employee}">Show employees</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div>
            <a class="btn btn-success" href="${add}">Add Department</a>
            <a class="btn btn-success" href="${addEmployee}">Add Employee</a>
        </div>
    </form>
</div>
</body>
</html>
