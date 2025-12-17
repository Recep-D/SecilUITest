package org.secil.utils;

import com.microsoft.playwright.Tracing;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {

  private static Path tracePath;

  @BeforeAll
  public static void setUp() throws Exception {

    String browserNameConfig = ConfigurationReader.getProperty("browserName");
    String headlessMod = ConfigurationReader.getProperty("headless");

    BrowserFactory.initBrowser(browserNameConfig, headlessMod);

    // Trace output klasörü
    Path traceDir = Paths.get("target", "traces");
    Files.createDirectories(traceDir);

    // Her run için farklı dosya ismi
    tracePath = traceDir.resolve("trace-" + System.currentTimeMillis() + ".zip");

    // TRACE START (initBrowser'dan sonra!)
    BrowserFactory.browserContext.tracing().start(new Tracing.StartOptions()
      .setScreenshots(true)
      .setSnapshots(true)
      .setSources(true)
    );
  }

  @AfterAll
  public static void tearDown() {

    // TRACE STOP + SAVE (closeItems'dan önce!)
    try {
      BrowserFactory.browserContext.tracing().stop(
        new Tracing.StopOptions().setPath(tracePath)
      );
      System.out.println("Trace saved: " + tracePath.toAbsolutePath());
    } catch (Exception e) {
      System.err.println("Trace could not be saved: " + e.getMessage());
    } finally {
      BrowserFactory.closeItems();
    }
  }
}
