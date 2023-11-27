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
import java.util.Optional;

@WebServlet(urlPatterns = "/menu")
public class QuestMenuServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestMenuServlet.class);
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        LOGGER.debug("User with IP address {} get the menu page", remoteAddr);

        RequestDispatcher requestDispatcher;
        requestDispatcher = req.getRequestDispatcher(JspHelper.get("main-page"));
        requestDispatcher.forward(req, resp);
    }
}
