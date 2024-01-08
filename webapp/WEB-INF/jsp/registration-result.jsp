<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Registration Result</title>
    <link rel="stylesheet" type="text/css" href="css/registration-result.css">
    <link rel="icon" href="img/registration-result-icon.png" type="image/png">
</head>

<body>
<c:choose>
    <c:when test="${sessionScope.registrationResult eq 1}">
        <h1 class="neon-title">Registration Successful</h1>
        <div class="container">
            <div class="login-table">
                <p>Thank you for registering, ${sessionScope.username}!</p>
                <a href="/" class="register-button">Go to Home Page</a>
            </div>
        </div>
    </c:when>
    <c:when test="${sessionScope.registrationResult eq 0}">
        <h1 class="neon-title">Registration Failed</h1>
        <div class="container">
            <div class="login-table">
                <p>User with username ${sessionScope.username} already exists or email ${sessionScope.email} is already in use. Please try again.</p>
                <a href="${pageContext.request.contextPath}/registration" class="register-button">Go Back</a>
            </div>
        </div>
    </c:when>
    <c:when test="${sessionScope.registrationResult eq -1}">
        <h1 class="neon-title">Registration Failed</h1>
        <div class="container">
            <div class="login-table">
                <p>Passwords do not match. Please try again.</p>
                <a href="${pageContext.request.contextPath}/registration" class="register-button">Go Back</a>
            </div>
        </div>
    </c:when>
</c:choose>
</body>

</html>