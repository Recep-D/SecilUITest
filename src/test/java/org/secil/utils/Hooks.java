package org.secil.utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class Hooks {

    @BeforeAll
    public static void setUp() {
        String browserNameConfig = ConfigurationReader.getProperty("browserName");
        boolean isHeadless = Boolean.parseBoolean(ConfigurationReader.getProperty("headless"));

        BrowserFactory.initBrowser(browserNameConfig, isHeadless);
    }

    @AfterAll
    public static void tearDown() {
        BrowserFactory.closeItems();
    }
}
