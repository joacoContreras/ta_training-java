package com.epam.training.joaquin_contreras.steps;

import com.epam.training.joaquin_contreras.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        logger.info("Initializing test for scenario: '{}' on browser: '{}'",
                scenario.getName(), DriverManager.getBrowserName());
        DriverManager.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                if (scenario.isFailed()) {
                    logger.warn("Scenario '{}' failed. Capturing failure screenshot...", scenario.getName());
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "failure-screenshot");
                }
            } catch (Exception e) {
                logger.error("Failed to capture screenshot", e);
            } finally {
                DriverManager.quitDriver();
                logger.info("Completed scenario: '{}' - Status: {}", scenario.getName(), scenario.getStatus());
            }
        }
    }
}
