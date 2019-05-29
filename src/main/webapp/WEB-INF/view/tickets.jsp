<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Your tickets</title>
</head>
<body>

<h2>Your tickets list:</h2>

<c:forEach var="ticket" items="${requestScope.tickets}">
    <h4>Ticket</h4>
    <div>Ticket email: ${ticket.user.email}</div>
    <div>Ticket date: ${ticket.date}</div>
    <div>Exhibition theme: ${ticket.theme.name}</div>
    <div>Ticket owner: ${ticket.payment.name} ${ticket.payment.surname}</div>
    <div>Price: ${ticket.payment.price}</div>
    <br/>
</c:forEach>
<a href="${pageContext.request.contextPath}/">Back to home</a>

</body>
</html>
