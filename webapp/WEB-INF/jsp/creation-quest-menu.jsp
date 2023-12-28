<%@ page import="java.util.Base64" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Create quest menu</title>
    <link rel="stylesheet" type="text/css" href="css/creation-quest-menu.css">
</head>

<body>

<div class="container">
    <h1 class="neon-title">Create quest</h1>
    <br>

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/createQuestInstruction" class="add-quest-button">Instruction how to
            create quest</a>
    </div>

    <div class="quests-list">
        <h2 class="neon-title">Your quests</h2>

        <c:forEach var="quest" items="${myQuests}">

            <div class="quest-card">

                <div class="quest-img">
                    <img src="data:image/jpeg;base64,${quest.getBase64Image()}" alt="Quests Photo" width="100"
                         height="100">
                    <div class="quest-id neon-border">${quest.id}</div>
                </div>

                <p>${quest.name}</p>
                <p>Author: ${quest.author}</p>
                <p>Status: ${quest.status}</p>


                <form action="preview-quest">
                    <input type="hidden" name="questId" value="${quest.id}">
                    <button type="submit">Preview quest</button>
                </form>

            </div>

        </c:forEach>

    </div>

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/addQuest" class="add-quest-button">Add quest</a>
    </div>
</div>

<br>
<br>
<br>

<div class="container">
    <h1 class="neon-title">Create question</h1>
    <div class="quests-list">
        <h2 class="neon-title">Your questions</h2>
        <form action="showQuestWithId" method="post" class="neon-table">
            <label for="newQuestion">Show question for quest with id:</label>
            <input type="text" id="newQuestion" name="newQuestion">
            <input type="submit" value="Show questions">
        </form>
        <c:forEach var="question" items="${myQuestions}">

            <div class="quest-card">

                <div class="quest-img">
                    <img src="data:image/jpeg;base64,${question.getBase64Image()}" alt="Quests Photo" width="100"
                         height="100">
                    <div class="quest-id neon-border">${question.id}</div>
                </div>

                <p>Number of question:${question.numberOfQuestion}</p>
                <p>Quest id: ${question.questId}</p>
                <p>Is last question: ${question.isLastQuestion}</p>


                <form action="preview-quest">
                    <input type="hidden" name="questId" value="${quest.id}">
                    <button type="submit">Preview quest</button>
                </form>

            </div>

        </c:forEach>
    </div>

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/addQuestion" class="add-quest-button">Add question to quest</a>
    </div>
</div>

<br>
<br>
<br>

<div class="container">
    <h1 class="neon-title">Create answer</h1>
    <div class="quests-list">
        <h2 class="neon-title">Your answers</h2>
        <form action="showAnswersForQuest" method="post" class="neon-table">
            <label for="newAnswer">Show answers for quest:</label>
            <input type="text" id="newAnswer" name="newAnswer">
            <input type="submit" value="Show questions">
        </form>
        <ul>
            <c:forEach var="quest" items="${myAnswers}">
                <li>
                    <a href="${pageContext.request.contextPath}/quest?id=${quest.id}"
                       class="neon-text">${quest.name}</a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/addAnswer" class="add-quest-button">Add answer to quest</a>
    </div>
</div>

<br>
<br>
<br>

<div class="container">
    <div class="login-table">
        <a href="${pageContext.request.contextPath}/menu" class="add-quest-button">Go back</a>
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