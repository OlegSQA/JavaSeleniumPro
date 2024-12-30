package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class AssignmentNumber11 {
    private WebDriver driver;
    private WebDriverWait wait;
    private ISelect select;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void MyVeryFirstTest() {

        driver.get("http://localhost/litecart/");
        // Generate a unique email address
        String uniqueEmail = "abrakadabra" + System.currentTimeMillis() + "@yahoo.com";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[a[text()='New customers click here']]")));
        System.out.println("Page loaded ...");
        System.out.println("Initiating registration of " + uniqueEmail + " ...");
        driver.findElement(new By.ByXPath("//td[a[text()='New customers click here']]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#create-account.box")));
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("Abra Ka");
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Dabra");
        driver.findElement(By.cssSelector("input[name='address1']")).sendKeys("1 Police Plaza");
        driver.findElement(By.cssSelector("input[name='postcode']")).sendKeys("10038");
        driver.findElement(By.cssSelector("input[name='city']")).sendKeys("New York");

        //selecting country
        WebElement countryDropdown = driver.findElement(By.cssSelector("select[name='country_code']"));
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText("United States");
        // selecting state
        WebElement stateDropdown = driver.findElement(By.cssSelector("select[name='zone_code']"));
        Select selectState = new Select(stateDropdown);
        selectState.selectByVisibleText("New York");

        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(uniqueEmail);
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("3477426704");
        driver.findElement(By.cssSelector("input[name='newsletter']")).click();
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[name='confirmed_password']")).sendKeys("12345");
        driver.findElement(By.cssSelector("button[name='create_account']")).click();
        System.out.println( "Registration of " + uniqueEmail + " is complete...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Logout']"))).click();
        System.out.println("Logged out ...");

        // Re-log in
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[text()='Login']")));
        System.out.println("Login Page verified ...");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(uniqueEmail);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("12345");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name='login']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Logout']")));
        System.out.println("Logged into " + uniqueEmail + " account...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Logout']"))).click();
        System.out.println("Logged out ...");

        driver.quit();
        System.out.println("Driver dismantled");

    }
}

