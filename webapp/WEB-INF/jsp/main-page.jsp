<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Quest</title>
    <link rel="stylesheet" type="text/css" href="../../css/menu.css">
</head>

<body>
<div class="container">
    <h1 class="neon-title">Quest digital</h1>

    <div class="profile-link">
        <a href="${pageContext.request.contextPath}/profile">
            <img src="../../img/profile.png" alt="Profile">
        </a>
    </div>

    <div class="quests-list">
        <h2 class="neon-title">Quests</h2>
        <ul>
            <c:forEach var="quest" items="${quests}">
                <li>
                    <a href="/quest?id=${quest.id}" class="neon-text">${quest.name}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="social-media-window">
    <div class="social-media-bar">
        <a href="https://www.linkedin.com/in/maksim-rokitko-b9a722272/" target="_blank">
            <img src="https://seeklogo.com/images/L/linkedin-black-icon-logo-ECC426C572-seeklogo.com.png"
                 alt="linkedIn" class="neon-border">
        </a>
        <a href="https://github.com/9Foretruff" target="_blank">
            <img src="https://cdn-icons-png.flaticon.com/512/25/25231.png" alt="gitHub" class="neon-border">
        </a>
        <a href="https://www.instagram.com/foretruff/" target="_blank">
            <img src="https://seeklogo.com/images/I/instagram-new-2016-glyph-logo-84CB825424-seeklogo.com.png"
                 alt="instagram" class="neon-border">
        </a>
    </div>
</div>

<div class="footer">
    <p>Made by Rokitko Maksim</p>
</div>

</body>

</html>
