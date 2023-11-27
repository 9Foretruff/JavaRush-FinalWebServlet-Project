package com.adventurequest.controller.filter;

import com.adventurequest.controller.servlet.LoginServlet;
import com.adventurequest.model.entity.UserEntity;
import com.adventurequest.model.service.UserService;
import com.adventurequest.util.JspHelper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = {"/menu", "/profile"})
public class AuthorizationFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);
    private final UserService userService = UserService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;

        var remoteAddr = req.getRemoteAddr();
        HttpSession session = req.getSession();
        var authenticatedUser = Optional.ofNullable((Boolean) session.getAttribute("authenticatedUser"));
        var user = Optional.ofNullable((UserEntity) session.getAttribute("user"));

        if (!authenticatedUser.orElse(false) || userService.login(user.orElse(null)).isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JspHelper.get("not-authorized"));
            LOGGER.warn("User with IP address {} not authenticated, redirected to not-authorized page", remoteAddr);
            dispatcher.forward(req, resp);
        } else {
            chain.doFilter(request, response);
        }
    }

}

