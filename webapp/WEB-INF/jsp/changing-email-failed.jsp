<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Change Failed</title>
    <link rel="stylesheet" type="text/css" href="css/email-change-failed.css">
</head>
<body>

<div class="container">
    <h1 class="neon-title">Email Change Failed</h1>
    <p class="neon-title">The email address is already in use. Please choose another one.</p>
    <form class="navigation" action="${pageContext.request.contextPath}/menu" method="get">
        <input type="submit" value="Go to Menu">
    </form>

    <form class="navigation" action="${pageContext.request.contextPath}/profile" method="get">
        <input type="submit" value="Go to Profile">
    </form>
</div>

</body>
</html>