<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Add question</title>
    <link rel="stylesheet" type="text/css" href="css/add-question.css">
    <link rel="icon" href="https://cdn-icons-png.flaticon.com/512/5455/5455480.png" type="image/png">
</head>

<body>
<h1 class="neon-title">Add Question</h1>
<div class="container">
    <form action="${pageContext.request.contextPath}/addQuestion" method="post" enctype="multipart/form-data">
        <div class="login-table">
            <label for="numberOfQuestion">Number of question:</label>
            <input type="number" id="numberOfQuestion" name="numberOfQuestion" maxlength="35" required>

            <label for="questId">Quest id:</label>
            <input type="number" id="questId" name="questId" step="1" maxlength="35" required>

            <label for="questionText">Text of question:</label>
            <textarea id="questionText" name="questionText" rows="4" cols="100" required></textarea>

            <br>
            <label for="backgroundQuestionPhoto">Background photo of question:</label>
            <input type="file" id="backgroundQuestionPhoto" name="backgroundQuestionPhoto" accept="image/*" required maxlength="10485760">

            <br>
            <label for="isLastQuestion">This question is last?:</label>
            <select id="isLastQuestion" name="isLastQuestion" required>
                <option value="false">No</option>
                <option value="true">Yes</option>
            </select>

            <button type="submit" class="register-button">Create</button>
            <a href="${pageContext.request.contextPath}/createQuest" class="register-button">Go Back</a>
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
