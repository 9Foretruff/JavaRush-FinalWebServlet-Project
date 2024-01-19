<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Question Info</title>
    <link rel="stylesheet" type="text/css" href="css/question-info.css">
</head>
<body>
<h1 class="neon-title">Question info</h1>
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
                <p>Background photo</p>
                <div class="center-wrapper">
                    <div class="round-image neon-border">
                        <img src="data:image/jpeg;base64, ${sessionScope.questionInfo.getBase64Image()}" alt="User Photo"
                             width="100" height="100">
                    </div>
                </div>
                <br>

                <div class="user-data">
                    <ul class="neon-table">

                        <li><strong>Id:</strong> ${sessionScope.questionInfo.id}</li>

                        <li><strong>Question number:</strong> ${sessionScope.questionInfo.questionNumber}</li>

                        <li><strong>Quest id:</strong> ${sessionScope.questionInfo.questId}</li>

                        <div class="question-text">
                            <li><strong>Text:</strong> ${sessionScope.questionInfo.text}</li>
                        </div>

                        <li><strong>Is last question:</strong> ${sessionScope.questionInfo.isLastQuestion}</li>

                    </ul>
                </div>

                <br>

                <div class="form-container">
                    <h3>Change Number Of Question</h3>
                    <form action="/change-question-number" method="post">
                        <label for="newQuestionNumber">New Number Of Question:</label>
                        <input type="number" id="newQuestionNumber" name="newQuestionNumber" required>
                        <input type="submit" value="Change">
                    </form>
                </div>

                <br>
                <br>

                <div class="form-container">
                    <h3>Change Quest Id</h3>
                    <form action="/change-question-quest-id" method="post">
                        <label for="newQuestId">New Quest Id:</label>
                        <input type="number" id="newQuestId" name="newQuestId" required>
                        <input type="submit" value="Change">
                    </form>
                </div>

                <br>
                <br>

                <div class="form-container">
                    <h3>Change Text</h3>
                    <form action="/change-question-text" method="post">
                        <label for="newText">New Text:</label>

                        <textarea id="newText" name="newText" rows="4" cols="100" required></textarea>

                        <input type="submit" value="Change">
                    </form>
                </div>

                <br>
                <br>

                <div class="form-container">

                    <h3>Change Is Last Question</h3>

                    <form action="/change-quest-difficulty" method="post">

                        <label for="newStatus">New Status:</label>

                        <select id="newStatus" name="newStatus" required>
                            <option value="true">True</option>
                            <option value="false">False</option>
                        </select>

                        <input type="submit" value="Change">

                    </form>

                </div>

                <br>
                <br>

                <div class="form-container">
                    <h3>Change Background Photo</h3>
                    <form action="/change-question-photo" method="post" enctype="multipart/form-data">
                        <label for="newBackgroundPhoto">New Photo:</label>
                        <input type="file" id="newBackgroundPhoto" name="newBackgroundPhoto" accept="image/*" required
                               maxlength="10485760">
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