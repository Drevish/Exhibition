<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
  <h1>Log in Exhibition</h1>
  <form method="post">
    <label for="email">Email:</label>
    <input type="email" name="email" id="email">
    <br>

    <label for="password">Password:</label>
    <input type="password" name="password" id="password">
    <br>

    <button type="submit">Log in</button>
  </form>
  </body>
</html>
