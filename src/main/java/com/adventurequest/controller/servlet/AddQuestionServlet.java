package com.adventurequest.controller.servlet;

import com.adventurequest.model.service.QuestionService;
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

@WebServlet("/add-question")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20
)
public class AddQuestionServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddQuestionServlet.class);

    private static final String SUCCESS_JSP = "adding-question-success";
    private static final String FAILED_JSP = "adding-question-failed";
    private static final String ERROR_PAGE_JSP = "error-page";
    private static final String ADD_QUESTION_JSP = "add-question";

    private final QuestionService questionService = QuestionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("User {} accessed add-question page", UserSessionHelper.getUsername(req.getSession()));
        req.getRequestDispatcher(JspHelper.get(ADD_QUESTION_JSP)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = UserSessionHelper.getUsername(req.getSession());

            LOGGER.info("Create question data received from user: {}", username);

            Integer numberOfQuestion = Integer.valueOf(req.getParameter("numberOfQuestion"));
            Long questId = Long.valueOf(req.getParameter("questId"));
            String questionText = req.getParameter("questionText");
            Boolean isLastQuestion = Boolean.valueOf(req.getParameter("isLastQuestion"));
            Part photoPart = req.getPart("backgroundQuestionPhoto");
            byte[] backgroundQuestionPhoto = photoPart.getInputStream().readAllBytes();

            LOGGER.debug("Received question data - numberOfQuestion: {}, questId: {}, questionText: {}, isLastQuestion: {}, from user {}", numberOfQuestion, questId, questionText, isLastQuestion, username);

            var resultOfAdding = questionService.addQuestion(numberOfQuestion, questId, questionText, backgroundQuestionPhoto, isLastQuestion);

            if (resultOfAdding) {
                LOGGER.info("Quest successfully added by user: {}", username);
                req.getRequestDispatcher(JspHelper.get(SUCCESS_JSP)).forward(req, resp);
            } else {
                LOGGER.warn("Failed to add question by user: {}", username);
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
            }

        } catch (Exception exception) {
            LOGGER.error("Exception while adding question", exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }

}