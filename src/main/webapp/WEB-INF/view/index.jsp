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
        <c:forEach var="showroom" items="${requestScope.showrooms}">
            <h3>${showroom.name}</h3>
            <ul>
                <c:forEach var="exhibit" items="${showroom.exhibits}">
                    <li>${exhibit.name} (${exhibit.theme.name})</li>
                </c:forEach>
            </ul>
        </c:forEach>

    </div>
</main>

</body>
</html>
