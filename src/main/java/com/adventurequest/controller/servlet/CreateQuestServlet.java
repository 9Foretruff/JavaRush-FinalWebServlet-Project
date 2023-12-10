package com.adventurequest.controller.servlet;

import com.adventurequest.model.service.UserService;
import com.adventurequest.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/createQuest")
public class CreateQuestServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateQuestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        var session = req.getSession();
        LOGGER.debug("User with IP address {} get add-quest page", remoteAddr);

        var requestDispatcher = req.getRequestDispatcher(JspHelper.get("creation-quest-menu"));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
