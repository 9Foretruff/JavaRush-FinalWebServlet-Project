<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Not Authorized</title>
    <link rel="stylesheet" type="text/css" href="css/not-authorized.css">
</head>

<body>
<br>
<br>
<br>
<h1 class="neon-title">Not Authorized</h1>
<div class="container">
    <div class="login-table">
        <p class="neon-title">You are not authorized to access this page. Please log in.</p>
        <a href="${pageContext.request.contextPath}/login" class="register-button">Go to Login</a>
    </div>
</div>
</body>

</html>