package com.adventurequest.controller.servlet;

import com.adventurequest.model.dto.UserDto;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("User get login page");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("User sent the data for login");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        UserEntity user = new UserEntity(username, password, email);
        var login = userService.login(user);
        RequestDispatcher dispatcher;
        if (login) {
            LOGGER.info("User sent valid data to login!");
            var session = req.getSession();
            session.setAttribute("authenticatedUser", true);
            session.setAttribute("user", user);
            dispatcher = req.getRequestDispatcher(JspHelper.get("login-success"));
        } else {
            LOGGER.warn("User sent invalid data to login!");
            req.setAttribute("authenticatedUser", false);
            dispatcher = req.getRequestDispatcher(JspHelper.get("login-failed"));
        }
        LOGGER.debug("Forwarding to registration-result.jsp");
        dispatcher.forward(req, resp);
    }
}