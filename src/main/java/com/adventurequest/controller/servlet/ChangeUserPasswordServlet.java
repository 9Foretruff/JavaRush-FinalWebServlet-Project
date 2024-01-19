package com.adventurequest.controller.servlet;

import com.adventurequest.model.service.UserService;
import com.adventurequest.util.JspHelper;
import com.adventurequest.util.UserSessionHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/change-user-password")
public class ChangeUserPasswordServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeUserPasswordServlet.class);

    private static final String SUCCESS_URL = "/profile";
    private static final String FAILED_JSP = "changing-user-password-failed";
    private static final String ERROR_PAGE_JSP = "error-page";

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = UserSessionHelper.getUsername(req.getSession());

            LOGGER.info("Change password data received from user {}", username);

            var user = UserSessionHelper.getUser(req.getSession());
            String newPassword = req.getParameter("newPassword");

            if (!isPasswordLengthValid(newPassword)) {
                LOGGER.warn("Failed to change password for user {} - password length exceeds 255 letters", username);
                req.getRequestDispatcher(JspHelper.get("changing-password-failed")).forward(req, resp);
                return;
            }

            var newUser = userService.changePassword(user, newPassword);

            if (newUser.isPresent()) {
                LOGGER.info("Password changed successfully for user: {}", username);
                req.getSession().setAttribute("user", newUser.get());
                resp.sendRedirect(req.getContextPath() + SUCCESS_URL);
            } else {
                LOGGER.warn("Failed to change password for user: {}", username);
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
            }
        } catch (Exception exception) {
            LOGGER.error("Exception while changing user password", exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }

    private boolean isPasswordLengthValid(String password) {
        return password.length() <= 255;
    }

}