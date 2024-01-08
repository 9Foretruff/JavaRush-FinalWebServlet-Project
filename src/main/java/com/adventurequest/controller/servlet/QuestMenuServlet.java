package com.adventurequest.controller.servlet;

import com.adventurequest.model.service.QuestService;
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

@WebServlet(urlPatterns = "/menu")
public class QuestMenuServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestMenuServlet.class);
    private final QuestService questService = QuestService.getInstance();
    private static final String ERROR_PAGE_JSP = "error-page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var page = req.getParameter("page");
            var currentPage = page == null ? 1 : Long.parseLong(page);
            LOGGER.debug("User: {} get the menu page", UserSessionHelper.getUsername(req.getSession()));
            var publishedQuests = questService.findPublishedQuestsWithPage(currentPage);
            req.getSession().setAttribute("publishedQuests", publishedQuests);
            req.getSession().setAttribute("currentPage", currentPage);

            req.getRequestDispatcher(JspHelper.get("main-page")).forward(req, resp);
        } catch (Exception exception) {
            LOGGER.error("Exception while getting menu page", exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }

}