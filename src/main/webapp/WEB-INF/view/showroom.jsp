<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title>${requestScope.showroom.name}</title>

    <%@include file="template/styles.jsp" %>
    <link rel="stylesheet" href="/static/css/showroom.css">
</head>
<body>

<%@include file="template/header.jsp" %>

<main>
    <div class="container">
        <h1><fmt:message key="showroom.showroom"/> "${requestScope.showroom.name}"</h1>
        <div class="caption"><fmt:message key="showroom.exhibits"/></div>
        <c:forEach var="exhibit" items="${requestScope.showroom.exhibits}">
            <div class="exhibit">
                <div class="text"><b>${exhibit.name}</b></div>
                <div class="text"><fmt:message key="showroom.Theme"/> ${exhibit.theme.name}</div>
                <img src="/static/image/${exhibit.name.replaceAll(":", "")}.jpg" alt="${exhibit.name}" width="500">
            </div>
        </c:forEach>

        <div class="link-back"><a href="/"><fmt:message key="general.back_to_home"/> </a></div>
    </div>
</main>

<script>
    $("img").click(function (event) {
        $(event.target).toggleClass("enlarged");
    });
</script>

</body>
</html>
