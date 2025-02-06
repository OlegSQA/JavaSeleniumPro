package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RunWith(Parameterized.class)
public class AssignmentNumber14 {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String browser;

    public AssignmentNumber14(String browser) {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void Windows() {
        try {
            driver.get("http://localhost/litecart/admin/");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.logotype")));

            // Navigate to Countries page
            driver.findElement(By.xpath("//span[text()='Countries']")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));

            // Select a random  country
            List<WebElement> countries = driver.findElements(By.cssSelector("tr.row"));
            if (countries.isEmpty()) {
                System.out.println("No Countries found!");
                return;
            }
            Random rand = new Random();
            int randomIndex = rand.nextInt(countries.size());
            WebElement randomCountry = countries.get(randomIndex);
            WebElement countryName = randomCountry .findElement(By.tagName("a"));
            System.out.println("Selected Country: " + countryName.getAttribute("textContent"));
            countryName.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
            String h1 = driver.getTitle();


            // Store original window handle
            String originalWindow = driver.getWindowHandle();
            System.out.println("Original Window title: " + h1);
            System.out.println("Original Window handle: " + originalWindow);

            Set<String> existingWindows = driver.getWindowHandles();

            // Find all links that open in a new tab
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form[method = post] table")));

            WebElement table = driver.findElement(By.cssSelector("table"));
            List<WebElement> links = table.findElements(By.cssSelector(".fa-external-link"));

            System.out.println("Total external links found: " + links.size());

            for (WebElement link : links) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
                wait.until(ExpectedConditions.elementToBeClickable(link)).click();

                // Wait for the new window to appear
                String newWindow = wait.until(anyWindowOtherThan(existingWindows));

                if (newWindow != null) {
                    driver.switchTo().window(newWindow);
                    String newWindowHandle = driver.getWindowHandle();
                    System.out.println("New Window handle: " + newWindowHandle);
                    System.out.println("Switched to new window: " + driver.getTitle());

                    driver.close();
                    driver.switchTo().window(originalWindow);
                    System.out.println("Closed new window and switched back to Original Window: " + originalWindow);
                } else {
                    System.out.println("No new window detected.");
                }
            }
        } catch (Exception e) {
            System.err.println("Test failed with exception:");
            e.printStackTrace();
        } finally {
            System.out.println(" ");
            System.out.println("----------------- TEST COMPLETE ----------------");
            if (driver != null) {
                driver.quit();
                System.out.println("--------------- Driver dismantled --------------");
            }
        }
    }

    private ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.isEmpty() ?  null : handles.iterator().next() ;
            }
        };
    }
}
