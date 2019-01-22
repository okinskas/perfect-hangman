package com.okinskas.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ApplicationProperties {

    private static Properties properties = null;

    public static Properties getProperties() {
        if (properties != null) {
            return properties;
        }

        URL resource = Thread.currentThread().getContextClassLoader().getResource("");
        if (resource == null) {
            System.exit(1);
        }

        String propertiesPath = resource.getPath() + "app.properties";
        properties = new Properties();

        try {
            properties.load(new FileInputStream(propertiesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
