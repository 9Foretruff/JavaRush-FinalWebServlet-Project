package com.adventurequest.controller.servlet;

import com.adventurequest.controller.servlet.exception.LogoutException;
import com.adventurequest.model.entity.UserEntity;
import com.adventurequest.util.JspHelper;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher requestDispatcher;
            var remoteAddr = req.getRemoteAddr();
            var user = req.getSession().getAttribute("user");
            LOGGER.info("Logout request received from IP address: {}", remoteAddr);

            HttpSession session = req.getSession(false);
            if (session != null && user != null) {
                LOGGER.info("Logging out user from IP address: {}", remoteAddr);
                session.invalidate();
                LOGGER.info("User successfully logged out");
                requestDispatcher = req.getRequestDispatcher(JspHelper.get("logoutSuccess"));
            } else {
                LOGGER.warn("No active session found for logout request from IP address: {}", remoteAddr);
                LOGGER.warn("User failed logged out");
                requestDispatcher = req.getRequestDispatcher(JspHelper.get("logoutFailed"));
            }
            LOGGER.debug("Forwarding user with IP address {} to result of logout", remoteAddr);
            requestDispatcher.forward(req, resp);
        } catch (LogoutException exception) {
            LOGGER.error("Error during logout", exception);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}