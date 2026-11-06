package com.epam.training.joaquin_contreras.webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstAutomation {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            driver.get(
                    "https://www.selenium.dev/selenium/web/web-form.html"
            );

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.name("my-text")
                    )
            ).sendKeys("Hola, Selenium");

            driver.findElement(
                    By.cssSelector("button[type='submit']")
            ).click();

            String mensaje = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("message")
                    )
            ).getText();

            if (!"Received!".equals(mensaje)) {
                throw new AssertionError(
                        "Se esperaba 'Received!', pero se obtuvo: " + mensaje
                );
            }

            System.out.println(
                    "Prueba exitosa: el formulario se envió correctamente."
            );

        } finally {
            driver.quit();
        }
    }
}
