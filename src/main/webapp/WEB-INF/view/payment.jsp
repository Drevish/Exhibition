<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Buy a ticket</title>
</head>
<body>

<h1>Fill the payment info</h1>

<c:if test="${requestScope.exception != null}" >
    <div class="validation-error">
            ${requestScope.exception.message}
    </div>
</c:if>

<form method="post">
    <label for="name">Your name:</label><br/>
    <c:forEach var="error" items="${requestScope.errors.getErrors('name')}">
        <div class="validation-error">${error.message}</div>
    </c:forEach>
    <input type="text" name="name" id="name"><br/>

    <label for="surname">Your surname:</label><br/>
    <c:forEach var="error" items="${requestScope.errors.getErrors('surname')}">
        <div class="validation-error">${error.message}</div>
    </c:forEach>
    <input type="text" name="surname" id="surname"><br/>

    <label for="price">Price you are ready to pay: </label><br/>
    <input type="number" min="${requestScope.minPrice}" name="price" id="price"><br/>
    
    <button type="submit">Buy ticket</button>
</form>

</body>
</html>
