<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="css/error-page.css">
</head>

<body>
<h1 class="neon-title">Error page</h1>
    <div class="container">
        <div class="login-table">
            <p>We encountered a problem while processing your request.</p>
            <p>I will be grateful if you write to me and tell how you got this error</p>
            <a href="${pageContext.request.contextPath}/menu" class="register-button">Go to menu</a>
            <a href="${pageContext.request.contextPath}/profile" class="register-button">Go to profile</a>
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
