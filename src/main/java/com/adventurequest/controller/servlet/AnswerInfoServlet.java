package com.adventurequest.controller.servlet;

import com.adventurequest.model.service.AnswerService;
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

@WebServlet("/answer-info")
public class AnswerInfoServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnswerInfoServlet.class);
    private final AnswerService answerService = AnswerService.getInstance();
    private static final String ANSWER_INFO_PAGE_JSP = "answer-info";
    private static final String ANSWER_NOT_FOUND_JSP = "answer-not-found";
    private static final String ERROR_PAGE_JSP = "error-page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var username = UserSessionHelper.getUsername(req.getSession());
        try {
            LOGGER.debug("User: {} get the answer info page", username);

            var answerId = req.getParameter("answerId");
            var answer = answerService.findAnswerById(Long.valueOf(answerId));

            if (answer.isEmpty()) {
                LOGGER.warn("Answer with ID {} not found", answerId);
                req.getRequestDispatcher(JspHelper.get(ANSWER_NOT_FOUND_JSP)).forward(req, resp);
                return;
            }

            LOGGER.info("Found answer with ID {}, setting it as session attribute", answerId);
            req.getSession().setAttribute("answerInfo", answer.get());

            LOGGER.info("Forwarding user {} to answer info page", username);
            req.getRequestDispatcher(JspHelper.get(ANSWER_INFO_PAGE_JSP)).forward(req, resp);

        } catch (Exception exception) {
            LOGGER.error("Error while getting answer info for user {}", username, exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }

}