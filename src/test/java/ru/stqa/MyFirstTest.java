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
    public void MyVeryFirstTest()  {
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("web driver");
        //driver.findElement(By.name("btnK").click());
        //driver.findElement(By.xpath("//div[@class='lJ9FBc']/center/input[@class='gNO89b']").click());
        //wait.until(titleIs("WebDriver - Search in Google"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
