package ru.stqa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Text;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static java.awt.SystemColor.text;
import static java.lang.reflect.Array.get;
import static javax.swing.text.html.CSS.getAttribute;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

@RunWith(Parameterized.class)
public class AssignmentNumber13 {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String browser;

    public AssignmentNumber13(String browser) {
        this.browser = browser;
    }
    @Parameterized.Parameters(name = "Browser: {0}")
    public static List<String> browsers() {
        return Arrays.asList("Chrome", "Firefox", "Safari");
    }

    @Before
    public void start() {
        System.out.println();
        System.out.println("Starting test on " + browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @Test
    public void MyVeryFirstTest() {
        driver.get("http://localhost/litecart/");
        List<WebElement> mostPopularDucksList = driver.findElements(By.cssSelector("div#box-most-popular a.link"));

        if (mostPopularDucksList.isEmpty()) {
            System.out.println("No ducks found!");
            return;
        }

        int itemsToClick = Math.min(3, mostPopularDucksList.size());
        for (int i = 0; i < itemsToClick; i++) {
            WebElement duck = mostPopularDucksList.get(i);
            String duckTitle = duck.getAttribute("title");
            duck.click();
            System.out.println("Clicked on " + duckTitle);

//----------------------- Select size (if available)------------------------------

            try {
                // Wait for the dropdown container to be present
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name='options[Size]']")));
                WebElement sizeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[name='options[Size]']")));
                Select dropdown = new Select(sizeDropdown);
                List<WebElement> allOptions = dropdown.getOptions();
                List<WebElement> validOptions = allOptions.subList(1, allOptions.size());
                if (!allOptions.isEmpty()) {
                    // Choose a random option
                    Random random = new Random();
                    int index = random.nextInt(validOptions.size());
                    validOptions.get(index).click();
                    System.out.println("Selected option: " + validOptions.get(index).getText());
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                } else {
                }
            } catch (TimeoutException e) {
                // no options found...proceeding
            }

// ---------------------Click the "Add to Cart" button-----------------------

            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='add_cart_product']")));
            addToCartButton.click();

            // -------------------Verify the cart count has updated-------------------

            WebElement cartCount = driver.findElement(By.cssSelector("span.quantity"));
            // Get initial count before adding a duck
            String initialCount = cartCount.getAttribute("textContent");
            wait.until(driver -> {
                String currentCount = cartCount.getAttribute("textContent");
                return !currentCount.equals(initialCount);
            });
            System.out.println(duckTitle + " added to the cart! Current Duck Count: " + cartCount.getAttribute("textContent"));

            // -------------------Navigate back to the main page------------------------

            driver.navigate().back();
            mostPopularDucksList = driver.findElements(By.cssSelector("div#box-most-popular a.link")); // Refresh the list
        }

//--------------------------Navigate to checkout page------------------------

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(), 'Checkout')]"))).click();
        System.out.println("Proceeding to checkout....");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name='confirm_order']")));

//------------------------------ Removing ducks -----------------------------------------------

        while (true) {
            List<WebElement> ducksInTheSummary = driver.findElements(By.xpath("//tr[td[contains(@class, 'item')]]"));
            if (ducksInTheSummary.isEmpty()) {
                System.out.println("All ducks removed. Cart is empty!");
                break;
            }
            // Count the number of rows (ducks) before removal
            int duckQuantityBefore = ducksInTheSummary.size();
            List<WebElement> rows = driver.findElements(By.cssSelector("tr:has(td.item)"));
            int totalSum = 0;
            // Iterate through rows and get the value from the first <td>
            for (WebElement row : rows) {
                try {

                    WebElement firstCell = row.findElement(By.cssSelector("td:first-child"));
                    String cellText = firstCell.getText().trim();
                    totalSum += Integer.parseInt(cellText); // Parse and add
                } catch (NoSuchElementException | NumberFormatException e) {
                    // Handle missing <td> or non-numeric value
                }
            }

            // Print the total sum
            System.out.println("Current Duck Count: " + totalSum );
            // Remove first duck
            WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[name='remove_cart_item']")));
            WebElement duckRow = ducksInTheSummary.get(0);
            WebElement duckNameCell = duckRow.findElement(By.cssSelector("td.item"));
            String duckName = duckNameCell.getText();
            System.out.println("Removing " + duckName + " ...");
            removeButton.click();

            // Wait until the number of rows (ducks) decreases
            wait.until(driver -> {
                List<WebElement> updatedDucks = driver.findElements(By.xpath("//tr[td[contains(@class, 'item')]]"));
                return updatedDucks.size() < duckQuantityBefore;
            });
        }
    }

    @After
    public void teardown() {
        driver.quit();
        System.out.println("Driver dismantled");
    }
}



