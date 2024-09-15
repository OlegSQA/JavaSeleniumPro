package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class test {

    private static WebDriver driver;

    //private WebDriver driver;
    public static void main(String[] args) {
        driver = new ChromeDriver();

        driver.get("http://localhost/litecart/en/");
        List<WebElement> stickers = driver.findElements(By.cssSelector("div.sticker"));
        System.out.println("Total number of stickers found: " + stickers.size());
        for (int i = 0; i < stickers.size(); i++) {
            System.out.println("Sticker " + (i + 1) + ": " + stickers.get(i).getText());
        }
        if (isElementPresent(driver, By.cssSelector("div.image-wrapper"))) {
            System.out.println("Sticker is present.");
        } else {
            System.out.println("Sticker is not present.");
        }

        stop();

    }
    private static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
    public static void stop () {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        System.out.println("driver dismantled");
    }
}




