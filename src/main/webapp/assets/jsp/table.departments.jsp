<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="<c:url value="${pageContext.request.contextPath}/assets/favicon.ico"/>" type="image/x-icon"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/main.css"/>" type="text/css"/>
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
                    <tr>
                        <td>${department.id}</td>
                        <td>${department.name}</td>
                        <td><a class="btn btn-default" href="<c:url value="${pageContext.request.contextPath}/department/form?id=${department.id}"/>">Edit</a></td>
                        <td><a class="btn btn-default" href="<c:url value="/department/delete?id=${department.id}"/>">delete</a></td>
                        <td><a class="btn btn-default" href="<c:url value="/department/employees?id=${department.id}"/>">Show employees</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div>
            <a class="btn btn-success" href="/department/form">Add Department</a>
        </div>
    </form>
</div>
</body>
</html>
