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

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class AssignmentNumber6 {
    private WebDriver driver;
    private WebDriverWait wait;
    //WebDriver chromeDriver = new ChromeDriver();
    //WebDriver firefoxDriver = new FirefoxDriver();

    @Before
    public void start() {

        driver = new ChromeDriver();
        //firefoxDriver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void MyVeryFirstTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        //Thread.sleep(2000);
        driver.findElement(By.name("login")).click();

        //Appearence
        driver.findElement(By.xpath("//span[contains(text(),'Appearence')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Template']")));
        driver.findElement(By.xpath("//span[contains(text(),'Template')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Template']")));
        driver.findElement(By.xpath("//span[contains(text(),'Logotype')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Logotype']")));

        //Catalog
        driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Catalog']")));
        driver.findElement(By.xpath("//span[contains(text(),'Product Groups')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Product Groups']")));
        driver.findElement(By.xpath("//span[contains(text(),'Option Groups')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Option Groups']")));
        driver.findElement(By.xpath("//span[contains(text(),'Manufacturers')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Manufacturers']")));
        driver.findElement(By.xpath("//span[contains(text(),'Suppliers')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Suppliers']")));
        driver.findElement(By.xpath("//span[contains(text(),'Delivery Statuses')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Delivery Statuses']")));
        driver.findElement(By.xpath("//span[contains(text(),'Sold Out Statuses')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Sold Out Statuses']")));
        driver.findElement(By.xpath("//span[contains(text(),'Quantity Units')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Quantity Units']")));
        driver.findElement(By.xpath("//span[contains(text(),'CSV Import/Export')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' CSV Import/Export']")));

        //Countries
        driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Countries']")));

        //Currencies
        driver.findElement(By.xpath("//span[contains(text(),'Currencies')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Currencies']")));

        //Customers
        driver.findElement(By.xpath("//span[contains(text(),'Customers')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Customers']")));
        driver.findElement(By.xpath("//span[contains(text(),'CSV Import/Export')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' CSV Import/Export']")));
        driver.findElement(By.xpath("//span[contains(text(),'Newsletter')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Newsletter']")));

        //Geo Zones
        driver.findElement(By.xpath("//span[contains(text(),'Geo Zones')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()= ' Geo Zones']")));

        //Languages
        driver.findElement(By.xpath("//span[contains(text(),'Languages')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Languages']")));
        driver.findElement(By.xpath("//span[contains(text(),'Storage Encoding')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Storage Encoding']")));
        driver.findElement(By.xpath("//span[contains(text(),'Languages')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Languages']")));

        //Modules
        driver.findElement(By.xpath("//span[contains(text(),'Modules')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Job Modules']")));
        driver.findElement(By.xpath("//span[text() ='Customer']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Customer Modules']")));
        driver.findElement(By.xpath("//span[text()='Shipping']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Shipping Modules']")));
        driver.findElement(By.xpath("//span[text() ='Payment']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Payment Modules']")));
        driver.findElement(By.xpath("//span[text() ='Order Total']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Order Total Modules']")));
        driver.findElement(By.xpath("//span[text()='Order Success']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Order Success Modules']")));
        driver.findElement(By.xpath("//span[text() ='Order Action']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Order Action Modules']")));

        //Orders
        driver.findElement(By.xpath("//span[contains(text(),'Orders')]")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Orders']")));
        driver.findElement(By.xpath("//span[text() ='Order Statuses']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Order Statuses']")));
        driver.findElement(By.xpath("//span[text()='Orders']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Orders']")));

        //Pages
        driver.findElement(By.xpath("//span[text() ='Pages']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Pages']")));
        driver.findElement(By.xpath("//span[text() ='Reports']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Monthly Sales']")));
        driver.findElement(By.xpath("//span[text()='Most Sold Products']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Most Sold Products']")));
        driver.findElement(By.xpath("//span[text() ='Most Shopping Customers']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Most Shopping Customers']")));
        driver.findElement(By.xpath("//span[text() ='Monthly Sales']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Monthly Sales']")));

        //Settings

        driver.findElement(By.xpath("//span[text() ='Settings']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Settings']")));
        driver.findElement(By.xpath("//span[text() ='Store Info']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Settings']")));
        driver.findElement(By.xpath("//span[text()='Defaults']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Settings']")));
        driver.findElement(By.xpath("//span[text() ='General']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Settings']")));
        driver.findElement(By.xpath("//span[text() ='Listings']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Settings']")));
        driver.findElement(By.xpath("//span[text() ='Images']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Settings']")));
        driver.findElement(By.xpath("//span[text() ='Checkout']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Settings']")));
        driver.findElement(By.xpath("//span[text()='Advanced']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Settings']")));
        driver.findElement(By.xpath("//span[text() ='Security']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Settings']")));

        //Slides
        driver.findElement(By.xpath("//span[text() ='Slides']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Slides']")));

        //Tax
        driver.findElement(By.xpath("//span[text() ='Tax']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Tax Classes']")));
        driver.findElement(By.xpath("//span[text()='Tax Classes']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Tax Classes']")));
        driver.findElement(By.xpath("//span[text() ='Tax Rates']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Tax Rates']")));

        //Translations
        driver.findElement(By.xpath("//span[text() ='Translations']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Search Translations']")));
        driver.findElement(By.xpath("//span[text()='Search Translations']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Search Translations']")));
        driver.findElement(By.xpath("//span[text() ='Scan Files']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Scan Files For Translations']")));
        driver.findElement(By.xpath("//span[text() ='CSV Import/Export']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' CSV Import/Export']")));

        //Users
        driver.findElement(By.xpath("//span[text() ='Users']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' Users']")));

        //vQmods
        driver.findElement(By.xpath("//span[text() ='vQmods']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' vQmods']")));
        driver.findElement(By.xpath("//ul[@class='docs']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()=' vQmods']")));




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
