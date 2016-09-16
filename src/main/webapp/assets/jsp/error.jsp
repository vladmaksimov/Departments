<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="error" scope="request" type="java.lang.String"/>

<c:set var="urlMain" value="${pageContext.request.contextPath}/"/>
<c:set var="urlImage" value="${pageContext.request.contextPath}/assets/images/error.jpg"/>

<c:url var="mainPage" value="${urlMain}"/>
<c:url var="errorImage" value="${urlImage}"/>

<html>
<head>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/favicon.ico"/>" rel="shortcut icon" type="image/x-icon"/>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="${pageContext.request.contextPath}/assets/css/main.scss"/>" rel="stylesheet" type="text/css"/>
    <title>Error</title>
</head>
<body>

<div class="container">
    <div class="form-header">
        <span>${error}</span>
        <img src="${errorImage}" />
    </div>
</div>
</body>
</html>
