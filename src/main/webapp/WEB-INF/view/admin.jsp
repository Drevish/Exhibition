<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Admin panel</title>
</head>
<body>

<h1>Welcome to admin panel!</h1>

<div>All users list:</div>
<table border="1">
    <thead>
        <tr>
            <td>Email</td>
            <td>Role</td>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td>${user.email}</td>
            <td>${user.role}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
