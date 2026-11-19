package com.epam.training.joaquin_contreras.runners;

import com.epam.training.joaquin_contreras.driver.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.epam.training.joaquin_contreras.steps"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/edge/cucumber.html",
                "json:target/cucumber-reports/edge/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class EdgeCucumberTestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setUpBrowser(@Optional("edge") String browser) {
        DriverManager.setBrowserName(browser != null && !browser.isBlank() ? browser : "edge");
    }
}
