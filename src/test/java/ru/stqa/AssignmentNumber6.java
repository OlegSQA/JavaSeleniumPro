package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class AssignmentNumber6 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        driver = new ChromeDriver();
        //firefoxDriver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));


    }


    @Test
    public void MyVeryFirstTest() throws InterruptedException {
        try {
            driver.get("http://localhost/litecart/admin/");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.logotype")));
            System.out.println("Page verified");
            final int maxRetries = 3;
            List<WebElement> mainMenuItems = driver.findElements(By.cssSelector("li#app-"));
            System.out.println("Main Menu contains " + (mainMenuItems.size()) + " items.");
            System.out.println("------------------------------------------------");

            for (int i = 0; i < mainMenuItems.size(); i++) {

                mainMenuItems = driver.findElements(By.cssSelector("li#app-"));
                String title = mainMenuItems.get(i).getText();
                mainMenuItems.get(i).click();
                WebElement h1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
                System.out.println("------------------------------------------------");
                System.out.println("Clicked on the Main Menu Item # " + (i+1) + " << " + title + " >>" );
                System.out.println("Page Title is " + h1.getText());
                List<WebElement> subMenuItems = driver.findElements(By.cssSelector("li[id^=doc-"));
                System.out.println("This Main Menu Item contains " + (subMenuItems.size()) + " SubMenus.");
                System.out.println("------------------------------------------------");

                for (int j = 0; j < subMenuItems.size(); j++) {
                    String subtitle = subMenuItems.get(j).getText();
                    subMenuItems.get(j).click();
                    WebElement h1Element = new WebDriverWait(driver, Duration.ofSeconds(5))
                            .until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
                    System.out.println("Clicked on Submenu Item #" + (j + 1) + " <<" + subtitle + ">> under << " + title + " >>");
                    System.out.println("Page Title changed to << " + h1Element.getText() + " >>.");
                    //System.out.println("Page Title changed to <" + h1.getText() + ">.");
                    subMenuItems = driver.findElements(By.cssSelector("li[id^=doc-"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("------------------------------------------------");
            System.out.println("------------------SUCCESS-----------------------");

            driver.quit();
            System.out.println("driver dismantled");
        }
    }
}




