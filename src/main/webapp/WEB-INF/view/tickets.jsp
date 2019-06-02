<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Your tickets</title>

    <%@include file="template/styles.jsp" %>
    <link rel="stylesheet" href="/static/css/tickets.css">
</head>
<body>

<main>
    <div class="container">
        <h2 class="tickets-caption"><fmt:message key="tickets.ticket_list"/></h2>

        <c:forEach var="ticket" items="${requestScope.tickets}">
            <div class="ticket">
                <h4 class="ticket-caption"><fmt:message key="tickets.ticket"/></h4>
                <div class="ticket-field">
                    <span class="ticket-field-name"><fmt:message key="ticket.ticket_email"/>:</span>
                    <span class="ticket-field-value">${ticket.user.email}</span>
                </div>
                <div class="ticket-field">
                    <span class="ticket-field-name"><fmt:message key="ticket.ticket_date"/>:</span>
                    <span class="ticket-field-value">${ticket.date}</span>
                </div>
                <div class="ticket-field">
                    <span class="ticket-field-name"><fmt:message key="ticket.exhibition_theme"/>:</span>
                    <span class="ticket-field-value">${ticket.theme.name}</span>
                </div>
                <div class="ticket-field">
                    <span class="ticket-field-name"><fmt:message key="ticket.ticket_owner"/>:</span>
                    <span class="ticket-field-value">${ticket.payment.name} ${ticket.payment.surname}</span>
                </div>
                <div class="ticket-field">
                    <span class="ticket-field-name"><fmt:message key="ticket.price"/>:</span>
                    <span class="ticket-field-value">${ticket.payment.price}</span>
                </div>
            </div>
        </c:forEach>

        <a href="${pageContext.request.contextPath}/" class="link-back"><fmt:message key="general.back_to_home"/></a>
    </div>
</main>

</body>
</html>
