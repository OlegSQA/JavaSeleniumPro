package ru.stqa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignmentNumber8 {
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
            driver.get("http://localhost/litecart/admin/");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.logotype")));

            // Navigate to Countries page
            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            WebElement h1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

            if (h1.getText().equals("Countries")) {
                System.out.println("Page with title <<" + h1.getAttribute("textContent").trim() + ">> verified.");
                System.out.println( "    ");
            } else {
                System.out.println("Error: Could not verify Page Title. Found: " + h1.getAttribute("textContent").trim());
            }

            // Retrieve country names from the table (column 5)
            List<WebElement> countryElements = driver.findElements(By.cssSelector("tr.row td:nth-child(5)"));
            List<String> actualCountryNames = new ArrayList<>();

            for (WebElement element : countryElements) {
                actualCountryNames.add(element.getAttribute("textContent").trim());
            }

            // Create a sorted copy of the list
            List<String> sortedCountryNames = new ArrayList<>(actualCountryNames);
            Collections.sort(sortedCountryNames);

            // Verify alphabetical order
            if (actualCountryNames.equals(sortedCountryNames)) {
                System.out.println("The country rows are sorted alphabetically.");
                System.out.println("Expected Order: " + sortedCountryNames);
                System.out.println("Actual Order: " + actualCountryNames);

            } else {
                System.out.println("The country rows are NOT sorted alphabetically.");
                System.out.println("Expected Order: " + sortedCountryNames);
                System.out.println("Actual Order: " + actualCountryNames);
            }System.out.println( "    ");
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println( "    ");

            List<WebElement> zones = driver.findElements(By.cssSelector("tr.row td:nth-child(6)"));
            List<WebElement> countryNames = driver.findElements(By.cssSelector("tr.row td:nth-child(5)"));

            for (int i = 0; i < zones.size(); i++) {
                String zoneValue = zones.get(i).getAttribute("outerText").trim();
                String countryName = countryNames.get(i).getAttribute("outerText").trim();
                if (!"0".equals(zoneValue)) {
                    try {
                        WebElement link = countryNames.get(i).findElement(By.cssSelector("a"));
                        link.click();
                        // Wait for the zones table to appear
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table#table-zones")));

                        List<WebElement> zoneElements = driver.findElements(By.cssSelector("table#table-zones tr > td:nth-child(3)"));
                        List<String> actualZoneNames = new ArrayList<>();
                        for (WebElement element : zoneElements) {
                            String zoneName = element.getAttribute("outerText").trim();
                            if (!zoneName.isEmpty()) {
                                actualZoneNames.add(zoneName);
                            }
                        }
                        List<String> sortedZoneNames = new ArrayList<>(actualZoneNames);
                        Collections.sort(sortedZoneNames);

                        System.out.println( "There are " + zoneValue + " Zones in " + countryName);

                        if (actualZoneNames.equals(sortedZoneNames)) {
                            System.out.println("Zones are sorted alphabetically.");
                            System.out.println( "Expected order : " + sortedZoneNames);
                            System.out.println( "Actual order : " + actualZoneNames );
                            System.out.println( "    ");
                        } else {
                            System.out.println("Zones are NOT sorted alphabetically.");
                        }

                        // Navigate back to the country list
                        driver.navigate().back();

                        // Reinitialize the lists
                        zones = driver.findElements(By.cssSelector("tr.row td:nth-child(6)"));
                        countryNames = driver.findElements(By.cssSelector("tr.row td:nth-child(5)"));

                    } catch (Exception e) {
                        System.out.println("Error processing country: " + countryName);
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println( "There aren't any Zones listed for any other Country at this time.");
            System.out.println( "    ");
            System.out.println("------------------------------------------------");
            System.out.println("----------------- TEST COMPLETE ----------------");
            System.out.println("------------------------------------------------");
            driver.quit();
            System.out.println("Driver dismantled");
        }
    }
}
