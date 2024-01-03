package com.adventurequest.controller.filter;

import com.adventurequest.model.entity.UserEntity;
import com.adventurequest.model.service.UserService;
import com.adventurequest.util.JspHelper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = {"/menu", "/profile","/changeEmail","/uploadPhoto","/changePassword","/createQuest","/addAnswer","/addQuestion","/addQuest","/questInstruction"})
public class AuthorizationFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);
    private final UserService userService = UserService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;

        var remoteAddr = req.getRemoteAddr();
        var session = req.getSession();
        var authenticatedUser = Optional.ofNullable((Boolean) session.getAttribute("authenticatedUser"));
        var user = Optional.ofNullable((UserEntity) session.getAttribute("user"));
        LOGGER.debug("AuthorizationFilter: Processing request for URL {}", req.getRequestURI());

        if (!authenticatedUser.orElse(false) || userService.login(user.orElse(null)).isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JspHelper.get("not-authorized"));
            LOGGER.warn("AuthorizationFilter: User with IP address {} not authenticated, redirected to not-authorized page", remoteAddr);
            dispatcher.forward(req, resp);
        } else {
            LOGGER.debug("AuthorizationFilter: User {} is authenticated. Continuing with the request.", user.get().getUsername());
            chain.doFilter(request, response);
        }

    }

}