<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="urlMain" value="${pageContext.request.contextPath}/"/>
<c:set var="urlImage" value="${pageContext.request.contextPath}/assets/images/problem.jpg"/>

<c:url var="mainPage" value="${urlMain}"/>
<c:url var="problemImage" value="${urlImage}"/>

<html>
<head>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/favicon.ico"/>" rel="shortcut icon" type="image/x-icon"/>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/css/main.css"/>" rel="stylesheet" type="text/css"/>
    <title>404 Page Not Found</title>
</head>
<body>
<nav class="navbar navbar-default navbar-static-top">
    <div class="container form-inline">
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlMain}">Departments</a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="form-header">
        <div class="problem-header">
            <span>Page not found!</span>
            <a class="btn btn-default" href="${mainPage}">To main page</a>
        </div>
        <img src="${problemImage}"/>
    </div>
</div>
</body>
</html>
