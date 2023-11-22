<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Quest</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>

<body>
<h1 class="neon-title">Quest digital</h1>
<div class="container">
    <h1>Welcome to our website!</h1>
    <p>We are glad to see you here.</p>
    <p>We hope that you enjoy your visit.</p>

    <!-- Табличка регистрации и логина -->
    <div class="login-table">
        <form action="processLogin.jsp" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Login</button>
        </form>
        <p>Don't have an account?<a href="${pageContext.request.contextPath}/registration" class="register-button">Register here</a></p>
<%--        <p>Don't have an account? <a href="WEB-INF/jsp/registration.jsp" class="register-button">Register here</a></p>--%>
    </div>
</div>

<div class="social-media-window">
    <!-- Полоска с соцсетями -->
    <div class="social-media-bar">
        <a href="https://www.linkedin.com/in/maksim-rokitko-b9a722272/" target="_blank">
            <img src="https://seeklogo.com/images/L/linkedin-black-icon-logo-ECC426C572-seeklogo.com.png"
                 alt="linkedIn">
        </a>
        <a href="https://github.com/9Foretruff" target="_blank">
            <img src="https://cdn-icons-png.flaticon.com/512/25/25231.png" alt="gitHub">
        </a>
        <a href="https://www.instagram.com/foretruff/" target="_blank">
            <img src="https://seeklogo.com/images/I/instagram-new-2016-glyph-logo-84CB825424-seeklogo.com.png"
                 alt="instagram">
        </a>
    </div>
</div>

<div class="footer">
    <p>Made by Rokitko Maksim</p>
</div>

</body>

</html>
