<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Quest</title>
    <link rel="stylesheet" type="text/css" href="css/menu.css">
</head>

<body>

<div class="container">
    <h1 class="neon-title">Quest digital</h1>
    <br>

    <div class="profile-link">
        <a href="${pageContext.request.contextPath}/profile">
            <img src="../../img/profile.png" alt="Profile">
        </a>
    </div>

    <div class="login-table">
        <a href="${pageContext.request.contextPath}/create-quest" class="add-quest-button">Create quest</a>
    </div>

    <div class="quests-list">
        <h2 class="neon-title">Quests</h2>
        <ul>
            <c:forEach var="quest" items="${sessionScope.publishedQuests}">
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

                    <form action="${pageContext.request.contextPath}/preview-quest">
                        <input type="hidden" name="questId" value="${quest.id}">
                        <button type="submit" class="add-quest-button">Preview quest</button>
                    </form>

                </div>
            </c:forEach>
        </ul>
    </div>


    <div class="login-table777">

        <c:choose>
            <c:when test="${sessionScope.currentPage > 5}">
                <c:forEach begin="${sessionScope.currentPage - 4}" end="${sessionScope.currentPage + 4}" var="i">
                    <c:choose>
                        <c:when test="${sessionScope.currentPage eq i}">
                            <form action="${pageContext.request.contextPath}/menu" method="get">
                                <input type="hidden" name="page" value="${i}" }>
                                <button type="submit" class="button777-current">${i}</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="${pageContext.request.contextPath}/menu" method="get">
                                <input type="hidden" name="page" value="${i}" }>
                                <button type="submit" class="button777">${i}</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach begin="1" end="9" var="i">

                    <c:choose>
                        <c:when test="${sessionScope.currentPage eq i}">
                            <form action="${pageContext.request.contextPath}/menu" method="get">
                                <input type="hidden" name="page" value="${i}" }>
                                <button type="submit" class="button777-current">${i}</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="${pageContext.request.contextPath}/menu" method="get">
                                <input type="hidden" name="page" value="${i}" }>
                                <button type="submit" class="button777">${i}</button>
                            </form>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </c:otherwise>

        </c:choose>

        <form action="/menu" method="get" class="neon-table">
            <label for="page">Enter page number:</label>
            <input type="number" id="page" name="page">
        </form>

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