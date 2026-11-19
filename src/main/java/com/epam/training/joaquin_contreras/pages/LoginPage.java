package com.epam.training.joaquin_contreras.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators combining CSS Selectors and XPath
    private final By usernameInput = By.cssSelector("#user-name");
    private final By passwordInput = By.xpath("//input[@id='password']");
    private final By loginButton = By.cssSelector("#login-button");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        navigateTo("https://www.saucedemo.com");
        return this;
    }

    public InventoryPage login(String username, String password) {
        logger.info("Logging in with username: '{}'", username);
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
