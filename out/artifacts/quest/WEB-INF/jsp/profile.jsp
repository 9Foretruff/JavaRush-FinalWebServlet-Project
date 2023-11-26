<%@ page import="java.net.InetAddress" %>
<%@ page import="java.net.UnknownHostException" %>
<%@ page import="com.adventurequest.model.service.UserService" %>
<%@ page import="com.adventurequest.model.entity.UserEntity" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String username = (String) session.getAttribute("username");
    UserService userService = UserService.getInstance();
    UserEntity user = userService.getUserByUsername(username);
    String ipAddress = request.getRemoteAddr();

    String photoUrl = user.getPhotoUrl();
    String email = user.getEmail();
    int gamesPlayed = user.getGamesPlayed();
%>

<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <!-- Ваши стили и скрипты могут быть добавлены здесь -->
</head>
<body>

<h2>Welcome, <%= username %>!</h2>

<%-- Вывод фотографии пользователя --%>
<img src="<c:out value="${photoUrl}" />" alt="User Photo" width="100" height="100">

<%-- Информация о пользователе --%>
<ul>
    <li><strong>Email:</strong> <c:out value="${email}" /></li>
    <li><strong>IP Address:</strong> <c:out value="${ipAddress}" /></li>
    <li><strong>Games Played:</strong> <c:out value="${gamesPlayed}" /></li>
</ul>

<%-- Форма для изменения пароля --%>
<h3>Change Password</h3>
<form action="changePassword" method="post">
    <label for="newPassword">New Password:</label>
    <input type="password" id="newPassword" name="newPassword" required>
    <input type="submit" value="Change Password">
</form>

<%-- Форма для изменения email --%>
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

<!-- Другие формы и функциональность могут быть добавлены здесь -->

</body>
</html>

