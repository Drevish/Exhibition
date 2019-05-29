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
        <br/>

        <a href="${pageContext.request.contextPath}/logout">Log out</a>
        <br/>

        <a href="${pageContext.request.contextPath}/tickets">View your tickets</a>
        <br/>
        <c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
            <a href="${pageContext.request.contextPath}/admin">ADMIN PANEL</a>
        </c:if>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/login">Log in</a>
        <br/>
        <a href="${pageContext.request.contextPath}/register">Register</a>
        <br/>
    </c:otherwise>
</c:choose>

<h2>Showrooms:</h2>
<c:forEach var="showroom" items="${requestScope.showrooms}">
    <h3>${showroom.name}</h3>
    <ul>
        <c:forEach var="exhibit" items="${showroom.exhibits}">
            <li>${exhibit.name} (${exhibit.theme.name})</li>
        </c:forEach>
    </ul>
</c:forEach>

<a href="${pageContext.request.contextPath}/buy">Buy a ticket</a>

</body>
</html>
