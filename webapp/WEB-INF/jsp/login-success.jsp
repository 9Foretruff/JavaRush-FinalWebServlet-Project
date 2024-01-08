<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Login success</title>
    <link rel="stylesheet" type="text/css" href="css/login-success.css">
    <link rel="icon" href="img/login-success-mark.png" type="image/png">
</head>

<body>
<h1 class="neon-title">Login Success</h1>
<div class="container">
    <div class="login-table">
        <p>Login success.</p>
        <form action="${pageContext.request.contextPath}/menu" method="get" class="neon-table">
            <button type="submit" class="add-quest-button">Go to main page</button>
        </form>
    </div>
</div>

</body>

</html>