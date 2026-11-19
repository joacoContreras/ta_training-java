# SauceDemo Sorting Automation - EPAM Java Training

Automated end-to-end test suite for [SauceDemo](https://www.saucedemo.com) sorting functionality, developed in Java using Selenium WebDriver, Cucumber BDD, TestNG, and Allure Reports.

---

## Technical Stack & Prerequisites

- **Java JDK**: 17 (`17.0.6`+ / LTS)
- **Build Tool**: Apache Maven (via included `mvnw` wrapper)
- **Test Automation Tool**: Selenium WebDriver `4.52.0`
- **BDD Framework**: Cucumber `7.20.1` (`cucumber-java`, `cucumber-testng`)
- **Test Runner**: TestNG `7.10.2`
- **Assertions**: AssertJ `3.26.3`
- **Logging**: SLF4J `2.0.16` + Apache Log4j2 `2.24.1`
- **Reporting**: Allure Report `2.29.0` + Cucumber HTML reports
- **Supported Browsers**: Google Chrome, Microsoft Edge (executed in parallel)

---

## Architectural Patterns & Good Practices

1. **Page Object Model (POM)**:
   - `BasePage`: Encapsulates explicit `WebDriverWait` (10s timeout), safe element interactions (`click`, `type`, `selectByVisibleText`, `getText`), and logger output.
   - `LoginPage`: Encapsulates authentication elements and login flow.
   - `InventoryPage`: Encapsulates product catalog interactions, sort selection dropdown, and retrieval of item names and prices.
2. **Factory Pattern**:
   - `DriverFactory`: Instantiates configured `WebDriver` sessions (`ChromeDriver`, `EdgeDriver`) with optimized arguments (`--start-maximized`, `--disable-search-engine-choice-screen`, headless support, timeout policies).
3. **ThreadLocal Session Management**:
   - `DriverManager`: Thread-safe management of `WebDriver` instances enabling concurrent cross-browser execution without race conditions.
4. **Locators (CSS & XPath)**:
   - Resilient locators using both CSS Selectors (e.g., `#user-name`, `#login-button`, `span.active_option`, `div.inventory_item_price`) and XPath (e.g., `//input[@id='password']`, `//select[@data-test='product-sort-container']`, `//div[@data-test='inventory-item-name']`).
5. **Assertions**:
   - Fluent, descriptive assertions via AssertJ verifying non-empty lists and exact ordered sequences with meaningful failure messages.
6. **Logging**:
   - Comprehensive logging via SLF4J/Log4j2 to both the terminal and `target/logs/test.log`.
7. **Failure Diagnostics**:
   - Automatic screenshot capture on test failure attached to the scenario in Allure.

---

## Implemented Use Cases

All scenarios are defined in `src/test/resources/features/sorting.feature` using Gherkin syntax (`Given-When-Then`):

| Use Case | Scenario | Description |
| :--- | :--- | :--- |
| **UC-1** | `UC-1 Sort by Name (A to Z)` | Login as `standard_user`, select "Name (A to Z)", verify items are in alphabetical order (A → Z). |
| **UC-2** | `UC-2 Sort by Name (Z to A)` | Login as `standard_user`, select "Name (Z to A)", verify items are in reverse alphabetical order (Z → A). |
| **UC-3** | `UC-3 Sort by Price (low to high)` | Login as `standard_user`, select "Price (low to high)", verify items are sorted by price ascending (low → high). |
| **UC-4** | `UC-4 Sort by Price (high to low)` | Login as `standard_user`, select "Price (high to low)", verify items are sorted by price descending (high → low). |

---

## Execution Guide

### 1. Ensure Java 17 is active
Set `JAVA_HOME` to your JDK 17 installation path:

**Windows (PowerShell):**
```powershell
$env:JAVA_HOME="C:\Users\cjoaq\.jdks\ms-17.0.19"
```

**Linux / macOS:**
```bash
export JAVA_HOME=/path/to/jdk-17
```

---

### 2. Run All Tests in Parallel (Chrome & Edge)
Executes all 4 use cases on Google Chrome and Microsoft Edge concurrently (8 scenario executions total):

```powershell
.\mvnw.cmd clean test
```

---

### 3. Run on a Specific Browser
You can run only the Chrome suite or only the Edge suite:

**Run Chrome tests only:**
```powershell
.\mvnw.cmd test -Dtest=ChromeCucumberTestRunner
```

**Run Edge tests only:**
```powershell
.\mvnw.cmd test -Dtest=EdgeCucumberTestRunner
```

---

### 4. Headed / Headless Execution Mode
By default, tests run in `--headless` mode for fast, non-intrusive CI execution. To view browser windows open during the test run:

```powershell
.\mvnw.cmd test -Dheadless=false
```

---

## Generating and Viewing Test Reports

### Allure Report (Recommended)

1. **Generate the Allure HTML report**:
   ```powershell
   .\mvnw.cmd allure:report
   ```
   The static HTML report will be generated at:
   `target/site/allure-maven-plugin/index.html`

2. **Serve the Allure report in a local web browser**:
   ```powershell
   .\mvnw.cmd allure:serve
   ```

### Cucumber HTML Reports
Native Cucumber HTML reports are generated per browser in:
- Chrome: `target/cucumber-reports/chrome/cucumber.html`
- Edge: `target/cucumber-reports/edge/cucumber.html`

### TestNG Reports
Standard TestNG reports are available in:
- `target/surefire-reports/index.html`
- `target/surefire-reports/emailable-report.html`

### Execution Logs
Detailed logs are written to:
- `target/logs/test.log`

---

## Commit History

Each use case was implemented atomically with its own commit under `joacoContreras`:
- `feat: setup test framework infrastructure (Selenium, Cucumber BDD, POM, DriverFactory, Logger, Allure)`
- `feat: implement UC-1 Sort by Name (A to Z)`
- `feat: implement UC-2 Sort by Name (Z to A)`
- `feat: implement UC-3 Sort by Price (low to high)`
- `feat: implement UC-4 Sort by Price (high to low)`
- `docs: update README with execution and Allure reporting instructions`
