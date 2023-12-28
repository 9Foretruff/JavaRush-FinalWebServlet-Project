package com.adventurequest.controller.servlet;

import com.adventurequest.model.service.AnswerService;
import com.adventurequest.util.JspHelper;
import com.adventurequest.util.UserSessionHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/addAnswer")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20
)
public class AddAnswerServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddAnswerServlet.class);

    private static final String SUCCESS_JSP = "adding-answer-success";
    private static final String FAILED_JSP = "adding-answer-failed";
    private static final String ERROR_PAGE_JSP = "error-page";
    private static final String ADD_ANSWER_JSP = "add-answer";

    private final AnswerService answerService = AnswerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("User {} accessed add-answer page", UserSessionHelper.getUsername(req.getSession()));
        req.getRequestDispatcher(JspHelper.get(ADD_ANSWER_JSP)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = UserSessionHelper.getUsername(req.getSession());

            LOGGER.info("Create answer data received from user: {}", username);

            Integer questionId = Integer.valueOf(req.getParameter("questionId"));
            String answerText = req.getParameter("answerText");
            Boolean isAnswerCorrect = Boolean.valueOf(req.getParameter("isAnswerCorrect"));

            LOGGER.debug("Received answer data - questionId: {}, answerText: {}, isAnswerCorrect: {}, from user: {}", questionId, answerText, isAnswerCorrect, username);

            var resultOfAdding = answerService.addAnswer(questionId, answerText, isAnswerCorrect);

            if (resultOfAdding) {
                LOGGER.info("Answer successfully added by user: {}", username);
                req.getRequestDispatcher(JspHelper.get(SUCCESS_JSP)).forward(req, resp);
            } else {
                LOGGER.warn("Failed to add answer by user: {}", username);
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
            }

        } catch (Exception exception) {
            LOGGER.error("Exception while adding answer", exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }

}