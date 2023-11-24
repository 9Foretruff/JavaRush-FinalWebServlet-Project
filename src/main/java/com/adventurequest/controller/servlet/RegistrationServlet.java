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

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServlet.class);
    private static final int SAME_PASSWORD = -1;
    private static final int REGISTRATION_SUCCESS = 1;
    private static final int REGISTRATION_FAILED = 0;
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("User get registration page");
        req.getRequestDispatcher(JspHelper.get("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("User sent the data for registration");
        RequestDispatcher dispatcher = req.getRequestDispatcher(JspHelper.get("registration-result"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String email = req.getParameter("email");
        if (!password.equals(confirmPassword)) {
            LOGGER.warn("User sent different passwords!");
            req.setAttribute("registrationSuccessful", SAME_PASSWORD);
            dispatcher.forward(req, resp);
            return;
        }
        UserEntity userEntity = new UserEntity(username, password, email);
        var result = userService.save(userEntity);
        req.setAttribute("username", username);
        if (result) {
            LOGGER.info("The user has successfully registered");
            req.setAttribute("registrationSuccessful", REGISTRATION_SUCCESS);
        } else {
            LOGGER.warn("The user did not register due to incorrect data");
            req.setAttribute("registrationSuccessful", REGISTRATION_FAILED);
        }
        LOGGER.debug("Forwarding to registration-result.jsp");
        dispatcher.forward(req, resp);
    }

}
