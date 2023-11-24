<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Login Failed</title>
    <link rel="stylesheet" type="text/css" href="css/login-failed.css">
</head>

<body>
<h1 class="neon-title">Login Failed</h1>
<div class="container">
    <div class="login-table">
        <p>Invalid username or password. Please try again.</p>
        <a href="${pageContext.request.contextPath}/" class="button-link">Go Back to Login</a>
        <p>Don't have an account?   <a href="${pageContext.request.contextPath}/registration" class="register-button">Register here</a></p>
    </div>
</div>

</body>

</html>
