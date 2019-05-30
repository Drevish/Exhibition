<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        .validation-error {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Register into Exhibition</h1>

    <c:if test="${requestScope.exception != null}" >
        <div class="validation-error">
                ${requestScope.exception.message}
        </div>
    </c:if>

    <c:if test="${requestScope.errors.hasErrors()}">
        <div class="validation-error">Wrong data, please fill correctly</div>
    </c:if>

    <form method="post">
        <label for="email">Email:</label>
        <br/>
        <c:forEach var="error" items="${requestScope.errors.getErrors('email')}">
            <div class="validation-error">${error.message}</div>
        </c:forEach>
        <input type="email" name="email" id="email">
        <br>

        <label for="password">Password:</label>
        <br/>
        <c:forEach var="error" items="${requestScope.errors.getErrors('password')}">
            <div class="validation-error">${error.message}</div>
        </c:forEach>
        <input type="password" name="password" id="password">
        <br>

        <button type="submit">Register</button>
    </form>
</body>
</html>
