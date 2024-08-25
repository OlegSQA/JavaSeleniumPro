package ru.stqa;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        driver = new ChromeDriver();
        //Clock sec = null;
        wait = new WebDriverWait(driver, null);
    }

    @Test
    public void MyVeryFirstTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("test");
        driver.findElement(By.name("password")).sendKeys("test");
        //driver.manage().wait(300); // 2sec();
        Thread.sleep(2000);
        driver.findElement(By.name("login")).click();
        //driver.findElement(By.xpath("//div[@class='lJ9FBc']/center/input[@class='gNO89b']").click());
        //wait.until(titleIs("WebDriver - Search in Google"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
