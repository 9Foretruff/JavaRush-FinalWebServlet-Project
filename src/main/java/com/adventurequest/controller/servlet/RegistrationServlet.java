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

import java.io.IOException;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final int SAME_PASSWORD = -1;
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.get("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(JspHelper.get("registration-result"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String email = req.getParameter("email");
        if (!password.equals(confirmPassword)) {
            req.setAttribute("registrationSuccessful", SAME_PASSWORD);
            dispatcher.forward(req, resp);
            return;
        }
        UserEntity userEntity = new UserEntity(username, password, email);
        var result = userService.save(userEntity);
        req.setAttribute("username", username);
        if (result) {
            req.setAttribute("registrationSuccessful", 1);
        } else {
            req.setAttribute("registrationSuccessful", 0);
        }
        dispatcher.forward(req, resp);
    }

}
