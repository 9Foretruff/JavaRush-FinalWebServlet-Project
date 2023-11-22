<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Registration Processing</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>

<body>
<c:set var="username" value="${param.username}" />
<c:set var="password" value="${param.password}" />
<c:set var="confirmPassword" value="${param.confirmPassword}" />
<c:set var="email" value="${param.email}" />

<c:choose>
    <c:when test="${not empty password and password eq confirmPassword}">

        <!-- Ваши дополнительные действия по обработке регистрации -->
        <!-- Здесь вы можете вставить код для сохранения данных в базе данных или выполнять другие действия -->
        <h1 class="neon-title">Registration Successful</h1>
        <div class="container">
            <div class="login-table">
                <p>Thank you for registering, ${username}!</p>
                <a href="/" class="button-link">Go to Home Page</a>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <h1 class="neon-title">Registration Failed</h1>
        <div class="container">
            <div class="login-table">
                <p>Passwords do not match. Please try again.</p>
<%--                <a href="registration.jsp" class="button-link">Go Back</a>--%>
                <a href="${pageContext.request.contextPath}/registration" class="button-link">Go Back</a>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>

</html>