package com.adventurequest.controller.servlet;

import com.adventurequest.model.entity.UserEntity;
import com.adventurequest.model.service.UserService;
import com.adventurequest.util.JspHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangePasswordServlet.class);
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        var session = req.getSession();
        LOGGER.debug("User with IP address {} sent the data for changing password", remoteAddr);

        var user = (UserEntity) session.getAttribute("user");
        String newPassword = req.getParameter("newPassword");
        LOGGER.debug("Attempting to change password for user {} to new password ", user.getUsername());
        RequestDispatcher requestDispatcher;

        if (newPassword.length() > 255) {
            LOGGER.warn("Failed to change password for user {}", user.getUsername());

            requestDispatcher = req.getRequestDispatcher(JspHelper.get("changing-password-failed"));
            requestDispatcher.forward(req, resp);
            return;
        }

        var newUser = userService.changePassword(user, newPassword);

        if (newUser.isPresent()) {
            LOGGER.info("Password changed successfully for user {}", newUser.get().getUsername());
            session.setAttribute("user", newUser.get());

            requestDispatcher = req.getRequestDispatcher(JspHelper.get("profile"));
            requestDispatcher.forward(req, resp);
        } else {
            LOGGER.warn("Failed to change password for user {}", user.getUsername());

            requestDispatcher = req.getRequestDispatcher(JspHelper.get("password-change-failed"));
            requestDispatcher.forward(req, resp);
        }
    }
}
