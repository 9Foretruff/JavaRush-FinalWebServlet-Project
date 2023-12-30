<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
    <title>Instruction how to create quest</title>
    <link rel="stylesheet" type="text/css" href="css/creation-quest-instruction.css">
</head>

<body>

<div class="container">
    <h1 class="neon-title">This is instructions how to make your own quests</h1>
    <br>

    <p>Firstly you need create quest on this page</p>

    <div class="login-table">
            <a href="${pageContext.request.contextPath}/create-quest" class="register-button">Create here</a>
    </div>

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/create-quest" class="add-quest-button">Create quest</a>
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
