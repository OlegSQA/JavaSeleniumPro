package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Safari {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new SafariDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println(((RemoteWebDriver) driver).getCapabilities());
    }

    @Test
    public void MyVeryFirstTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("test");
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.name("login")).click();
        System.out.println("clicked login in safari");
        //wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
        System.out.println("da driver quit and nullified");
    }

}
