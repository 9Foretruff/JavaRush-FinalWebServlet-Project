<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Preview Quest</title>
    <link rel="stylesheet" type="text/css" href="css/preview-quest.css">
</head>
<body>
<h1 class="neon-title">Preview quest</h1>
<br>
<br>
<br>

<table class="neon-table">
    <tr>
        <td>
            <br>
            <h1 class="neon-text">Welcome, ${sessionScope.user.username}!</h1>
            <br>

            <div class="login-table">
                <p>Quest photo</p>
                <div class="center-wrapper">
                    <div class="round-image neon-border">
                        <img src="data:image/jpeg;base64, ${sessionScope.previewQuest.getBase64Image()}" alt="User Photo"
                             width="100" height="100">
                    </div>
                </div>
                <br>

                <div class="user-data">
                    <ul class="neon-table">

                        <li><strong>Id:</strong> ${sessionScope.previewQuest.id}</li>

                        <li><strong>Name of quest:</strong> ${sessionScope.previewQuest.name}</li>

                        <li><strong>Difficulty:</strong> ${sessionScope.previewQuest.difficulty}</li>

                        <li><strong>Author:</strong> ${sessionScope.previewQuest.author}</li>

                        <div class="question-text">
                            <li><strong>Description:</strong> ${sessionScope.previewQuest.description}</li>
                        </div>

                    </ul>
                </div>

                <form action="${pageContext.request.contextPath}/start-quest" method="get">
                    <input type="hidden" name="questId" value="${quest.id}">
                    <button type="submit" class="add-quest-button">Start Quest</button>
                </form>

                <form action="${pageContext.request.contextPath}/menu" method="get">
                    <input type="submit" value="Go Back">
                </form>

            </div>
        </td>
    </tr>
</table>

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