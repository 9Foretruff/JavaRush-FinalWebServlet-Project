package com.adventurequest.controller.servlet;

import com.adventurequest.model.service.AnswerService;
import com.adventurequest.model.service.QuestService;
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

@WebServlet("/create-quest")
public class CreateQuestServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateQuestServlet.class);
    private final QuestService questService = QuestService.getInstance();
    private final QuestionService questionService = QuestionService.getInstance();
    private final AnswerService answerService = AnswerService.getInstance();
    private static final String CREATE_QUEST_JSP = "creation-quest-menu";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var username = UserSessionHelper.getUsername(req.getSession());
        LOGGER.debug("User: {} get creation-quest page", username);
        req.getSession().setAttribute("myQuests", questService.findQuestsByAuthor(username));
        req.getSession().setAttribute("myQuestions", questionService.findQuestionsByAuthor(username));
        req.getSession().setAttribute("myAnswers", answerService.findAnswersByAuthor(username));
        req.getRequestDispatcher(JspHelper.get(CREATE_QUEST_JSP)).forward(req, resp);
    }

}