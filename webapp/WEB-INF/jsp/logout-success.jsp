<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logout Successful</title>
    <link rel="stylesheet" type="text/css" href="css/logout-success.css">
</head>
<body>

<h1 class="neon-title">Logout Successful</h1>
<div class="container">
    <div class="login-table">
        <p>You have been successfully logged out.</p>
        <a href="${pageContext.request.contextPath}/login" class="register-button" >Login</a>
        <a href="${pageContext.request.contextPath}/registration" class="register-button" >Register</a>
    </div>
</div>

</body>
</html>