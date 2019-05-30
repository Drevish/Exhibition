<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Buy a ticket</title>
</head>
<body>

<h1>Fill the ticket info form:</h1>

<c:if test="${requestScope.exception != null}" >
    <div class="validation-error">
            ${requestScope.exception.message}
    </div>
</c:if>

<form method="post">
    <label for="date">Date:</label><br/>
    <c:forEach var="error" items="${requestScope.errors.getErrors('date')}">
        <div class="validation-error">${error.message}</div>
    </c:forEach>
    <input type="date" name="date" id="date"><br/>

    <label for="theme">Exhibition theme</label><br/>
    <select name="theme" id="theme">
        <c:forEach var="theme" items="${requestScope.themes}">
            <option value="${theme.id}">${theme.name}</option>
        </c:forEach>
    </select>
    <br/>

    <button type="submit">Process payment</button>
</form>

</body>
</html>
