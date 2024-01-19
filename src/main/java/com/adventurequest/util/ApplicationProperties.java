package com.adventurequest.util;

import com.adventurequest.util.exeption.LoadPropertyException;

import java.io.IOException;
import java.util.Properties;

public final class ApplicationProperties {
    private static final Properties PROPERTIES = new Properties();

    private ApplicationProperties() {
    }

    static {
        loadProperty();
    }

    private static void loadProperty() {
        try (var resourceAsStream = ApplicationProperties.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(resourceAsStream);
        } catch (IOException exception) {
            throw new LoadPropertyException("Exception while loading property",exception);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

}