<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Admin panel</title>

    <%@include file="template/styles.jsp" %>
    <link rel="stylesheet" href="/static/css/admin_user.css">
</head>
<body>

<main>
    <div class="container">

        <c:choose>
            <c:when test="${requestScope.exception != null}">
                <h4 class="validation-error">${requestScope.exception.message}</h4>
            </c:when>
            <c:otherwise>

                <form method="post">
                    <h3>User info:</h3>
                    <div class="email">Email: ${requestScope.user.email}</div>

                    <div class="form-group form-inline">
                        <label for="role">Role: </label>
                        <select class="custom-select ml-2" name="role" id="role">
                            <option value="user"
                                    <c:if test="${requestScope.user.role == 'USER'}"> selected</c:if>>
                                User
                            </option>
                            <option value="admin"
                                    <c:if test="${requestScope.user.role == 'ADMIN'}"> selected</c:if>>
                                Admin
                            </option>
                        </select>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="active" name="active" value="true"
                        <c:if test="${requestScope.user.active}"> checked</c:if>>

                        <label class="form-check-label" for="active">Active</label>
                    </div>

                    <button class="btn btn-success">Save</button>
                </form>

            </c:otherwise>
        </c:choose>

        <a href="/admin" class="link-back"><fmt:message key="admin.users_list"/> </a>
        <br/>

        <a href="/" class="link-back"><fmt:message key="general.back_to_home"/></a>
    </div>
</main>

<style>
    main {
        margin-top: 50px;
    }
</style>
</body>
</html>
