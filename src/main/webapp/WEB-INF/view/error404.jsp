<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>404 error</title>
</head>
<body>

<h1>404 Error</h1>
<a href="${pageContext.request.contextPath}/"><fmt:message key="general.back_to_home"/></a>

</body>
</html>
