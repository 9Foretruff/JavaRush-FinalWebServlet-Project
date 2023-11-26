package com.adventurequest.controller.servlet;

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
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("User with IP address {} visited the registration page", req.getRemoteAddr());
        req.getRequestDispatcher(JspHelper.get("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        LOGGER.info("Registration data received from user with IP address {}", remoteAddr);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String email = req.getParameter("email");

        int registrationResult = userService.registerUser(username, password, confirmPassword, email);

        req.getSession().setAttribute("username", username);
        req.getSession().setAttribute("password", password);
        req.getSession().setAttribute("confirmPassword", confirmPassword);
        req.getSession().setAttribute("email", email);

        switch (registrationResult) {
            case UserService.PASSWORDS_DO_NOT_MATCH -> {
                LOGGER.warn("User with IP address {} attempted registration with different passwords", remoteAddr);
                req.getSession().setAttribute("registrationSuccessful", UserService.PASSWORDS_DO_NOT_MATCH);
            }
            case UserService.REGISTRATION_SUCCESSFUL -> {
                LOGGER.info("User with IP address {} has successfully registered", remoteAddr);
                req.getSession().setAttribute("registrationSuccessful", UserService.REGISTRATION_SUCCESSFUL);
            }
            case UserService.REGISTRATION_FAILED -> {
                LOGGER.warn("User with IP address {} did not register due to incorrect data", remoteAddr);
                req.getSession().setAttribute("registrationSuccessful", UserService.REGISTRATION_FAILED);
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(JspHelper.get("registration-result"));
        LOGGER.debug("Forwarding user with IP address {} to registration-result.jsp", remoteAddr);
        dispatcher.forward(req, resp);
    }
}