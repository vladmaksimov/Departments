<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<jsp:useBean id="page" scope="request" type="org.springframework.data.domain.PageImpl"/>--%>
<%--@elvariable id="search" type="java.lang.String"--%>

<c:import url="../util/pagination.jsp" scope="request" var="pagination"/>

<c:set var="urlAddDepartment" value="${pageContext.request.contextPath}/department/form"/>
<c:set var="urlAddEmployee" value="${pageContext.request.contextPath}/department/employee/form/clear"/>
<c:set var="urlDelete" value="${pageContext.request.contextPath}/department/delete"/>
<c:set var="urlEmployee" value="${pageContext.request.contextPath}/department/employees"/>
<c:set var="urlForm" value="${pageContext.request.contextPath}/"/>

<c:url value="${urlAddDepartment}" var="addDepartment"/>
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
                <li><a href="${addDepartment}">Add Department<span class="sr-only">(current)</span></a></li>
                <li><a href="${addEmployee}">Add Employee</a></li>
            </ul>

            <form class="navbar-form navbar-right" action="${urlForm}">
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
        <span>Department List:</span>
    </div>

    <c:if test="${not empty page.content}">
        ${pagination}
    </c:if>

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
            <c:forEach var="department" items="${page.content}">

                <c:url value="${addDepartment}" var="edit">
                    <c:param name="id" value="${department.id}"/>
                </c:url>

                <c:url value="${urlDelete}" var="delete">
                    <c:param name="id" value="${department.id}"/>
                </c:url>

                <c:url value="${urlEmployee}/${department.id}" var="employee"/>

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
        <c:if test="${empty page.content && empty search}">
            <div class="text-center">
                <span>Department list is empty! Add new department</span>
                <div>
                    <a href="${addDepartment}" class="btn btn-default">Add Department</a>
                </div>
            </div>
        </c:if>
        <c:if test="${empty page.content && not empty search}">
            <div class="text-center">
                <span>No page found matching: </span>
                <span>${search}</span>
            </div>
        </c:if>
    </div>

    <c:if test="${not empty page.content}">
        ${pagination}
    </c:if>

</div>
</body>
</html>
