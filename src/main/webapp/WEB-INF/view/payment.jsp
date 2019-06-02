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
        <div class="text"><fmt:message key="buy.payment.fill_info"/></div>

        <c:if test="${requestScope.exception != null}">
            <div class="validation-error">
                    ${requestScope.exception.message}
            </div>
        </c:if>

        <form method="post">

            <div class="form-group">
                <label for="name"><fmt:message key="buy.payment.name"/>:</label><br/>
                <c:forEach var="error" items="${requestScope.errors.getErrors('name')}">
                    <div class="validation-error">${error.message}</div>
                </c:forEach>
                <input class="form-control" type="text" name="name" id="name">
                <br/>
            </div>


            <div class="form-group">
                <label for="surname"><fmt:message key="buy.payment.surname"/>:</label><br/>
                <c:forEach var="error" items="${requestScope.errors.getErrors('surname')}">
                    <div class="validation-error">${error.message}</div>
                </c:forEach>
                <input class="form-control" type="text" name="surname" id="surname">
                <br/>
            </div>


            <div class="form-group">
                <label for="price"><fmt:message key="buy.payment.price"/>: </label><br/>
                <c:forEach var="error" items="${requestScope.errors.getErrors('price')}">
                    <div class="validation-error">${error.message}</div>
                </c:forEach>
                <input class="form-control" type="number" min="${requestScope.minPrice}" name="price" id="price">
                <br/>
            </div>

            <button class="btn btn-success btn-block" type="submit">
                <fmt:message key="buy.payment.buy_ticket"/>
            </button>
        </form>
    </div>
</main>

</body>
</html>
