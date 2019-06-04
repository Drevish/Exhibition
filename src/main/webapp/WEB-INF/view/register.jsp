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
    <link rel="stylesheet" href="/static/css/register.css">
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
                <label id="label-password">
                    <span class="popup"><fmt:message key="register.password-info"/></span>
                    <fmt:message key="general.password"/>: <span class="required">*</span>
                </label>
                <br/>

                <c:forEach var="error" items="${requestScope.errors.getErrors('password')}">
                    <div class="validation-error">${error.message}</div>
                </c:forEach>
                <input class="form-control" type="password" name="password" id="password">
                <br>
            </div>


            <div class="form-group">
                <label>
                    <fmt:message key="general.password-repeat"/>
                </label>
                <br/>

                <div class="validation-error">${requestScope.password_repeat}</div>
                <input class="form-control" type="password" name="password_repeat" id="password_repeat">
                <br>
            </div>

            <button class="btn btn-primary btn-block" type="submit"><fmt:message key="register.register"/></button>
        </form>
    </div>
</main>

<script>
    var password = document.getElementById("password")
        , confirm_password = document.getElementById("password_repeat");

    function validatePassword(){
        if(password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords don't match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>
</body>
</html>
