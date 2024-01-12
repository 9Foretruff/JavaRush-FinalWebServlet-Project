<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="css/profile.css">
</head>
<body>

<h1 class="neon-title">Profile</h1>
<br>
<br>
<br>

<table class="neon-table">
    <tr>
        <td>
            <br>
            <h1 class="neon-text">Welcome, ${sessionScope.user.username}!</h1>
            <br>

            <div class="login-table">
                <div class="center-wrapper">
                    <div class="round-image neon-border">
                        <img src="data:image/jpeg;base64, ${sessionScope.user.getBase64Image()}" alt="User Photo"
                             width="100" height="100">
                    </div>
                </div>
                <br>

                <div class="user-data">
                    <ul class="neon-table">

                        <li><strong>Id:</strong> ${sessionScope.user.id}</li>

                        <li><strong>Username:</strong> ${sessionScope.user.username}</li>

                        <li><strong>Password:</strong> ${sessionScope.user.password}</li>

                        <li><strong>Email:</strong> ${sessionScope.user.email}</li>

                        <li><strong>IP Address:</strong> <%= request.getRemoteAddr() %>
                        </li>

                        <li><strong>Games Played:</strong> ${sessionScope.user.gamesPlayed}</li>

                    </ul>
                </div>

                <br>

                <div class="form-container">
                    <h3>Change Password</h3>
                    <form action="change-user-password" method="post">
                        <label for="newPassword">New Password:</label>
                        <input type="password" id="newPassword" name="newPassword" required>
                        <input type="submit" value="Change Password">
                    </form>
                </div>

                <br>
                <br>

                <div class="form-container">
                    <h3>Change Email</h3>
                    <form action="change-user-email" method="post">
                        <label for="newEmail">New Email:</label>
                        <input type="email" id="newEmail" name="newEmail" required>
                        <input type="submit" value="Change Email">
                    </form>
                </div>

                <br>
                <br>

                <div class="form-container">
                    <h3>Change Photo</h3>
                    <form action="change-user-photo" method="post" enctype="multipart/form-data">
                        <label for="newPhoto">New Photo:</label>
                        <input type="file" id="newPhoto" name="newPhoto" accept="image/*" required maxlength="10485760">
                        <input type="submit" value="Upload Photo">
                    </form>
                </div>

                <br>
                <br>

                <form action="${pageContext.request.contextPath}/menu" method="get">
                    <input type="submit" value="Go Back">
                </form>

                <form action="${pageContext.request.contextPath}/logout" method="get">
                    <input type="submit" value="Log Out">
                </form>
            </div>
        </td>
    </tr>
</table>

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