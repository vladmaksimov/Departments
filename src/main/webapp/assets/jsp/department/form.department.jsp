<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="department" scope="request" type="com.maksimov.models.Department"/>
<jsp:useBean id="errors" scope="request" type="java.util.Map"/>

<c:set var="mainUrl" value="${pageContext.request.contextPath}/" />

<c:url value="${mainUrl}" var="mainPage"/>

<html>
<head>
    <link rel="shortcut icon" href="<c:url value="/assets/favicon.ico"/>" type="image/x-icon"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/main.css"/>"/>
    <title>Add department</title>
</head>
<body>
<nav class="navbar navbar-default navbar-static-top">
    <div class="container form-inline">
        <div class="navbar-header">
            <a class="navbar-brand" href="${mainPage}">Departments</a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="form-header">
        <c:choose>
            <c:when test="${empty department.id}">
                Add new Department
            </c:when>
            <c:otherwise>
                Edit department: ${department.name}
            </c:otherwise>
        </c:choose>
    </div>
    <form action="/department/put" method="post" class="form-employee">
        <input type="hidden" name="id" value="<c:out value="${department.id}"/>"/>

        <div class="form-group">
            <label for="name">Department name:</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Email" value="<c:out value="${department.name}"/>"/>
            <div class="error-validation">
                <c:forEach var="error" items="${errors.name}">
                    <div>${error}</div>
                </c:forEach>
            </div>
        </div>
        <button type="submit" class="btn btn-default">Save</button>
        <a class="btn btn-info" href="${mainPage}">Back</a>
    </form>
</div>

</body>
</html>
