package com.adventurequest.controller.servlet;

import com.adventurequest.model.service.UserService;
import com.adventurequest.util.JspHelper;
import com.adventurequest.util.UserSessionHelper;
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

@WebServlet("/change-user-photo")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20
)
public class ChangeUserPhotoServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeUserPhotoServlet.class);

    private static final String SUCCESS_URL = "/profile";
    private static final String FAILED_JSP = "changing-photo-failed";
    private static final String ERROR_PAGE_JSP = "error-page";
    private static final int MAX_FILE_SIZE = 10 * 1024 * 1024;

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = UserSessionHelper.getUsername(req.getSession());

            LOGGER.info("Change photo data received from user: {}", username);

            var user = UserSessionHelper.getUser(req.getSession());
            Part newPhoto = req.getPart("newPhoto");
            String contentType = newPhoto.getContentType();

            if (!contentType.startsWith("image/")) {
                LOGGER.warn("User: {} send invalid file type {}", username, contentType);
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
                return;
            }

            if(newPhoto.getSize() > MAX_FILE_SIZE) {
                LOGGER.warn("File too big , image must be under 10 MB . file size that was provided:{}",newPhoto.getSize());
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
                return;
            }

            LOGGER.debug("Attempting to change photo for user {} ", username);
            
            var newUser = userService.changePhoto(user, newPhoto.getInputStream());

            if (newUser.isPresent()) {
                LOGGER.info("Photo changed successfully for user: {}", username);
                req.getSession().setAttribute("user", newUser.get());
                resp.sendRedirect(req.getContextPath() + SUCCESS_URL);
            } else {
                LOGGER.warn("Failed to change photo for user: {}", username);
                req.getRequestDispatcher(JspHelper.get(FAILED_JSP)).forward(req, resp);
            }

        } catch (Exception exception) {
            LOGGER.error("Exception while changing user photo", exception);
            req.getRequestDispatcher(JspHelper.get(ERROR_PAGE_JSP)).forward(req, resp);
        }
    }

}