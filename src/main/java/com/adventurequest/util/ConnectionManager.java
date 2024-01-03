package com.adventurequest.util;

import com.adventurequest.util.exeption.JdbcConnectionException;
import com.adventurequest.util.exeption.LoadDriverException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private final static String URL_KEY = "db.url";
    private final static String USER_KEY = "db.user";
    private final static String PASSWORD_KEY = "db.password";
    private final static String DRIVER_KEY = "db.driver";

    private ConnectionManager() {
    }

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName(ApplicationProperties.get(DRIVER_KEY));
        } catch (ClassNotFoundException cause) {
            throw new LoadDriverException("Exception while loading driver", cause);
        }
    }

    public static Connection get(){
        try {
            return DriverManager.getConnection(
                    ApplicationProperties.get(URL_KEY),
                    ApplicationProperties.get(USER_KEY),
                    ApplicationProperties.get(PASSWORD_KEY)
            );
        } catch (SQLException cause) {
            throw new JdbcConnectionException("Exception while making connection to database",cause);
        }
    }

}