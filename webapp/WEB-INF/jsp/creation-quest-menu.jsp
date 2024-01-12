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
        <a href="${pageContext.request.contextPath}/create-quest-instruction" class="add-quest-button">Instruction how to create quest</a>
    </div>


    <div class="login-table">
        <a href="${pageContext.request.contextPath}/menu" class="add-quest-button">Go back</a>
    </div>

    <div class="quests-list">
        <h2 class="neon-title">Your quests</h2>

        <c:forEach var="quest" items="${sessionScope.myQuests}">

            <div class="quest-card">
                <p>Quest photo</p>
                <div class="quest-img ">
                    <div class="quest-id2 neon-border">
                        Id
                    </div>
                    <img src="data:image/jpeg;base64,${quest.getBase64Image()}" alt="Quests Photo" width="100"
                         height="100">
                    <div class="quest-id neon-border">
                            ${quest.id}
                    </div>
                </div>

                <p>Name: ${quest.name}</p>
                <p>Author: ${quest.author}</p>
                <p>Status: ${quest.status}</p>

                <form action="${pageContext.request.contextPath}/quest-info">
                    <input type="hidden" name="questId" value="${quest.id}">
                    <button type="submit" class="add-quest-button">See all information and edit</button>
                </form>

                <form action="${pageContext.request.contextPath}/start-quest">
                    <input type="hidden" name="questId" value="${quest.id}">
                    <button type="submit" class="add-quest-button">Start quest</button>
                </form>

            </div>

            <br>
            <br>
        </c:forEach>

    </div>

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/add-quest" class="add-quest-button">Add quest</a>
    </div>
</div>

<br>
<br>
<br>

<div class="container">
    <h1 class="neon-title">Create question</h1>
    <div class="quests-list">
        <h2 class="neon-title">Your questions</h2>
        <form action="/show-questions-for-quest" method="post" class="neon-table">
            <label for="newQuestion">Show question for quest with id:</label>
            <input type="number" id="newQuestion" name="newQuestion">
        </form>
        <br>
        <form action="${pageContext.request.contextPath}/show-all-questions">
            <button type="submit" class="add-quest-button">Show all questions</button>
        </form>
        <br>
        <br>
        <c:forEach var="question" items="${sessionScope.myQuestions}">

            <div class="quest-card">
                <p>Background photo</p>
                <div class="quest-img">
                    <div class="quest-id2 neon-border">
                        Id
                    </div>
                    <img src="data:image/jpeg;base64,${question.getBase64Image()}" alt="Quests Photo" width="100"
                         height="100">
                    <div class="quest-id neon-border">${question.id}</div>
                </div>

                <p>Number of question:${question.numberOfQuestion}</p>
                <p>Quest id: ${question.questId}</p>
                <p>Is last question: ${question.isLastQuestion}</p>
                <div class="question-text">
                    <p>Question text:${question.text}</p>
                </div>

                <form action="${pageContext.request.contextPath}/question-info" method="get">
                    <input type="hidden" name="questionId" value="${question.id}">
                    <button type="submit" class="add-quest-button">See all information and edit</button>
                </form>

            </div>

            <br>
            <br>

        </c:forEach>
    </div>

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/add-question" class="add-quest-button">Add question to quest</a>
    </div>
</div>

<br>
<br>
<br>

<div class="container">
    <h1 class="neon-title">Create answer</h1>
    <div class="quests-list">
        <h2 class="neon-title">Your answers</h2>
        <form action="show-answers-for-quest" method="post" class="neon-table">
            <label for="questId">Show answers for quest with id:</label>
            <input type="number" id="questId" name="questId">
        </form>
        <br>
        <form action="${pageContext.request.contextPath}/show-all-answers">
            <button type="submit" class="add-quest-button">Show all answers</button>
        </form>
        <br>
        <br>
        <c:forEach var="answer" items="${sessionScope.myAnswers}">
            <div class="quest-card">
                <br>
                <br>
                <c:choose>


                    <c:when test="${answer.isCorrect}">
                        <div class="quest-img">
                            <div class="quest-id2 neon-border">
                                Id
                            </div>
                            <img src="img/login-success-mark.png" alt="Quests Photo" width="100"
                                 height="100">
                            <div class="quest-id neon-border">${answer.id}</div>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="quest-img">
                            <div class="quest-id2 neon-border">
                                Id
                            </div>
                            <img src="img/login-failed-mark.png" alt="Quests Photo" width="100"
                                 height="100">
                            <div class="quest-id neon-border">${answer.id}</div>
                        </div>
                    </c:otherwise>

                </c:choose>

                <p>Question id: ${answer.questionId}</p>
                <p>Is correct: ${answer.isCorrect}</p>
                <div class="question-text">
                    <p>Answer text:${answer.text}</p>
                </div>


                <form action="${pageContext.request.contextPath}/answer-info">
                    <input type="hidden" name="answerId" value="${answer.id}">
                    <button type="submit" class="add-quest-button">See all information and edit</button>
                </form>

            </div>

            <br>
            <br>

        </c:forEach>
    </div>

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/add-answer" class="add-quest-button">Add answer to quest</a>
    </div>
</div>

<br>
<br>
<br>

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