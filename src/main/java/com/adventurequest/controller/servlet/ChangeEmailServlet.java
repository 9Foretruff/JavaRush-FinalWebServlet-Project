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


@WebServlet("/changeEmail")
public class ChangeEmailServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeEmailServlet.class);
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        var session = req.getSession();
        LOGGER.debug("User with IP address {} sent the data for changing email", remoteAddr);

        var user = (UserEntity) session.getAttribute("user");
        String newEmail = req.getParameter("newEmail");
        LOGGER.debug("Attempting to change email for user {} to new email {}", user.getUsername(), newEmail);

        var newUser = userService.changeEmail(user, newEmail);
        RequestDispatcher requestDispatcher;

        if (newUser.isPresent()) {
            LOGGER.info("Email changed successfully for user {}", newUser.get().getUsername());
            session.setAttribute("user", newUser.get());

            requestDispatcher = req.getRequestDispatcher(JspHelper.get("profile"));
            requestDispatcher.forward(req, resp);
        } else {
            LOGGER.warn("Failed to change email for user {}", user.getUsername());
            requestDispatcher = req.getRequestDispatcher(JspHelper.get("changing-email-failed"));
            requestDispatcher.forward(req, resp);
        }
    }
}