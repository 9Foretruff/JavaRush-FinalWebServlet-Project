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

@WebServlet("/start-quest")
public class StartQuestServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartQuestServlet.class);
    private final QuestService questService = QuestService.getInstance();
    private static final String PREVIEW_QUEST_JSP = "preview-quest";
    private static final String QUEST_NOT_FOUND_JSP = "quest-not-found";
    private static final String ERROR_PAGE_JSP = "error-page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var username = UserSessionHelper.getUsername(req.getSession());
        var questId = req.getParameter("questId");
        try {
            LOGGER.debug("User: {} get the preview quest page with id: {}", username, questId);

            var quest = questService.findQuestById(Long.valueOf(questId));

            if (quest.isEmpty()) {
                LOGGER.warn("Quest with ID {} not found", questId);
                req.getRequestDispatcher(JspHelper.get(QUEST_NOT_FOUND_JSP)).forward(req, resp);
                return;
            }

            LOGGER.info("Found quest with ID {}, setting it as session attribute", questId);
            req.getSession().setAttribute("previewQuest", quest.get());

            LOGGER.info("Forwarding user {} to preview quest page", username);
            req.getRequestDispatcher(JspHelper.get(PREVIEW_QUEST_JSP)).forward(req, resp);

        } catch (Exception exception) {
            LOGGER.error("Error while getting quest preview page by user: {}", username, exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }
}