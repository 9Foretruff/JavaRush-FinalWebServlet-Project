<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Registration Result</title>
    <link rel="stylesheet" type="text/css" href="css/registration-result.css">
</head>

<body>
<c:choose>
    <c:when test="${registrationSuccessful==1}">
        <h1 class="neon-title">Registration Successful</h1>
        <div class="container">
            <div class="login-table">
                <p>Thank you for registering, ${username}!</p>
                <a href="/" class="button-link">Go to Home Page</a>
            </div>
        </div>
    </c:when>
    <c:when test="${registrationSuccessful==0}">
        <h1 class="neon-title">Registration Failed</h1>
        <div class="container">
            <div class="login-table">
                <p>User already exists. Please try again.</p>
                <a href="${pageContext.request.contextPath}/registration" class="button-link">Go Back</a>
            </div>
        </div>
    </c:when>
    <c:when test="${registrationSuccessful==-1}">
        <h1 class="neon-title">Registration Failed</h1>
        <div class="container">
            <div class="login-table">
                <p>Passwords do not match. Please try again.</p>
                <a href="${pageContext.request.contextPath}/registration" class="button-link">Go Back</a>
            </div>
        </div>
    </c:when>
</c:choose>
</body>

</html>