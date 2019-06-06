<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Maintenance</title>
</head>
<body>

<h1>
    <fmt:message key="maintenance.unavailable"/>
    <fmt:message key="maintenance.try_later"/>
</h1>

</body>
</html>
