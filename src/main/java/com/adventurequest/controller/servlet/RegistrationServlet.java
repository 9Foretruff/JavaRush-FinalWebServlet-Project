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
    private static final String REGISTRATION_PAGE_JSP = "registration";
    private static final String REGISTRATION_RESULT_JSP = "registration-result";
    private static final String ERROR_PAGE_JSP = "error-page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("User with IP address {} visited the registration page", req.getRemoteAddr());
        req.getRequestDispatcher(JspHelper.get(REGISTRATION_PAGE_JSP)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var remoteAddr = req.getRemoteAddr();
            var session = req.getSession();
            LOGGER.info("Registration data received from user with IP address {}", remoteAddr);

            String username = req.getParameter("username").replace(" ", "");
            String password = req.getParameter("password").replace(" ", "");
            String confirmPassword = req.getParameter("confirmPassword");
            String email = req.getParameter("email");

            int registrationResult = userService.registerUser(username, password, confirmPassword, email);

            session.setAttribute("username", username);
            session.setAttribute("email", email);

            switch (registrationResult) {
                case UserService.PASSWORDS_DO_NOT_MATCH -> {
                    LOGGER.warn("User with IP address {} attempted registration with different passwords", remoteAddr);
                    req.getSession().setAttribute("registrationResult", UserService.PASSWORDS_DO_NOT_MATCH);
                }
                case UserService.REGISTRATION_SUCCESSFUL -> {
                    LOGGER.info("User with IP address {} has successfully registered", remoteAddr);
                    req.getSession().setAttribute("registrationResult", UserService.REGISTRATION_SUCCESSFUL);
                }
                case UserService.REGISTRATION_FAILED -> {
                    LOGGER.warn("User with IP address {} did not register due to incorrect data", remoteAddr);
                    req.getSession().setAttribute("registrationResult", UserService.REGISTRATION_FAILED);
                }
            }

            LOGGER.debug("Forwarding user with IP address {} to registration-result.jsp", remoteAddr);
            req.getRequestDispatcher(JspHelper.get(REGISTRATION_RESULT_JSP)).forward(req, resp);
        } catch (Exception exception) {
            LOGGER.error("Exception while registration", exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }

}