<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Registration</title>

    <%@include file="template/styles.jsp" %>
    <link rel="stylesheet" href="/static/css/center-form.css">
</head>
<body>


<main>
    <div class="center-form">
        <h3>Exhibition</h3>
        <div class="text"><fmt:message key="register.register"/></div>
        <c:if test="${requestScope.exception != null}">
            <div class="validation-error">
                    ${requestScope.exception.message}
            </div>
        </c:if>

        <c:if test="${requestScope.errors.hasErrors()}">
            <div class="validation-error">Wrong data, please fill correctly</div>
        </c:if>

        <form method="post">
            <div class="form-group">
                <label for="email"><fmt:message key="general.email"/>:</label>
                <br/>
                <c:forEach var="error" items="${requestScope.errors.getErrors('email')}">
                    <div class="validation-error">${error.message}</div>
                </c:forEach>
                <input class="form-control" type="email" name="email" id="email">
                <br>
            </div>


            <div class="form-group">
                <label for="password"><fmt:message key="general.password"/>:</label>
                <br/>
                <c:forEach var="error" items="${requestScope.errors.getErrors('password')}">
                    <div class="validation-error">${error.message}</div>
                </c:forEach>
                <input class="form-control" type="password" name="password" id="password">
                <br>
            </div>

            <button class="btn btn-primary btn-block" type="submit"><fmt:message key="register.register"/></button>
        </form>
    </div>
</main>

</body>
</html>
