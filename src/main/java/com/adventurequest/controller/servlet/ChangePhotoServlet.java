package com.adventurequest.controller.servlet;

import com.adventurequest.model.entity.UserEntity;
import com.adventurequest.model.service.UserService;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/uploadPhoto")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20
)
public class ChangePhotoServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangePhotoServlet.class);
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var remoteAddr = req.getRemoteAddr();
        var session = req.getSession();
        LOGGER.debug("User with IP address {} sent the data for changing photo", remoteAddr);

        RequestDispatcher requestDispatcher;
        var user = (UserEntity) session.getAttribute("user");
        Part newPhoto = req.getPart("newPhoto");
        
        if (!isImage(newPhoto) || newPhoto.getSize() > 10485760) {
            LOGGER.warn("Failed to change photo for user {}", user.getUsername());
            requestDispatcher = req.getRequestDispatcher(JspHelper.get("changing-photo-failed"));
            requestDispatcher.forward(req, resp);
            return;
        }
        LOGGER.debug("Attempting to change photo for user {} ", user.getUsername());

        var newUser = userService.changePhoto(user, newPhoto.getInputStream());

        if (newUser.isPresent()) {
            LOGGER.info("Photo changed successfully for user {}", newUser.get().getUsername());
            session.setAttribute("user", newUser.get());

            requestDispatcher = req.getRequestDispatcher(JspHelper.get("profile"));
            requestDispatcher.forward(req, resp);
        } else {
            LOGGER.warn("Failed to change photo for user {}", user.getUsername());
            requestDispatcher = req.getRequestDispatcher(JspHelper.get("changing-photo-failed"));
            requestDispatcher.forward(req, resp);
        }
    }

    private boolean isImage(Part part) {
        try {
            InputStream input = part.getInputStream();
            BufferedImage image = ImageIO.read(input);
            return image != null;
        } catch (IOException ex) {
            return false;
        }
    }

}