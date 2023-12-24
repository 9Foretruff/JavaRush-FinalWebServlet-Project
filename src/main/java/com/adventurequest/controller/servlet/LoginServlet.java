package com.adventurequest.controller.servlet;

import com.adventurequest.model.entity.UserEntity;
import com.adventurequest.model.service.UserService;
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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    private final UserService userService = UserService.getInstance();
    private final String LOGIN_SUCCESS_JSP = JspHelper.get("login-success");
    private final String LOGIN_FAILED_JSP = JspHelper.get("login-failed");
    private static final String ERROR_PAGE_JSP = "error-page";
    private static final String LOGIN_PAGE_JSP = "/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("User: {} get login page", UserSessionHelper.getUsername(req.getSession()));
        req.getRequestDispatcher(LOGIN_PAGE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var remoteAddr = req.getRemoteAddr();
            LOGGER.info("User with IP address {} sent the data for login", remoteAddr);

            UserEntity user = getUserFromRequest(req);

            var login = userService.login(user);

            var session = req.getSession();

            if (login.isPresent()) {
                LOGGER.info("User with IP address {} sent valid data to login!", remoteAddr);

                session.setAttribute("authenticatedUser", true);
                session.setAttribute("user", login.get());

                forwardToPage(req, resp, LOGIN_SUCCESS_JSP);
            } else {
                LOGGER.warn("User with IP address {} sent invalid data to login!", remoteAddr);

                session.setAttribute("authenticatedUser", false);

                forwardToPage(req, resp, LOGIN_FAILED_JSP);
            }
        }catch (Exception exception){
            LOGGER.error("Exception while login", exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }

    private UserEntity getUserFromRequest(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        return new UserEntity(null, username, password, email, null, null);
    }

    private void forwardToPage(HttpServletRequest req, HttpServletResponse resp, String page) throws ServletException, IOException {
        LOGGER.debug("Forwarding user with IP address {} to registration-result.jsp", req.getRemoteAddr());
        req.getRequestDispatcher(page).forward(req, resp);
    }
}