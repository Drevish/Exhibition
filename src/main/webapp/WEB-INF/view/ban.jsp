<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Restricted</title>

    <%@include file="template/styles.jsp" %>
</head>
<body>
<main>
    <div class="container">
        <div class="m-5">
            <div>Your account has been banned.</div>
            <a href="/logout" class="btn btn-danger m-2">Log out</a>
        </div>
    </div>
</main>
</body>
</html>
