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
        <a href="${pageContext.request.contextPath}/createQuestInstruction" class="add-quest-button">Instruction how to create quest</a>
    </div>

    <div class="quests-list">
        <h2 class="neon-title">Your quests</h2>
        <ul>
            <c:forEach var="quest" items="${myQuests}">
                <li>
                    <a href="${pageContext.request.contextPath}/quest?id=${quest.id}"
                       class="neon-text">${quest.name}</a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/addQuest" class="add-quest-button">Add quest</a>
    </div>

    <div class="quests-list">
        <h2 class="neon-title">Your questions</h2>
        <form action="showQuestsWithName" method="post" class="neon-table">
            <label for="newQuestion">Show question for quest with name:</label>
            <input type="text" id="newQuestion" name="newQuestion">
            <input type="submit" value="Show questions">
        </form>
        <ul>
            <c:forEach var="quest" items="${myQuestions}">
                <li>
                    <a href="${pageContext.request.contextPath}/question?id=${quest.id}"
                       class="neon-text">${quest.name}</a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/addQuestion" class="add-quest-button">Add question to quest</a>
    </div>

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

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/menu" class="add-quest-button">Go back</a>
    </div>

</div>

</body>
</html>