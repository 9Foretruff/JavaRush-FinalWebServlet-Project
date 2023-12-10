<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logout Error</title>
    <link rel="stylesheet" type="text/css" href="css/logout-failed.css">
</head>
<body>
<h1 class="neon-title">Logout Error</h1>
<div class="container">
    <div class="login-table">
        <p>There was an error during the logout process. Please try again.</p>
        <a href="${pageContext.request.contextPath}/login" class="register-button" >Login</a>
        <a href="${pageContext.request.contextPath}/registration" class="register-button" >Register</a>
    </div>
</div>
</body>
</html>

