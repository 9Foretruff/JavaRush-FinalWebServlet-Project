package com.adventurequest.controller.servlet;

import com.adventurequest.model.service.QuestService;
import com.adventurequest.util.JspHelper;
import com.adventurequest.util.UserSessionHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(urlPatterns = "/menu")
public class QuestMenuServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestMenuServlet.class);
    private final QuestService  questService = QuestService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("User: {} get the menu page", UserSessionHelper.getUsername(req.getSession()));
        var allQuests = questService.findPublishedQuests();
        req.getSession().setAttribute("quests",allQuests);

        req.getRequestDispatcher(JspHelper.get("main-page")).forward(req, resp);
    }
}
