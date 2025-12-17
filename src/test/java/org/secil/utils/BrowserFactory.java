package org.secil.utils;

import com.microsoft.playwright.*;

import java.awt.*;//awt:abstract window toolkit
import java.nio.file.Paths;

public class BrowserFactory {

    private static Playwright playwright;
    private static BrowserType browserType;
    private static Browser browser;
    public static BrowserContext browserContext;
    public static Page page;

    public static void initBrowser(String browserName, String headless) {
        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "chromium":
                browserType = playwright.chromium();
                break;
            case "firefox":
                browserType = playwright.firefox();
                break;
            case "webkit":
                browserType = playwright.webkit();
                break;
            default:
                throw new IllegalArgumentException("Browser name is not provided or is empty. Please check the configuration.");
        }

        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(headless)));
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("src/test/resources/videos")));

        page = browserContext.newPage();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();
        page.setViewportSize(width, height); 
        

    }

    public static void closeItems() {
        if (browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
