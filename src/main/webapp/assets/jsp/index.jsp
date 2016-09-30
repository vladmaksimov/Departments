<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<html>
<head>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/favicon.ico"/>" rel="shortcut icon"
          type="image/x-icon"/>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/css/main.css"/>" rel="stylesheet"
          type="text/css"/>
    <script src="/assets/js/modules/jquery/jquery-3.1.1.min.js"></script>
    <script src="/assets/js/modules/departments/constants/Constants.js"></script>
    <script src="/assets/js/modules/departments/services/DepartmentService.js"></script>
    <script src="/assets/js/modules/departments/drawServices/DepartmentDrawService.js"></script>
    <script src="/assets/js/modules/departments/drawServices/ErrorsDrawService.js"></script>
    <script src="/assets/js/modules/departments/controllers/DepartmentController.js"></script>
    <script src="/assets/js/modules/departments/main.js"></script>
    <title>Department</title>
</head>
<body>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container form-inline">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Departments</a>
        </div>
    </div>
</nav>

<div class="main container"></div>
</body>
</html>
