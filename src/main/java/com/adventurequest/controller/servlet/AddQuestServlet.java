package com.adventurequest.controller.servlet;

import com.adventurequest.model.entity.DifficultyEnum;
import com.adventurequest.model.service.QuestService;
import com.adventurequest.util.JspHelper;
import com.adventurequest.util.UserSessionHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/addQuest")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20
)
public class AddQuestServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddQuestServlet.class);

    private static final String SUCCESS_JSP = "adding-quest-success";
    private static final String FAILED_JSP = "adding-quest-failed";
    private static final String ERROR_PAGE_JSP = "error-page";
    private static final String ADD_QUEST_JSP = "add-quest";

    private final QuestService questService = QuestService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("User {} accessed add-quest page", UserSessionHelper.getUsername(req.getSession()));
        req.getRequestDispatcher(JspHelper.get(ADD_QUEST_JSP)).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = UserSessionHelper.getUsername(req.getSession());

            LOGGER.info("Create quest data received from user: {}", username);

            String questName = req.getParameter("questName");
            String questDescription = req.getParameter("questDescription");
            Part photoPart = req.getPart("questPhoto");
            byte[] questPhoto = photoPart.getInputStream().readAllBytes();
            DifficultyEnum questDifficulty = DifficultyEnum.valueOf(req.getParameter("questDifficulty").toUpperCase());

            LOGGER.debug("Received quest data - questName: {}, questDescription: {}, questDifficulty: {}, from user: {}", questName, questDescription, questDifficulty, username);

            var resultOfAdding = questService.addQuest(questName, questDescription, questPhoto, questDifficulty, username);

            if (resultOfAdding) {
                LOGGER.info("Quest successfully added by user: {}", username);
                req.getRequestDispatcher(JspHelper.get(SUCCESS_JSP)).forward(req, resp);
            } else {
                LOGGER.warn("Failed to add quest by user: {}", username);
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
            }

        } catch (Exception exception) {
            LOGGER.error("Exception while adding quest", exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }

}