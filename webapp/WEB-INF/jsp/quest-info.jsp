<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quest Info</title>
    <link rel="stylesheet" type="text/css" href="css/quest-info.css">
</head>
<body>
<h1 class="neon-title">Quest info</h1>
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
                        <img src="data:image/jpeg;base64, ${sessionScope.questInfo.getBase64Image()}" alt="User Photo"
                             width="100" height="100">
                    </div>
                </div>
                <br>

                <div class="user-data">
                    <ul class="neon-table">

                        <li><strong>Id:</strong> ${sessionScope.questInfo.id}</li>

                        <li><strong>Name of quest:</strong> ${sessionScope.questInfo.name}</li>

                        <li><strong>Difficulty:</strong> ${sessionScope.questInfo.difficulty}</li>

                        <li><strong>Author:</strong> ${sessionScope.questInfo.author}</li>

                        <li><strong>Status:</strong> ${sessionScope.questInfo.status}</li>

                        <div class="question-text">
                            <li><strong>Description:</strong> ${sessionScope.questInfo.description}</li>
                        </div>

                    </ul>
                </div>

                <br>

                <div class="form-container">
                    <h3>Change Name Of Quest</h3>
                    <form action="${pageContext.request.contextPath}/change-quest-name" method="post">

                        <input type="hidden" name="questId" value="${sessionScope.questInfo.id}">

                        <label for="newName">New name of quest:</label>

                        <input type="text" id="newName" name="newName" maxlength="25" required>

                        <input type="submit" value="Change">

                    </form>
                </div>

                <br>
                <br>

                <div class="form-container">
                    <h3>Change Description</h3>
                    <form action="${pageContext.request.contextPath}/change-quest-description" method="post">

                        <input type="hidden" name="questId" value="${sessionScope.questInfo.id}">

                        <label for="newDescription">New Description:</label>

                        <textarea id="newDescription" name="newDescription" rows="4" cols="100" required></textarea>

                        <input type="submit" value="Change">

                    </form>

                </div>

                <br>
                <br>

                <div class="form-container">

                    <h3>Change Difficulty</h3>

                    <form action="${pageContext.request.contextPath}/change-quest-difficulty" method="post">

                        <input type="hidden" name="questId" value="${sessionScope.questInfo.id}">

                        <label for="newDifficulty">New Difficulty:</label>

                        <select id="newDifficulty" name="newDifficulty" required>
                            <option value="EASY">Easy</option>
                            <option value="MEDIUM">Medium</option>
                            <option value="HARD">Hard</option>
                        </select>

                        <input type="submit" value="Change">

                    </form>

                </div>

                <br>
                <br>

                <div class="form-container">

                    <h3>Change Status</h3>

                    <form action="${pageContext.request.contextPath}/change-quest-status" method="post">

                        <input type="hidden" name="questId" value="${sessionScope.questInfo.id}">

                        <label for="newStatus">New Status:</label>

                        <select id="newStatus" name="newStatus" required>
                            <option value="DRAFT">Draft</option>
                            <option value="PUBLISHED">Published</option>
                        </select>

                        <input type="submit" value="Change">

                    </form>

                </div>

                <br>
                <br>

                <div class="form-container">

                    <h3>Change Quest Photo</h3>
                    <form action="${pageContext.request.contextPath}/change-quest-photo" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="questId" value="${sessionScope.questInfo.id}">
                        <label for="newPhoto">New Photo:</label>
                        <input type="file" id="newPhoto" name="newPhoto" accept="image/*" required maxlength="10485760">
                        <input type="submit" value="Upload Photo">
                    </form>

                </div>

                <br>
                <br>

                <form action="${pageContext.request.contextPath}/create-quest" method="get">
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