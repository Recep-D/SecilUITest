package org.secil.utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
//import org.secil.pages.LocatorsPage;

public class Hooks {


    @BeforeAll
    public static void setUp() {
        String browserNameConfig = ConfigurationReader.getProperty("browserName");

        // boolean isHeadless = Boolean.parseBoolean(ConfigurationReader.getProperty("headless"));
        String headlessMod = ConfigurationReader.getProperty("headless");

        BrowserFactory.initBrowser(browserNameConfig, headlessMod);

    }

    @AfterAll
    public static void tearDown() {
        BrowserFactory.closeItems();
    }
}
