<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Question Info</title>
    <link rel="stylesheet" type="text/css" href="css/answer-info.css">
</head>
<body>
<h1 class="neon-title">Answer info</h1>
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
                <div class="center-wrapper">
                    <c:choose>

                        <c:when test="${sessionScope.answer.isCorrect}">
                            <div class="round-image neon-border">
                                <img src="img/login-success-mark.png" alt="Quests Photo" width="100" height="100">
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div class="round-image neon-border">
                                <img src="img/login-failed-mark.png" alt="Quests Photo" width="100" height="100">
                            </div>
                        </c:otherwise>

                    </c:choose>
                </div>
                <br>

                <div class="user-data">
                    <ul class="neon-table">

                        <li><strong>Id:</strong> ${sessionScope.answer.id}</li>

                        <li><strong>Question id:</strong> ${sessionScope.answer.questionId}</li>

                        <div class="question-text">
                            <li><strong>Text:</strong> ${sessionScope.answer.text}</li>
                        </div>

                        <li><strong>Is correct:</strong> ${sessionScope.answer.isCorrect}</li>

                    </ul>
                </div>

                <br>

                <div class="form-container">
                    <h3>Change Question Id</h3>
                    <form action="change-answer-question-id" method="post">
                        <label for="newQuestionId">New Question Id:</label>
                        <input type="number" id="newQuestionId" name="newQuestionId" required>
                        <input type="submit" value="Change">
                    </form>
                </div>

                <br>
                <br>

                <div class="form-container">
                    <h3>Change Text</h3>
                    <form action="change-answer-text" method="post">

                        <label for="newText">New Text:</label>

                        <textarea id="newText" name="newText" rows="4" cols="100" required></textarea>

                        <input type="submit" value="Change">

                    </form>

                </div>

                <br>
                <br>

                <div class="form-container">

                    <h3>Change Is Correct</h3>

                    <form action="change-answer-is-correct" method="post">

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