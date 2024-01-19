<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Failed to change quest name</title>
    <link rel="stylesheet" type="text/css" href="css/email-change-failed.css">
</head>
<body>
<h1 class="neon-title">Failed to change quest name</h1>
<div class="container">
    <div class="login-table">
        <p>You already have quest with this name or you are trying to change not your quest. Please try again.</p>
        <a href="${pageContext.request.contextPath}/quest-info?questId=${sessionScope.questInfo.id}" class="register-button">Go back</a>
        <a href="${pageContext.request.contextPath}/menu" class="register-button">Go to menu</a>
    </div>
</div>
</body>
</html>