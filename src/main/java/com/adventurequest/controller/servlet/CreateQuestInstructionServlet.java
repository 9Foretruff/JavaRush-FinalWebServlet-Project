package com.adventurequest.controller.servlet;

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

@WebServlet("/create-quest-instruction")
public class CreateQuestInstructionServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileServlet.class);
    private static final String CREATE_QUEST_INSTRUCTION_JSP = "creation-quest-instruction";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("User: {} get the profile page", UserSessionHelper.getUsername(req.getSession()));
        req.getRequestDispatcher(JspHelper.get(CREATE_QUEST_INSTRUCTION_JSP)).forward(req, resp);
    }

}