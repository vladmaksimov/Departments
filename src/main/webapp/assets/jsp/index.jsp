<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/assets/favicon.ico"/>" rel="shortcut icon" type="image/x-icon"/>
    <link href="<c:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/assets/css/main.css"/>" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/assets/js/modules/jquery/jquery-3.1.1.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/lodash/lodash.core.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/constants/Constants.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/services/PageService.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/services/DepartmentService.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/services/EmployeeService.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/utils/DrawUtils.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/utils/ControllerUtils.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/drawServices/PaginationDrawService.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/drawServices/EmployeeDrawService.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/drawServices/DepartmentDrawService.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/drawServices/ErrorsDrawService.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/controllers/EmployeeController.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/controllers/DepartmentController.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modules/departments/main.js"></script>
    <title>Department</title>
</head>
<body>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container form-inline">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Departments</a>
        </div>
    </div>
</nav>

<div class="main container"></div>
</body>
</html>
