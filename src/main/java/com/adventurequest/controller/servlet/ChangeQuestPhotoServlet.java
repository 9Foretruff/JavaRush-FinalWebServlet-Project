package com.adventurequest.controller.servlet;

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

@WebServlet("/change-quest-photo")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20
)
public class ChangeQuestPhotoServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeQuestPhotoServlet.class);

    private static final String SUCCESS_URL = "/quest-info?questId=";
    private static final String FAILED_JSP = "changing-quest-photo-failed";
    private static final String ERROR_PAGE_JSP = "error-page";

    private static final int MAX_FILE_SIZE = 10 * 1024 * 1024;

    private final QuestService questService = QuestService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = UserSessionHelper.getUsername(req.getSession());

            LOGGER.info("Change quest photo data received from user: {}", username);

            Part newPhoto = req.getPart("newPhoto");
            String contentType = newPhoto.getContentType();

            if (!contentType.startsWith("image/")) {
                LOGGER.warn("User: {} send invalid file type {}", username, contentType);
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
                return;
            }

            if(newPhoto.getSize() > MAX_FILE_SIZE) {
                LOGGER.warn("File too big , image must be under 10 MB . file size that was provided:{}",newPhoto.getSize());
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
                return;
            }

            Long questId = Long.valueOf(req.getParameter("questId"));

            LOGGER.debug("Attempting to change quest photo for quest with id: {} ", questId);

            var resultOfChanging = questService.changeQuestPhoto(questId, newPhoto);

            if (resultOfChanging) {
                LOGGER.info("Photo changed successfully for quest with id: {}", questId);
                resp.sendRedirect(req.getContextPath() + SUCCESS_URL + questId);
            } else {
                LOGGER.warn("Failed to change photo for quest with id: {}", questId);
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
            }

        } catch (Exception exception) {
            LOGGER.error("Exception while changing user photo", exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }

}