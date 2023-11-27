package com.adventurequest.util;

import com.adventurequest.util.exeption.ImageNullException;
import com.adventurequest.util.exeption.NotFoundImageException;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;

public final class DefaultProfileImageUtil {
    private static final String DEFAULT_IMAGE_PATH = "img/default-user-photo.png";

    private DefaultProfileImageUtil() {
    }

    public static byte[] getDefaultProfileImageBytes() {
        try (InputStream inputStream = DefaultProfileImageUtil.class.getClassLoader().getResourceAsStream(DEFAULT_IMAGE_PATH)) {
            if (inputStream != null) {
                return inputStream.readAllBytes();
            } else {
                throw new ImageNullException("Default profile image not found.");
            }
        } catch (IOException e) {
            throw new NotFoundImageException("Failed to read default profile image.", e);
        }
    }
}