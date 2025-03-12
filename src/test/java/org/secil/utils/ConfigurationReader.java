package org.secil.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties = new Properties();

    static {
        try {

            FileInputStream configFile = new FileInputStream("configuration.properties");
            properties.load(configFile);
            configFile.close();
        } catch (IOException e) {

            e.printStackTrace();
            System.out.println("There is an error in ConfigurationReader class");
        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}
