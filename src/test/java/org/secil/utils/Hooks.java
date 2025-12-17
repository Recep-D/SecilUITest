package org.secil.utils;

import com.microsoft.playwright.Tracing;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {

  private Path tracePath;

  @BeforeEach
  public void setUp(TestInfo testInfo) throws Exception {

    String browserNameConfig = ConfigurationReader.getProperty("browserName");
    String headlessMod = ConfigurationReader.getProperty("headless");
    BrowserFactory.initBrowser(browserNameConfig, headlessMod);

    // Trace output klasörü
    Path traceDir = Paths.get("target", "traces");
    Files.createDirectories(traceDir);

    // Trace altında her TEST için farklı dosya ismi (pass/fail fark etmez)
    String methodName = testInfo.getTestMethod()
        .map(m -> m.getName())
        .orElse(testInfo.getDisplayName());

    String className = testInfo.getTestClass()
        .map(c -> c.getSimpleName())
        .orElse("UnknownClass");

    String safeName = (className + "-" + methodName).replaceAll("[^a-zA-Z0-9-_]", "_");

    tracePath = traceDir.resolve("trace-" + safeName + "-" + System.currentTimeMillis() + ".zip");

    // TRACE START (initBrowser'dan sonra)
    BrowserFactory.browserContext.tracing().start(new Tracing.StartOptions()
        .setScreenshots(true)
        .setSnapshots(true)
        .setSources(true));
  }

  @AfterEach
  public void tearDown() {

    // TRACE STOP + SAVE (closeItems'dan önce)
    try {
      BrowserFactory.browserContext.tracing().stop(
          new Tracing.StopOptions().setPath(tracePath));
      System.out.println("Trace saved: " + tracePath.toAbsolutePath());
    } catch (Exception e) {
      System.err.println("Trace could not be saved: " + e.getMessage());
    } finally {
      BrowserFactory.closeItems();
    }
  }
}
