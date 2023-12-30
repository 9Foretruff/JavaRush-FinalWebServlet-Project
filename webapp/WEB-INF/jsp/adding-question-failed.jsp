<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adding Question Failed</title>
    <link rel="stylesheet" type="text/css" href="css/adding-question-failed.css">
</head>
<body>
<h1 class="neon-title">Adding Question Failed</h1>
<div class="container">
    <div class="login-table">
        <p>This question is already exist or last question is already exist. Please try to create another one.</p>
        <a href="${pageContext.request.contextPath}/add-question" class="register-button">Go back</a>
        <a href="${pageContext.request.contextPath}/menu" class="register-button">Go to menu</a>
    </div>
</div>
</body>
</html>
