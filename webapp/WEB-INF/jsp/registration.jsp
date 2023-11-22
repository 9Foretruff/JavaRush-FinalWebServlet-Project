<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <link rel="stylesheet" type="text/css" href="css/registration.css">
</head>

<body>
<h1 class="neon-title">Registration Form</h1>
<div class="container">
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <div class="login-table">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <button type="submit" class="register-button">Register</button>
            <a href="${pageContext.request.contextPath}/" class="register-button">Go Back to Home Page</a>
        </div>
    </form>
</div>
</body>

</html>
