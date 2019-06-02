<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Buy a ticket</title>

    <%@include file="template/styles.jsp" %>
    <link rel="stylesheet" href="/static/css/center-form.css">
</head>
<body>

<main>
    <div class="center-form">
        <h1>Exhibition</h1>
        <div class="text"><fmt:message key="buy.choose"/></div>

        <c:if test="${requestScope.exception != null}">
            <div class="validation-error">
                    ${requestScope.exception.message}
            </div>
        </c:if>

        <form method="post">

            <div class="form-group">
                <label for="date"><fmt:message key="buy.date"/>:</label><br/>
                <c:forEach var="error" items="${requestScope.errors.getErrors('date')}">
                    <div class="validation-error">${error.message}</div>
                </c:forEach>
                <input class="form-control" type="date" name="date" id="date"><br/>
            </div>

            <div class="form-group">
                <label for="theme"><fmt:message key="buy.theme"/></label><br/>
                <select class="form-control" name="theme" id="theme">
                    <c:forEach var="theme" items="${requestScope.themes}">
                        <option value="${theme.id}">${theme.name}</option>
                    </c:forEach>
                </select>
                <br/>
            </div>

            <button class="btn btn-primary btn-block" type="submit"><fmt:message key="buy.process"/></button>
        </form>
    </div>
</main>

</body>
</html>
