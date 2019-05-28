<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Hi there</title>
</head>
<body>

<h1>Hello!</h1>

<c:choose>
    <c:when test="${sessionScope.user!=null}">
        <h2>You are logged in by email: ${sessionScope.user.email}</h2>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/login">Log in</a>
        <br/>
        <a href="${pageContext.request.contextPath}/register">Register</a>
    </c:otherwise>
</c:choose>
<br/>

<h2>Showrooms:</h2>
<c:forEach var="showroom" items="${requestScope.showrooms}">
    <h3>${showroom.name}</h3>
    <ul>
        <c:forEach var="exhibit" items="${showroom.exhibits}">
            <li>${exhibit.name} (${exhibit.theme.name})</li>
        </c:forEach>
    </ul>
</c:forEach>

</body>
</html>
