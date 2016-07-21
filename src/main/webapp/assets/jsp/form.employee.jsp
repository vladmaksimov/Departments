<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="/assets/favicon.ico" type="image/x-icon"/>
    <title>Title</title>
</head>
<body>
<form action="/department/employee/put" method="post">
    <input type="hidden" name="department" value="${department}">
    <input type="hidden" name="id" value="${employee.id}">
    <div>
        <input type="text" name="name" placeholder="Name" value="<c:out value="${employee.name}"/>"/>
        <label><font color="red">${errors.name}</font></label>
    </div>

    <div>
        <input type="email" name="email" placeholder="Email" value="<c:out value="${employee.email}"/>"/>
        <label><font color="red">${errors.email}</font></label>
    </div>
    <div>
        <input type="date" name="birthday" placeholder="Birthday" value="<c:out value="${employee.birthday}"/>"/>
        <label><font color="red">${errors.birthday}</font></label>
    </div>
    <button type="submit" value="/department/employee/put">Save</button>
</form>

</body>
</html>
