package com.epam.training.joaquin_contreras.driver;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private DriverFactory() {
    }

    public static WebDriver createDriver(String browser) {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        String selectedBrowser = (browser == null || browser.isBlank()) ? "chrome" : browser.toLowerCase().trim();

        logger.info("Creating WebDriver instance for browser: '{}' (headless: {})", selectedBrowser, headless);

        WebDriver driver;
        switch (selectedBrowser) {
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                }
                options.addArguments(
                        "--start-maximized",
                        "--window-size=1920,1080",
                        "--remote-allow-origins=*",
                        "--disable-search-engine-choice-screen",
                        "--disable-gpu",
                        "--no-sandbox"
                );
                driver = new EdgeDriver(options);
            }
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                }
                options.addArguments(
                        "--start-maximized",
                        "--window-size=1920,1080",
                        "--remote-allow-origins=*",
                        "--disable-search-engine-choice-screen",
                        "--disable-gpu",
                        "--no-sandbox"
                );
                driver = new ChromeDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + selectedBrowser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return driver;
    }
}
