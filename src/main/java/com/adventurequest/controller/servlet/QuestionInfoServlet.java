package com.adventurequest.controller.servlet;

import com.adventurequest.model.service.QuestionService;
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

@WebServlet("/question-info")
public class QuestionInfoServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionInfoServlet.class);
    private final QuestionService questionService = QuestionService.getInstance();
    private static final String QUESTION_INFO_PAGE_JSP = "question-info";
    private static final String QUESTION_NOT_FOUND_JSP = "question-not-found";
    private static final String ERROR_PAGE_JSP = "error-page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var username = UserSessionHelper.getUsername(req.getSession());
        try {
            LOGGER.debug("User: {} get the question info page", username);

            var questionId = req.getParameter("questionId");
            var question = questionService.findQuestionById(Long.valueOf(questionId));

            if (question.isEmpty()) {
                LOGGER.warn("Question with ID {} not found", questionId);
                req.getRequestDispatcher(JspHelper.get(QUESTION_NOT_FOUND_JSP)).forward(req, resp);
                return;
            }

            LOGGER.info("Found question with ID {}, setting it as session attribute", questionId);
            req.getSession().setAttribute("question", question.get());

            LOGGER.info("Forwarding user {} to question info page", username);
            req.getRequestDispatcher(JspHelper.get(QUESTION_INFO_PAGE_JSP)).forward(req, resp);

        } catch (Exception exception) {
            LOGGER.error("Error while getting question info for user {}", username, exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }

}