package com.adventurequest.util;

import com.adventurequest.model.entity.UserEntity;
import jakarta.servlet.http.HttpSession;

public final class UserSessionHelper {
    public static String getUsername(HttpSession session) {

        UserEntity user = getUser(session);

        if (user == null) {
            return null;
        }

        return user.getUsername();

    }

    public static UserEntity getUser(HttpSession session) {

        Object userObj = session.getAttribute("user");

        if (userObj == null) {
            return null;
        }

        if (userObj instanceof UserEntity) {
            return (UserEntity) userObj;
        } else {
            throw new IllegalStateException("Invalid user type in session");
        }

    }

}