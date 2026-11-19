package com.epam.training.joaquin_contreras.driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {

    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> browserThreadLocal = ThreadLocal.withInitial(() -> "chrome");

    private DriverManager() {
    }

    public static void setBrowserName(String browser) {
        if (browser != null && !browser.isBlank()) {
            browserThreadLocal.set(browser);
        }
    }

    public static String getBrowserName() {
        return browserThreadLocal.get();
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void initDriver() {
        String browser = getBrowserName();
        String sysProp = System.getProperty("browser");
        if (sysProp != null && !sysProp.isBlank()) {
            browser = sysProp;
        }
        WebDriver driver = DriverFactory.createDriver(browser);
        driverThreadLocal.set(driver);
        logger.info("WebDriver successfully initialized for thread: {} (browser: {})",
                Thread.currentThread().getName(), browser);
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                logger.info("Quitting WebDriver for thread: {}", Thread.currentThread().getName());
                driver.quit();
            } catch (Exception e) {
                logger.error("Error quitting WebDriver", e);
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
}
