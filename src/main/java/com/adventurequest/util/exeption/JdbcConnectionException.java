package com.adventurequest.util.exeption;

public class JdbcConnectionException extends RuntimeException{
    public JdbcConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
