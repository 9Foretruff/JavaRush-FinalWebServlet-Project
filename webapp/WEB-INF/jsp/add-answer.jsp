<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Add answer</title>
    <link rel="stylesheet" type="text/css" href="css/add-answer.css">
    <link rel="icon" href="https://cdn-icons-png.flaticon.com/512/5455/5455480.png" type="image/png">
</head>

<body>
<h1 class="neon-title">Add Answer</h1>
<div class="container">
    <form action="${pageContext.request.contextPath}/add-answer" method="post" enctype="application/x-www-form-urlencoded">
        <div class="login-table">
            <label for="questionId">Question id:</label>
            <input type="number" id="questionId" name="questionId" maxlength="35" required>

            <label for="answerText">Text of answer:</label>
            <textarea id="answerText" name="answerText" rows="4" cols="100" required></textarea>

            <br>
            <label for="isAnswerCorrect">Answer is correct?:</label>
            <select id="isAnswerCorrect" name="isAnswerCorrect" required>
                <option value="true">Yes</option>
                <option value="false">No</option>
            </select>

            <button type="submit" class="register-button">Create</button>
            <a href="${pageContext.request.contextPath}/create-quest" class="register-button">Go Back</a>
        </div>
    </form>
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