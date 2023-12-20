package com.adventurequest.controller.servlet;

import com.adventurequest.model.entity.UserEntity;
import com.adventurequest.model.service.AnswerService;
import com.adventurequest.util.JspHelper;
import jakarta.servlet.RequestDispatcher;
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
    private final AnswerService answerService = AnswerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        LOGGER.debug("User with IP address {} accessed the add-answer page", remoteAddr);
        var requestDispatcher = req.getRequestDispatcher(JspHelper.get("add-answer"));
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        var session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        LOGGER.info("Create answer data received from user with IP address {}", remoteAddr);

        Integer questionId = Integer.valueOf(req.getParameter("questionId"));
        String answerText = req.getParameter("answerText");
        Boolean isAnswerCorrect = Boolean.valueOf(req.getParameter("isAnswerCorrect"));

        LOGGER.debug("Received answer data - questionId: {}, answerText: {}, isAnswerCorrect: {}", questionId, answerText, isAnswerCorrect);

        var resultOfAdding = answerService.addAnswer(questionId, answerText, isAnswerCorrect);

        RequestDispatcher requestDispatcher;
        if (resultOfAdding) {
            LOGGER.info("Answer successfully added by user with IP address {}", remoteAddr);
            requestDispatcher = req.getRequestDispatcher(JspHelper.get("adding-answer-success"));
        } else {
            LOGGER.warn("Failed to add answer by user with IP address {}", remoteAddr);
            requestDispatcher = req.getRequestDispatcher(JspHelper.get("adding-answer-failed"));
        }
        requestDispatcher.forward(req, resp);

    }
}