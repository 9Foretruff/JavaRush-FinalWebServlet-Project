<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Add quest</title>
    <link rel="stylesheet" type="text/css" href="css/add-quest.css">
    <link rel="icon" href="https://cdn-icons-png.flaticon.com/512/5455/5455480.png" type="image/png">
</head>

<body>
<h1 class="neon-title">Add Quest</h1>
<div class="container">
    <form action="${pageContext.request.contextPath}/addQuest" method="post" enctype="multipart/form-data">
        <div class="login-table">
            <label for="questName">Name of quest:</label>
            <input type="text" id="questName" name="questName" maxlength="255" required>

            <label for="questDescription">Description of quest:</label>
            <textarea id="questDescription" name="questDescription" rows="4" cols="50" required></textarea>

            <br>
            <label for="questPhoto">Photo of quest:</label>
            <input type="file" id="questPhoto" name="questPhoto" accept="image/*" required maxlength="10485760">

            <br>
            <label for="questDifficulty">Difficulty of quest:</label>
            <select id="questDifficulty" name="questDifficulty" required>
                <option value="easy">Easy</option>
                <option value="medium">Medium</option>
                <option value="hard">Hard</option>
            </select>

            <button type="submit" class="register-button">Create</button>
            <a href="${pageContext.request.contextPath}/addQuest" class="register-button">Go Back</a>
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