package com.epam.training.joaquin_contreras.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.training.joaquin_contreras.driver.DriverManager;
import com.epam.training.joaquin_contreras.pages.InventoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventorySteps {

    private static final Logger logger = LoggerFactory.getLogger(InventorySteps.class);

    private InventoryPage getInventoryPage() {
        return new InventoryPage(DriverManager.getDriver());
    }

    @When("the user selects {string} in sorting dropdown")
    public void userSelectsInSortingDropdown(String optionText) {
        logger.info("Step: selecting '{}' in sorting dropdown", optionText);
        getInventoryPage().selectSortOption(optionText);
    }

    @Then("items should be sorted in alphabetical order from A to Z")
    public void itemsShouldBeSortedAlphabeticallyAtoZ() {
        logger.info("Step: verifying items are sorted in alphabetical order (A to Z)");
        List<String> actualNames = getInventoryPage().getProductNames();
        assertThat(actualNames)
                .as("Product list should not be empty")
                .isNotEmpty();

        List<String> expectedSortedNames = actualNames.stream().sorted().toList();
        logger.info("Expected: {}, Actual: {}", expectedSortedNames, actualNames);

        assertThat(actualNames)
                .as("Items should be sorted alphabetically from A to Z")
                .containsExactlyElementsOf(expectedSortedNames);
    }

    @Then("items should be sorted in reverse alphabetical order from Z to A")
    public void itemsShouldBeSortedInReverseAlphabeticalOrderZtoA() {
        logger.info("Step: verifying items are sorted in reverse alphabetical order (Z to A)");
        List<String> actualNames = getInventoryPage().getProductNames();
        assertThat(actualNames)
                .as("Product list should not be empty")
                .isNotEmpty();

        List<String> expectedSortedNames = actualNames.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        logger.info("Expected: {}, Actual: {}", expectedSortedNames, actualNames);

        assertThat(actualNames)
                .as("Items should be sorted in reverse alphabetical order from Z to A")
                .containsExactlyElementsOf(expectedSortedNames);
    }

    @Then("items should be sorted by price from low to high")
    public void itemsShouldBeSortedByPriceFromLowToHigh() {
        logger.info("Step: verifying items are sorted by price (low to high)");
        List<Double> actualPrices = getInventoryPage().getProductPrices();
        assertThat(actualPrices)
                .as("Price list should not be empty")
                .isNotEmpty();

        List<Double> expectedSortedPrices = actualPrices.stream().sorted().toList();
        logger.info("Expected: {}, Actual: {}", expectedSortedPrices, actualPrices);

        assertThat(actualPrices)
                .as("Items should be sorted by price from low to high")
                .containsExactlyElementsOf(expectedSortedPrices);
    }
}
