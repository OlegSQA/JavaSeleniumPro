package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignmentNumber9 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void MyVeryFirstTest() {
        try {
            // Login to the admin panel
            driver.get("http://localhost/litecart/admin/");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.logotype")));

            // Navigate to Geo Zones page
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            WebElement h1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
            System.out.println("---------------------------------------");

            if (h1.getText().equals("Geo Zones")) {
                System.out.println("Page with title <<" + h1.getAttribute("textContent").trim() + ">> verified.");
            } else {
                System.out.println("Error: Could not verify Page Title. Found: " + h1.getAttribute("textContent").trim());
                return;
            }

            System.out.println("---------------------------------------");

            // Process each country row in Geo Zones
            List<WebElement> countryList = driver.findElements(By.cssSelector("tr.row a:not([title])"));
            for (int i = 0; i < countryList.size(); i++) {
                // Refresh the country list to avoid StaleElementReferenceException
                countryList = driver.findElements(By.cssSelector("tr.row a:not([title])"));
                WebElement countryLink = countryList.get(i);
                System.out.println(" ");
                System.out.println("Processing  " + countryLink.getAttribute("textContent").trim());
                countryLink.click();

                // Process zones for the selected country
                List<WebElement> zoneSelects = driver.findElements(By.cssSelector("td:nth-child(3) > select"));
                List<String> actualZoneNames = new ArrayList<>();

                for (WebElement selectElement : zoneSelects) {
                    Select dropdown = new Select(selectElement);
                    WebElement selectedOption = dropdown.getFirstSelectedOption();

                    String optionText = selectedOption.getAttribute("textContent").trim();
                    actualZoneNames.add(optionText);

                    //System.out.println("   Zone: " + optionText);
                }

                // Verify alphabetical order
                List<String> sortedZoneNames = new ArrayList<>(actualZoneNames);
                Collections.sort(sortedZoneNames);

                if (actualZoneNames.equals(sortedZoneNames)) {
                    System.out.println("Zones are sorted in alphabetical order.");
                    System.out.println("Expected order: " + sortedZoneNames);
                    System.out.println("Actual order: " + actualZoneNames);
                } else {
                    System.out.println("Zones are NOT sorted in alphabetical order.");
                    System.out.println("Expected order: " + sortedZoneNames);
                    System.out.println("Actual order: " + actualZoneNames);
                }
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");

                driver.navigate().back();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
            }

        } catch (Exception e) {
            System.err.println("Test failed with exception:");
            e.printStackTrace();
        } finally {
            System.out.println(" ");
            System.out.println("------------------------------------------------");
            System.out.println("----------------- TEST COMPLETE ----------------");
            System.out.println("------------------------------------------------");
            driver.quit();
            System.out.println("Driver dismantled");
        }
    }
}