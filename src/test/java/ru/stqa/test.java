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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class test {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        driver = new ChromeDriver();
        //firefoxDriver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));


    }
    public void clickElementWithWait(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3) ); // 10 seconds timeout
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            element.click();
        } catch (Exception e) {
            System.out.println("Element not clickable: " + e.getMessage());
        }
    }


    @Test
    public void MyVeryFirstTest() throws InterruptedException {
        try{
            driver.get("http://localhost/litecart/admin/");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.logotype")));
            System.out.println("div.logotype confirmed");
            final int maxRetries = 3;

            //List<WebElement> mainMenuItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li#app-"))); // Adjust selector for main menu items

            // Iterate over each main menu item
            //List<WebElement> mainMenuItems = driver.findElements(By.cssSelector("li#app-"));

            List<WebElement> mainMenuItems = driver.findElements(By.cssSelector("li#app-"));

            for (int i = 0; i < mainMenuItems.size(); i++) {
                // Re-locate the main menu items to avoid stale element reference
                //mainMenuItems = driver.findElements(By.cssSelector("li#app-"));

                // Click on the main menu item
                mainMenuItems.get(i).click();
                System.out.println("Clicked on Main Menu Item: "); //+ h1Element.getText());

                // Check for sub-menu items
                List<WebElement> subMenuItems = driver.findElements(By.cssSelector("span.name"));

                for (int j = 0; j < subMenuItems.size(); j++) {
                    // Re-locate the sub-menu items to avoid stale element reference
                    subMenuItems = driver.findElements(By.cssSelector("span.name"));

                    // Click on the sub-menu item
                    subMenuItems.get(j).click();
                    System.out.println("Clicked on Submenu Item: ");//+ subMenuItems.getText();

                    // Check for h1 element after clicking sub-menu
                    WebElement h1Element = new WebDriverWait(driver, Duration.ofSeconds(10))
                            .until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

                    // Print the text of the h1 element
                    System.out.println("H1 Text after clicking sub-menu: " + h1Element.getText());

                    // Optionally navigate back to the main menu item if necessary
                    // driver.navigate().back();
                    // Re-locate sub-menu items if needed
                }

                // Optionally navigate back to the main menu before the next iteration
                // driver.navigate().back();
                // Re-locate main menu items if needed
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}