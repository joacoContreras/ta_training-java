package com.epam.training.joaquin_contreras.steps;

import com.epam.training.joaquin_contreras.driver.DriverManager;
import com.epam.training.joaquin_contreras.pages.LoginPage;
import io.cucumber.java.en.Given;

public class LoginSteps {

    @Given("the user is on the SauceDemo login page")
    public void userIsOnLoginPage() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.open();
    }

    @Given("the user logs in as {string} with {string}")
    public void userLogsInWithCredentials(String username, String password) {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(username, password);
    }

    @Given("the user logs in as standard_user")
    public void userLogsInAsStandardUser() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login("standard_user", "secret_sauce");
    }
}
