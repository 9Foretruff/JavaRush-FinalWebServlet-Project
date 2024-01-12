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

@WebServlet("/change-quest-name")
public class ChangeQuestNameServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeQuestNameServlet.class);

    private static final String SUCCESS_URL = "/quest-info?questId=";
    private static final String MENU_URL = "/menu";
    private static final String FAILED_JSP = "changing-quest-name-failed";
    private static final String ERROR_PAGE_JSP = "error-page";

    private final QuestService questService = QuestService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = UserSessionHelper.getUsername(req.getSession());

            LOGGER.info("Change quest name data received from user: {}", username);

            Long questId = Long.valueOf(req.getParameter("questId"));
            var newName = req.getParameter("newName");

            LOGGER.debug("Received change quest name data - newName: {}, from user: {}", newName, username);

            var resultOfChanging = questService.changeQuestName(questId, newName, username);

            if (resultOfChanging) {
                LOGGER.info("Name changed successfully for quest with id: {}", questId);
                resp.sendRedirect(req.getContextPath() + SUCCESS_URL + questId);
            } else {
                LOGGER.warn("Failed to change name for quest with id: {}", questId);
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
            }
        } catch (Exception exception) {
            LOGGER.error("Exception while changing quest name", exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }
}