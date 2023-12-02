<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Email Change Failed</title>
    <link rel="stylesheet" type="text/css" href="css/password-change-failed.css">
</head>
<body>

<div class="container">
    <h1 class="neon-title">Email Change Failed</h1>
    <p>There was an error while updating the password. Please try again later.</p>

    <form action="${pageContext.request.contextPath}/menu" method="get">
        <input type="submit" value="Go to Menu">
    </form>

    <form action="${pageContext.request.contextPath}/profile" method="get">
        <input type="submit" value="Go to Profile">
    </form>
</div>

</body>
</html>
