package ru.stqa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Nightly {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability("MARIONETTE", false);
        //WebDriver driver = new FirefoxDriver();
        //FirefoxBinary firefoxBinary = new FirefoxBinary(new File("/~Applications/"));
        FirefoxOptions fo = new FirefoxOptions();
        fo.setBrowserVersion("nightly");
        driver = new FirefoxDriver(fo);
        System.out.println(((RemoteWebDriver) driver).getCapabilities());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    public void MyVeryFirstTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("test");
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.name("login")).click();
        System.out.println("clicked login in nightly");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        System.out.println("da driver quit and nullified");
    }
}




