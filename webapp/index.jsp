<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Quest</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="icon" href="img/start-page-icon.png" type="image/png">
</head>

<body>
<h1 class="neon-title">Quest digital</h1>
<div class="container">
    <h1>Welcome to our website!</h1>
    <p>We are glad to see you here.</p>
    <p>We hope that you enjoy your visit.</p>

    <div class="login-table">
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <button type="submit">Log in</button>
            <a href="${pageContext.request.contextPath}/registration" class="register-button">Register here</a>
        </form>
    </div>
</div>

<div class="social-media-window">
    <div class="social-media-bar">
        <a href="https://www.linkedin.com/in/maksim-rokitko-b9a722272/" target="_blank">
            <img src="img/linkenIn-logo.png"
                 alt="linkedIn">
        </a>
        <a href="https://github.com/9Foretruff" target="_blank">
            <img src="img/gitHub-logo.png" alt="gitHub">
        </a>
        <a href="https://www.instagram.com/foretruff/" target="_blank">
            <img src="img/instagram-logo.png"
                 alt="instagram">
        </a>
    </div>
</div>

<div class="footer">
    <p>Made by Rokitko Maksim</p>
</div>

</body>

</html>
