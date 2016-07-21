<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="/assets/favicon.ico" type="image/x-icon" />
    <title>Add department</title>
</head>
<body>
<form action="/department/put" method="post">
    <input type="hidden" name="id" value="<c:out value="${department.id}"/>" />
    <input type="text" name="name" placeholder="Name" value="<c:out value="${department.name}"/>" />
    <button type="submit">Save</button>
</form>

</body>
</html>
