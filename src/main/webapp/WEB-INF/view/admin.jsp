<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Admin panel</title>

    <%@include file="template/styles.jsp" %>
    <link rel="stylesheet" href="/static/css/admin.css">
</head>
<body>

<main>
    <div class="container">
        <h1><fmt:message key="admin.welcome"/></h1>

        <table class="table table-bordered table-hover ">
            <div class="table-caption"><fmt:message key="admin.users_list"/></div>
            <thead class="thead-light">
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

        <a href="/" class="link-back"><fmt:message key="general.back_to_home"/></a>
    </div>
</main>

</body>
</html>
