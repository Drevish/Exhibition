<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Buy a ticket</title>
</head>
<body>

<h1>You have bought a ticket successfully!</h1>
<h3>Information:</h3>
<div>Ticket email: ${requestScope.ticket.user.email}</div>
<div>Ticket date: ${requestScope.ticket.date}</div>
<div>Exhibition theme: ${requestScope.ticket.theme.name}</div>
<div>Ticket owner: ${requestScope.ticket.payment.name} ${requestScope.ticket.payment.surname}</div>
<div>Price: ${requestScope.ticket.payment.price}</div>

<a href="${pageContext.request.contextPath}/">Back to home</a>

</body>
</html>
