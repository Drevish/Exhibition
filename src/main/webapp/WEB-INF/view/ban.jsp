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
            <div><fmt:message key="ban.banned"/></div>
            <a href="/logout" class="btn btn-danger m-2"><fmt:message key="header.log_out"/> </a>
        </div>
    </div>
</main>
</body>
</html>
