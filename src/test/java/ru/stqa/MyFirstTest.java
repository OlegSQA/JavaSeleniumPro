package ru.stqa;


import org.junit.After;
import org.junit.Assert;
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
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyFirstTest {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private WebDriver driver;
    private WebDriverWait wait;
    //WebDriver chromeDriver = new ChromeDriver();
    //WebDriver firefoxDriver = new FirefoxDriver();
    // Helper method to check if an element is present
    private boolean isElementPresent(By locator) {
        try {
            wait.until((WebDriver d) -> d.findElement(locator));
            //driver.findElement(locator);
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    private boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @Before
    public void start() {
        if (tlDriver.get() !=null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return;
        }

        driver = new ChromeDriver();
        //firefoxDriver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        WebElement btnK = driver.findElement(By.name("btnK"));
        btnK.click();
        System.out.println("clicked");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".g")));
        System.out.println("waited 2 seconds");
        assertTrue(isElementPresent(By.cssSelector(".g")));
        System.out.println("searched for .g");
        //wait.until;
        System.out.println("waited 2 seconds");
        //assertFalse(isElementPresent(By.xpath("//div[")));
        //assertFalse(areElementsPresent(By.xpath("//div[")));
        System.out.println("waited 2 sec again");
    }




    @After
    public void stop() {
        if (driver != null) {
            driver.quit();
            tlDriver.remove();  // Remove from ThreadLocal storage
            driver = null;
        }
        System.out.println("da driver quit");
    }
}






//driver.findElement(By.xpath("//div[@class='lJ9FBc']/center/input[@class='gNO89b']").click());
//wait.until(titleIs("WebDriver - Search in Google"));
//driver.manage().wait(300); // 2sec();
// Thread.sleep(2000);



//@Test
//public void MyVeryFirstTest() throws InterruptedException {
//    driver.get("http://localhost/litecart/admin/");
//    driver.findElement(By.name("username")).sendKeys("test");
//    driver.findElement(By.name("password")).sendKeys("test");
//    Thread.sleep(2000);
//    driver.findElement(By.name("login")).click();
//    Thread.sleep(2000);
