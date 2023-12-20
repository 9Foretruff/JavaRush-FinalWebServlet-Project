package com.adventurequest.controller.servlet;

import com.adventurequest.model.entity.UserEntity;
import com.adventurequest.model.service.QuestionService;
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

@WebServlet("/addQuestion")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20
)
public class AddQuestionServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddQuestionServlet.class);
    private final QuestionService questionService = QuestionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        LOGGER.debug("User with IP address {} accessed the add-question page", remoteAddr);
        var requestDispatcher = req.getRequestDispatcher(JspHelper.get("add-question"));
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        var session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        LOGGER.info("Create question data received from {} with IP address {}", user.getUsername(), remoteAddr);

        Integer numberOfQuestion = Integer.valueOf(req.getParameter("numberOfQuestion"));
        Long questId = Long.valueOf(req.getParameter("questId"));
        String questionText = req.getParameter("questionText");
        Boolean isLastQuestion = Boolean.valueOf(req.getParameter("isLastQuestion"));

        Part photoPart = req.getPart("backgroundQuestionPhoto");
        byte[] backgroundQuestionPhoto = photoPart.getInputStream().readAllBytes();

        LOGGER.debug("Received question data - numberOfQuestion: {}, questId: {}, questionText: {}, isLastQuestion: {}", numberOfQuestion, questId, questionText, isLastQuestion);

        var resultOfAdding = questionService.addQuestion(numberOfQuestion, questId, questionText, backgroundQuestionPhoto, isLastQuestion);

        RequestDispatcher requestDispatcher;
        if (resultOfAdding) {
            LOGGER.info("Quest successfully added by user with IP address {}", remoteAddr);
            requestDispatcher = req.getRequestDispatcher(JspHelper.get("adding-question-success"));
        } else {
            LOGGER.warn("Failed to add quest by user with IP address {}", remoteAddr);
            requestDispatcher = req.getRequestDispatcher(JspHelper.get("adding-question-failed"));
        }
        requestDispatcher.forward(req, resp);
    }
}
