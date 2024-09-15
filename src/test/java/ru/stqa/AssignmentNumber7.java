package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class AssignmentNumber7 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        driver = new ChromeDriver();
        //firefoxDriver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void LocatingStickers() {
        driver.get("http://localhost/litecart/en/");
        //if (areElementsPresent(driver, By.cssSelector("div.image-wrapper"))) {
        if (areElementsPresent(driver, By.cssSelector("div.image-wrapper"))) {
            System.out.println("Wrapper is present.");
        } else {
            System.out.println("No wrappers found.");
        }
        List<WebElement> imageWrappers = driver.findElements(By.cssSelector("div.image-wrapper"));
        System.out.println("Total number of image-wrapper elements is " + imageWrappers.size());
        System.out.println("---------------------------------------------------");

        for (int i = 0; i < imageWrappers.size(); i++) {
            WebElement imageWrapper = imageWrappers.get(i);
            List<WebElement> stickers = imageWrapper.findElements(By.cssSelector("div.sticker"));

            // Check if exactly one "sticker" is found and include the list index in the output
            if (stickers.size() == 1) {
                System.out.println("Correct: Found 1 (" + stickers.get(0).getText() + ") sticker in image-wrapper number " + (i + 1) + ".");
            } else {
                System.out.println("Error: Found " + stickers.size() + " stickers in image-wrapper " + (i + 1) + ".");
            }
        }
        System.out.println("success");
    }
    private static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
    private static boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }


    @After
    public void stop () {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        System.out.println("driver dismantled");
    }

}

