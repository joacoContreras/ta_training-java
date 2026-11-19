package com.epam.training.joaquin_contreras.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage extends BasePage {

    // Locators using CSS Selectors and XPath
    private final By productSortDropdown = By.xpath("//select[@data-test='product-sort-container']");
    private final By activeSortOption = By.cssSelector("span.active_option");
    private final By inventoryItemNames = By.xpath("//div[@data-test='inventory-item-name']");
    private final By inventoryItemPrices = By.cssSelector("div.inventory_item_price");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void selectSortOption(String visibleText) {
        logger.info("Selecting sorting option: '{}'", visibleText);
        selectByVisibleText(productSortDropdown, visibleText);
    }

    public String getActiveSortOptionText() {
        return getText(activeSortOption);
    }

    public List<String> getProductNames() {
        List<WebElement> elements = waitForElements(inventoryItemNames);
        List<String> names = elements.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
        logger.info("Retrieved {} product names: {}", names.size(), names);
        return names;
    }

    public List<Double> getProductPrices() {
        List<WebElement> elements = waitForElements(inventoryItemPrices);
        List<Double> prices = elements.stream()
                .map(WebElement::getText)
                .map(text -> text.replace("$", "").trim())
                .map(Double::parseDouble)
                .toList();
        logger.info("Retrieved {} product prices: {}", prices.size(), prices);
        return prices;
    }
}
