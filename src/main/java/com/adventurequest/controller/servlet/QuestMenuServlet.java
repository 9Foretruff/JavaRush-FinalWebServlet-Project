package com.adventurequest.controller.servlet;

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
import java.util.Optional;

@WebServlet(urlPatterns = "/menu")
public class QuestMenuServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        var session = req.getSession();
        Optional<Boolean> authenticatedUser = Optional.ofNullable((Boolean) session.getAttribute("authenticatedUser"));
        if (!authenticatedUser.orElse(false)) {
            requestDispatcher = req.getRequestDispatcher(JspHelper.get("not-authorized"));
            requestDispatcher.forward(req, resp);
        }
        var user = session.getAttribute("user");


    }
}
