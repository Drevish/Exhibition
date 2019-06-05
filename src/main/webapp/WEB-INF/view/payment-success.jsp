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
</head>
<body>

<main>
    <div class="container text-center">
        <h1><fmt:message key="buy.payment.success.caption"/></h1>
        <h3><fmt:message key="buy.payment.success.information"/></h3>

        <div class="ticket">
            <div><fmt:message key="ticket.ticket_email"/>: ${requestScope.ticket.user.email}</div>
            <div><fmt:message key="ticket.ticket_date"/>: ${requestScope.ticket.date}</div>
            <div><fmt:message key="ticket.exhibition_theme"/>: ${requestScope.ticket.theme.name}</div>
            <div><fmt:message key="ticket.ticket_owner"/>:
                ${requestScope.ticket.payment.name} ${requestScope.ticket.payment.surname}
            </div>
            <div><fmt:message key="ticket.price"/>: ${requestScope.ticket.payment.price}</div>
        </div>

        <a href="${pageContext.request.contextPath}/">Back to home</a>
    </div>
</main>

<style>
    main {
        margin-top: 50px;
    }
</style>

</body>
</html>
