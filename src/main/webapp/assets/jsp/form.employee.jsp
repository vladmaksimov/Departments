<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="employee" scope="request" type="com.maksimov.models.Employee"/>
<jsp:useBean id="errors" scope="request" type="java.util.Map"/>
<jsp:useBean id="departments" scope="request" type="java.util.List"/>

<c:set var="urlBack" value="${pageContext.request.contextPath}/"/>
<c:set var="urlBackToDepartment" value="${pageContext.request.contextPath}/department/employees"/>
<c:set var="urlForm" value="${pageContext.request.contextPath}/department/employee/put"/>

<c:choose>
    <c:when test="${fn:length(departments) == 1}">
        <c:url value="${urlBackToDepartment}" var="back">
            <c:param name="id" value="${departments[0].id}"/>
        </c:url>
    </c:when>
    <c:otherwise>
        <c:url value="${urlBack}" var="back"/>
    </c:otherwise>
</c:choose>


<html>
<head>
    <link rel="shortcut icon" href="<c:url value="/assets/favicon.ico"/>" type="image/x-icon"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/assets/css/main.css"/>"/>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="form-header">
        <c:choose>
            <c:when test="${empty employee.id}">
                Add new Employee
            </c:when>
            <c:otherwise>
                Edit employee: ${employee.name}
            </c:otherwise>
        </c:choose>
    </div>
    <form action="<c:url value="${urlForm}"/>" method="post" class="form-employee">
        <input type="hidden" name="id" value="${employee.id}">
        <c:if test="${fn:length(departments) == 1}">
            <input type="hidden" name="department" value="${departments[0].id}">
        </c:if>

        <c:if test="${not empty departments}">
            <div>
                <label for="name">Select Department</label>
                <div>
                    <select name="department" <c:if test="${fn:length(departments) == 1}"> disabled </c:if>  title="Departments" class="form-control">
                        <c:forEach var="department" items="${departments}">
                            <option value="${department.id}">${department.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <c:forEach var="error" items="${errors.department}">
                    <div>${error}</div>
                </c:forEach>
            </div>
        </c:if>

        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Name"
                   value="<c:out value="${employee.name}"/>">
            <div class="error-validation">

                <c:forEach var="error" items="${errors.name}">
                    <div>${error}</div>
                </c:forEach>
            </div>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Email"
                   value="<c:out value="${employee.email}"/>">
            <div class="error-validation">
                <c:forEach var="error" items="${errors.email}">
                    <div>${error}</div>
                </c:forEach>
            </div>
        </div>
        <div class="form-group">
            <label for="birthday">Birthday</label>
            <input type="date" class="form-control" id="birthday" name="birthday" placeholder="Birthday"
                   value="<c:out value="${employee.birthday}"/>">
            <div class="error-validation">
                <c:forEach var="error" items="${errors.birthday}">
                    <div>${error}</div>
                </c:forEach>
            </div>
        </div>

        <div>
            <button type="submit" class="btn btn-default">Save</button>
            <a class="btn btn-info" href="${back}">Back</a>
        </div>
    </form>
</div>
</body>
</html>
