package org.secil.utils;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class BrowserFactory {

    private static Playwright playwright;
    private static BrowserType browserType;
    private static Browser browser;
    private static BrowserContext browserContext;
    public static Page page;

    public static void initBrowser(String browserName, boolean headless) {
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

        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless));
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("src/test/resources/videos")));

        page = browserContext.newPage();
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
