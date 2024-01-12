package com.adventurequest.controller.servlet;

import com.adventurequest.controller.servlet.exception.LogoutException;
import com.adventurequest.model.entity.UserEntity;
import com.adventurequest.util.JspHelper;
import com.adventurequest.util.UserSessionHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);
    private static final String SUCCESS_JSP = "logout-success";
    private static final String FAILED_JSP = "logout-failed";
    private static final String ERROR_PAGE_JSP = "error-page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var remoteAddr = req.getRemoteAddr();
            var user = UserSessionHelper.getUser(req.getSession());
            LOGGER.info("Logout request received from IP address: {}", remoteAddr);

            HttpSession session = req.getSession(false);
            if (session != null && user != null) {
                var username = UserSessionHelper.getUsername(req.getSession());
                LOGGER.info("Logging out user: {}", username);

                session.invalidate();

                LOGGER.info("User: {} successfully logged out", username);
                req.getRequestDispatcher(JspHelper.get(SUCCESS_JSP)).forward(req, resp);
            } else {
                LOGGER.warn("Failed to log out , no active session found for logout request from IP address: {}", remoteAddr);
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
            }
            LOGGER.debug("Forwarding user with IP address {} to result of logout", remoteAddr);
        } catch (LogoutException exception) {
            LOGGER.error("Exception while logout", exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req,resp);
        }
    }

}