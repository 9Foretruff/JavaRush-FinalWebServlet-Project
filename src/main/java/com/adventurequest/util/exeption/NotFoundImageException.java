package com.adventurequest.util.exeption;

public class NotFoundImageException extends RuntimeException {
    public NotFoundImageException(String message) {
        super(message);
    }

    public NotFoundImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
