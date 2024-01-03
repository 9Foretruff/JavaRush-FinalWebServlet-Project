<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Quest</title>
    <link rel="stylesheet" type="text/css" href="css/menu.css">
</head>

<body>

<div class="container">
    <h1 class="neon-title">Quest digital</h1>
    <br>

    <div class="profile-link">
        <a href="${pageContext.request.contextPath}/profile">
            <img src="../../img/profile.png" alt="Profile">
        </a>
    </div>

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/create-quest" class="add-quest-button">Create quest</a>
    </div>

    <div class="quests-list">
        <h2 class="neon-title">Quests</h2>
        <ul>
            <c:forEach var="quest" items="${quests}">
                <li>
                    <a href="${pageContext.request.contextPath}/quest?id=${quest.id}" class="neon-text">${quest.name}</a>
                </li>
            </c:forEach>
        </ul>
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
