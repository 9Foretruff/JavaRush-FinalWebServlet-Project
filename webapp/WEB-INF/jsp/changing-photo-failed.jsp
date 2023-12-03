<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Photo Change Failed</title>
    <link rel="stylesheet" type="text/css" href="css/email-change-failed.css">
</head>
<body>

<div class="container">
    <h1 class="neon-title">Changing Photo Failed</h1>
    <p class="neon-title">Sorry, there was an error while changing the photo. Please try again.</p>
    <form class="navigation" action="${pageContext.request.contextPath}/menu" method="get">
        <input type="submit" value="Go to Menu">
    </form>

    <form class="navigation" action="${pageContext.request.contextPath}/profile" method="get">
        <input type="submit" value="Go to Profile">
    </form>
</div>

</body>
</html>
