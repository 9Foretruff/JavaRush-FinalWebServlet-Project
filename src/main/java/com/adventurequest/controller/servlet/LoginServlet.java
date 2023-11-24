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

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        UserEntity user = new UserEntity(username,password,email);
        var login = userService.login(user);
        RequestDispatcher dispatcher;
        if (login){
            req.setAttribute("authenticatedUser", true);
            req.setAttribute("user", user);
            dispatcher = req.getRequestDispatcher(JspHelper.get("login"));
            dispatcher.forward(req,resp);
        }else {
            req.setAttribute("authenticatedUser", false);
            dispatcher = req.getRequestDispatcher(JspHelper.get("login-failed"));
            dispatcher.forward(req,resp);
        }
    }
}