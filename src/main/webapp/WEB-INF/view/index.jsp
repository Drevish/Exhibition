<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title>Exhibition</title>

    <%@include file="template/styles.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}static/css/index.css">
</head>
<body>

<%@include file="template/header.jsp" %>

<main>
    <div class="container">

        <h1><fmt:message key="index.welcome"/></h1>


        <h2><fmt:message key="index.showrooms"/></h2>
        <ul>
        <c:forEach var="showroom" items="${requestScope.showrooms}">
            <li><a href="/showroom/${showroom.name}">${showroom.name}</a></li>
        </c:forEach>
        </ul>

    </div>
</main>

</body>
</html>
