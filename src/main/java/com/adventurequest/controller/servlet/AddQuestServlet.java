package com.adventurequest.controller.servlet;

import com.adventurequest.model.entity.DifficultyEnum;
import com.adventurequest.model.entity.UserEntity;
import com.adventurequest.model.service.QuestService;
import com.adventurequest.util.JspHelper;
import jakarta.servlet.RequestDispatcher;
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
    private final QuestService questService = QuestService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        LOGGER.debug("User with IP address {} accessed the add-quest page", remoteAddr);
        var requestDispatcher = req.getRequestDispatcher(JspHelper.get("add-quest"));
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        var session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        LOGGER.info("Create quest data received from {} with IP address {}", user.getUsername(), remoteAddr);

        String questName = req.getParameter("questName");
        String questDescription = req.getParameter("questDescription");

        Part photoPart = req.getPart("questPhoto");
        byte[] questPhoto = photoPart.getInputStream().readAllBytes();

        DifficultyEnum questDifficulty = DifficultyEnum.valueOf(req.getParameter("questDifficulty").toUpperCase());

        LOGGER.debug("Received quest data - questName: {}, questDescription: {}, questDifficulty: {}", questName, questDescription, questDifficulty);

        var resultOfAdding = questService.addQuest(questName, questDescription, questPhoto, questDifficulty, user.getUsername());

        RequestDispatcher requestDispatcher;
        if (resultOfAdding) {
            LOGGER.info("Quest successfully added by user with IP address {}", remoteAddr);
            requestDispatcher = req.getRequestDispatcher(JspHelper.get("adding-quest-success"));
        } else {
            LOGGER.warn("Failed to add quest by user with IP address {}", remoteAddr);
            requestDispatcher = req.getRequestDispatcher(JspHelper.get("adding-quest-failed"));
        }
        requestDispatcher.forward(req, resp);
    }

}