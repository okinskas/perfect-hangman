package com.okinskas.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    private static Properties properties = null;

    public static Properties getProperties() {
        if (properties != null) {
            return properties;
        }

        InputStream resource = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("app.properties");
        properties = new Properties();

        try {
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
