package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AssignmentNumber17 {
    private WebDriver driver;
    private WebDriverWait wait;



    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void Windows() {
        try {
            // Log in
            driver.get("http://localhost/litecart/admin/");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.logotype")));

            // Navigate to Catalog page and open Rubber Ducks folder
            driver.findElement(By.xpath("//span[text()='Catalog']")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Rubber Ducks']")));
            driver.findElement(By.xpath("//a[text()='Rubber Ducks']")).click();

            // Get all category rows excluding [Root] and Rubber Ducks rows
            List<WebElement> rows = driver.findElements(By.xpath(
                    "//tr[@class='row' and not(.//a[text()='[Root]']) and not(.//a[text()='Rubber Ducks'])]"
            ));

            // Separate rows into duck products and subfolders based on link text.
            List<String> duckUrls = new ArrayList<>();
            List<String> subfolderUrls = new ArrayList<>();

            for (WebElement row : rows) {
                WebElement link = row.findElement(By.xpath(".//td//a"));
                String text = link.getText().trim();
                String url = link.getAttribute("href");
                // Adjust condition based on your actual folder names
                if (text.equalsIgnoreCase("Subcategory")) {
                    subfolderUrls.add(url);
                } else {
                    duckUrls.add(url);
                }
            }

            System.out.println("Found " + duckUrls.size() + " duck product rows.");
            System.out.println("Found " + subfolderUrls.size() + " 'Subcategory' rows.");

            // Process duck product links
            for (String duckUrl : duckUrls) {
                driver.get(duckUrl);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
                System.out.println("Duck detail page header: " + driver.findElement(By.cssSelector("h1")).getText());
                System.out.println("Clicking duck link: " + duckUrl);

                // Get logs
                System.out.println(driver.manage().logs().getAvailableLogTypes());
                driver.manage().logs().get("browser").forEach(l -> System.out.println(l));

                // Go back
                driver.navigate().back();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//tr[@class='row' and not(.//a[text()='[Root]']) and not(.//a[text()='Rubber Ducks'])]"
                )));
            }
            System.out.println(" --------------------------------------------------");

            // Process each subfolder separately.
            for (String subfolderUrl : subfolderUrls) {
                driver.get(subfolderUrl);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
                System.out.println("Opened 'Subcategory' folder: " + driver.getCurrentUrl());

                // Process duck rows directly inside the subfolder.
                // Re-fetch the duck rows on the subfolder page using your criteria.
                List<WebElement> duckRows = driver.findElements(By.xpath("//td[.//img[contains(@style, 'margin-left: 48px;')] and not(.//a[text()='Subcategory'])]"));
                int duckRowCount = duckRows.size();
                System.out.println("'Subcategory' folder page has " + duckRowCount + " duck rows.");

                // Iterate over the duck rows by index.
                for (int i = 0; i < duckRowCount; i++) {
                    // Re-fetch the list in each iteration to avoid stale element issues.
                    duckRows = driver.findElements(By.xpath("//td[.//img[contains(@style, 'margin-left: 48px;')] and not(.//a[text()='Subcategory'])]"));
                    WebElement duckRow = duckRows.get(i);
                    WebElement duckLink = duckRow.findElement(By.xpath(".//a"));
                    String duckUrl = duckLink.getAttribute("href");
                    System.out.println("Clicking duck link: " + duckUrl);
                    duckLink.click();

                    // Wait for the duck detail page to load (for example, by waiting for an h1 element).
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
                    System.out.println("Duck detail page header: " + driver.findElement(By.cssSelector("h1")).getText());

                    // Get logs
                    System.out.println(driver.manage().logs().getAvailableLogTypes());
                    driver.manage().logs().get("browser").forEach(l -> System.out.println(l));

                    // After processing the duck detail page, navigate back to the subfolder page.
                    driver.navigate().back();
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                            "//tr[@class='row' and not(.//a[text()='[Root]']) and not(.//a[text()='Rubber Ducks'])]"
                    )));
                }
            }

            Thread.sleep(2000); // For observation

        } catch (Exception e) {
            System.err.println("Test failed with exception:");
            e.printStackTrace();
        } finally {
            System.out.println("----------------- TEST COMPLETE ----------------");
            if (driver != null) {
                driver.quit();
                System.out.println("--------------- Driver dismantled --------------");
            }
        }
    }
}
