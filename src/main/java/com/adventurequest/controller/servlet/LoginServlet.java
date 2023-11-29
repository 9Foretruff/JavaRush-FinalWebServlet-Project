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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    private final UserService userService = UserService.getInstance();
    private final String LOGIN_SUCCESS_JSP = JspHelper.get("login-success");
    private final String LOGIN_FAILED_JSP = JspHelper.get("login-failed");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        LOGGER.debug("User with IP address {} get login page", remoteAddr);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        LOGGER.info("User with IP address {} sent the data for login", remoteAddr);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        UserEntity user = new UserEntity(username, password, email, null, null);

        var login = userService.login(user);
        
        RequestDispatcher dispatcher;
        var session = req.getSession();
        if (login.isPresent()) {
            LOGGER.info("User with IP address {} sent valid data to login!", remoteAddr);
            session.setAttribute("authenticatedUser", true);
            session.setAttribute("user", login.get());

            dispatcher = req.getRequestDispatcher(LOGIN_SUCCESS_JSP);
        } else {
            LOGGER.warn("User with IP address {} sent invalid data to login!", remoteAddr);
            session.setAttribute("authenticatedUser", false);
            dispatcher = req.getRequestDispatcher(LOGIN_FAILED_JSP);
        }
        LOGGER.debug("Forwarding user with IP address {} to registration-result.jsp", remoteAddr);
        dispatcher.forward(req, resp);
    }
}