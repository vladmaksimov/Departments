<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="departments" scope="request" type="java.util.List"/>

<c:set var="urlAdd" value="${pageContext.request.contextPath}/department/form"/>
<c:set var="urlAddEmployee" value="${pageContext.request.contextPath}/department/employee/form/clear"/>
<c:set var="urlDelete" value="${pageContext.request.contextPath}/department/delete"/>
<c:set var="urlEmployee" value="${pageContext.request.contextPath}/department/employees"/>
<c:set var="urlForm" value="${pageContext.request.contextPath}/"/>

<c:url value="${urlAdd}" var="add"/>
<c:url value="${urlAddEmployee}" var="addEmployee"/>
<c:url value="${urlForm}" var="department"/>

<html>
<head>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/favicon.ico"/>" rel="shortcut icon" type="image/x-icon"/>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/css/main.css"/>" rel="stylesheet" type="text/css"/>
    <title>Department</title>
</head>
<body>


<nav class="navbar navbar-default navbar-static-top">
    <div class="container form-inline">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Departments</a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${add}">Add Department<span class="sr-only">(current)</span></a></li>
                <li><a href="${addEmployee}">Add Employee</a></li>
            </ul>

            <form class="navbar-form navbar-right" action="department/search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
</nav>
<form method="get" name="department" action="<c:url value="${department}"/>">
    <c:if test="${not empty search}">
        <input type="hidden" name="search">
    </c:if>
    <div class="container">
        <div class="form-header">
            <span>Department List:</span>
        </div>

        <div>
            <div class="text-center">
                <ul class="pagination form-inline">
                    <li>
                        <button class="btn btn-default">
                            <span class="glyphicon glyphicon-menu-left" aria-hidden="true"/>
                        </button>
                    </li>
                    <li>
                        <button class="btn btn-sm">1/3</button>
                    </li>
                    <li>
                        <button class="btn btn-default">
                            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"/>
                        </button>
                    </li>
                </ul>
            </div>
        </div>
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
            <div class="form-inline">
                <div>qwe</div>
                <div class="text-center">
                    <ul class="pagination form-inline">
                        <li>
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-menu-left" aria-hidden="true"/>

                            </button>
                        </li>
                        <li>
                            <button class="btn btn-sm">1/3</button>
                        </li>
                        <li>
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-menu-right" aria-hidden="true"/>
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</form>

</body>
</html>
