package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class Chrome {
    private WebDriver driver;
    private WebDriverWait wait;
    WebDriver chromeDriver = new ChromeDriver();
    //WebDriver firefoxDriver = new FirefoxDriver();

    @Before
    public void start() {

        driver = new ChromeDriver();
        //firefoxDriver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, null);
    }

    @Test
    public void MyVeryFirstTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("test");
        driver.findElement(By.name("password")).sendKeys("test");
        Thread.sleep(2000);
        driver.findElement(By.name("login")).click();
        Thread.sleep(2000);

    }

    @After
    public void stop() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        System.out.println("da driver quit");
    }

}
