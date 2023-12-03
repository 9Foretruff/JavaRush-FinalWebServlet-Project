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

<table class="neon-table">
    <tr>
        <td>
            <h2 class="neon-text">Welcome, <%=username%>!</h2>

            <img class="profile-link" src="data:image/jpeg;base64, <%=base64Encoded %>" alt="User Photo" width="100" height="100">

            <ul class="neon-table">
                <li><strong>Username:</strong> <c:out value="<%=username%>"/></li>
                <li><strong>Password:</strong> <c:out value="<%=password%>"/></li>
                <li><strong>Email:</strong> <c:out value="<%=email%>"/></li>
                <li><strong>IP Address:</strong> <c:out value="<%=ipAddress%>"/></li>
                <li><strong>Games Played:</strong> <c:out value="<%=gamesPlayed%>"/></li>
            </ul>

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
                <input type="file" id="newPhoto" name="newPhoto" accept="image/*" required>
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

</body>
</html>