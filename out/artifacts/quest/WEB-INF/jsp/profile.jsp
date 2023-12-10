<%@ page import="com.adventurequest.model.service.UserService" %>
<%@ page import="com.adventurequest.model.entity.UserEntity" %>
<%@ page import="java.util.Base64" %>
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
<%
    UserEntity userFromSession = (UserEntity) request.getSession().getAttribute("user");
    UserService userService = UserService.getInstance();
    UserEntity user = userService.getUserByUsername(userFromSession.getUsername()).orElse(new UserEntity(null, null, null, null, null));
    byte[] photoBytes = user.getPhoto();
    String password = user.getPassword();
    String username = user.getUsername();
    String email = user.getEmail();
    String ipAddress = request.getRemoteAddr();
    Long gamesPlayed = user.getGamesPlayed();
    String base64Encoded = Base64.getEncoder().encodeToString(photoBytes);
%>

<h1 class="neon-title">Profile</h1>
<br>
<br>
<br>

<table class="neon-table">
    <tr>
        <td>
            <br>
            <h1 class="neon-text">Welcome, <%=username%>!</h1>
            <br>


            <div class="center-wrapper">
                <div class="round-image neon-border">
                    <img src="data:image/jpeg;base64, <%=base64Encoded %>" alt="User Photo" width="100" height="100">
                </div>
            </div>
            <br>

            <div class="user-data">
                <ul class="neon-table">
                    <li><strong>Username:</strong> <c:out value="<%=username%>"/></li>
                    <li><strong>Password:</strong> <c:out value="<%=password%>"/></li>
                    <li><strong>Email:</strong> <c:out value="<%=email%>"/></li>
                    <li><strong>IP Address:</strong> <c:out value="<%=ipAddress%>"/></li>
                    <li><strong>Games Played:</strong> <c:out value="<%=gamesPlayed%>"/></li>
                </ul>
            </div>

            <h3>Change Password</h3>
            <form action="changePassword" method="post">
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword" required>
                <input type="submit" value="Change Password">
            </form>

            <h3>Change Email</h3>
            <form action="changeEmail" method="post">
                <label for="newEmail">New Email:</label>
                <input type="email" id="newEmail" name="newEmail" required>
                <input type="submit" value="Change Email">
            </form>

            <h3>Change Photo</h3>
            <form action="uploadPhoto" method="post" enctype="multipart/form-data">
                <label for="newPhoto">Upload Photo:</label>
                <input type="file" id="newPhoto" name="newPhoto" accept="image/*" required maxlength="10485760">
                <input type="submit" value="Upload Photo">
            </form>

            <form action="${pageContext.request.contextPath}/menu" method="get">
                <input type="submit" value="Go Back">
            </form>

            <form action="${pageContext.request.contextPath}/logout" method="get">
                <input type="submit" value="Log Out">
            </form>

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