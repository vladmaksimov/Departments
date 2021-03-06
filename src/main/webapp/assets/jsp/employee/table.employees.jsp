<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="/assets/jsp/employee/urlParams.jsp" scope="request" var="urlParams"/>

<jsp:useBean id="department" scope="request" type="com.maksimov.models.Department"/>
<jsp:useBean id="employees" scope="request" type="java.util.List"/>
<%--@elvariable id="search" type="java.lang.String"--%>

<c:set var="urlMain" value="${pageContext.request.contextPath}/"/>
<c:set var="urlEmployee" value="${pageContext.request.contextPath}/department/employees"/>
<c:set var="urlEdit" value="${pageContext.request.contextPath}/department/employee/form"/>
<c:set var="urlDelete" value="${pageContext.request.contextPath}/department/employee/delete"/>
<c:set var="test" value="/assets/jsp/employee/urlParams.jsp"/>

<c:url value="${urlMain}" var="main"/>

<c:url value="${urlEdit}" var="add">
    <c:param name="department" value="${department.id}"/>
</c:url>

<c:url value="${urlEmployee}" var="employee">
    <c:param name="id" value="${department.id}"/>
</c:url>

<c:url value="${urlEmployee}" var="urlForm">
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

<nav class="navbar navbar-default navbar-static-top">
    <div class="container form-inline">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Departments</a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <c:if test="${not empty search}">
                    <li><a href="${employee}">To Employees</a></li>
                </c:if>
                <li><a href="${add}">Add Employee</a></li>
            </ul>

            <form class="navbar-form navbar-right" action="${urlForm}">
                ${urlParams}
                <div class="form-group">
                    <input type="text"
                           class="form-control"
                           placeholder="Search"
                           name="search"
                           <c:if test="${not empty search}"> value="${search}" </c:if>
                    >
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
</nav>
<div class="container">
    <div class="form-header">
        <span>Employees of: ${department.name}</span>
    </div>

    <c:if test="${not empty employees}">
        <%@ include file="/assets/jsp/util/pagination.jsp" %>
    </c:if>

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
    <c:if test="${empty employees && empty search}">
        <div class="text-center">
            <span>Employee list is empty! Add new employee</span>
            <div>
                <a href="${add}" class="btn btn-default">Add Employee</a>
            </div>
        </div>
    </c:if>
    <c:if test="${empty employees && not empty search}">
        <div class="text-center">
            <span>No employees found matching: </span>
            <span>${search}</span>
        </div>
    </c:if>

    <c:if test="${not empty employees}">
        <%@ include file="/assets/jsp/util/pagination.jsp" %>
    </c:if>

</div>
</body>
</html>
