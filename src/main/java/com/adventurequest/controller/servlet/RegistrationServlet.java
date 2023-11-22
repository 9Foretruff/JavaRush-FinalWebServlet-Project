package com.adventurequest.controller.servlet;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.get("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String email = req.getParameter("email");

        // Ваша логика обработки регистрации
       // boolean registrationSuccessful = /* Реализуйте вашу логику */;

        // Установить атрибуты для передачи данных в JSP
        req.setAttribute("username", username);
        req.setAttribute("registrationSuccessful", true);

        // Перенаправить пользователя на страницу с результатом
        RequestDispatcher dispatcher = req.getRequestDispatcher("/registration-result.jsp");
        dispatcher.forward(req, resp);
    }

}
