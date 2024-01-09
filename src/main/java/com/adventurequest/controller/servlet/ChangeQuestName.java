package com.adventurequest.controller.servlet;

import com.adventurequest.model.service.QuestService;
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

@WebServlet("/change-quest-name")
public class ChangeQuestName extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeQuestName.class);

    private static final String SUCCESS_URL = "/profile";
    private static final String FAILED_JSP = "changing-email-failed";
    private static final String ERROR_PAGE_JSP = "error-page";

    private final QuestService questService = QuestService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = UserSessionHelper.getUsername(req.getSession());

            LOGGER.info("Change user email data received from user: {}", username);

            var user = UserSessionHelper.getUser(req.getSession());
            String newEmail = req.getParameter("newEmail");

            LOGGER.debug("Received change email data - newEmail: {}, from user: {}", newEmail, username);

            var newUser = userService.changeEmail(user, newEmail);

            if (newUser.isPresent()) {
                LOGGER.info("Email changed successfully for user: {}", username);
                req.getSession().setAttribute("user", newUser.get());
                resp.sendRedirect(req.getContextPath() + SUCCESS_URL);
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
