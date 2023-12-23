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


@WebServlet("/changeEmail")
public class ChangeEmailServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeEmailServlet.class);

    private static final String SUCCESS_JSP = "profile";
    private static final String FAILED_JSP = "changing-email-failed";
    private static final String ERROR_PAGE_JSP = "error-page";

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = UserSessionHelper.getUsername(req.getSession());

            LOGGER.info("Change email data received from user: {}", username);

            var user = UserSessionHelper.getUser(req.getSession());
            String newEmail = req.getParameter("newEmail");

            LOGGER.debug("Received change email data - newEmail: {}, from user: {}", newEmail, username);

            var newUser = userService.changeEmail(user, newEmail);

            if (newUser.isPresent()) {
                LOGGER.info("Email changed successfully for user: {}", username);
                req.getSession().setAttribute("user", newUser.get());
                req.getRequestDispatcher(JspHelper.get(SUCCESS_JSP)).forward(req, resp);
            } else {
                LOGGER.warn("Failed to change email for user: {}", username);
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
            }
        } catch (Exception exception) {
            LOGGER.error("Exception while changing user email", exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }
}