package com.adventurequest.controller.servlet.exception;

public class LogoutException extends RuntimeException {
    public LogoutException(String message) {
        super(message);
    }
}