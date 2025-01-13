package ru.stqa;

import net.bytebuddy.asm.Advice;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AssignmentNumber12 {private WebDriver driver;
    private WebDriverWait wait;
    private ISelect select;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }
    @Test
    public void MyVeryFirstTest() throws InterruptedException {

        //try {
            // Login to the admin panel
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Catalog']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()=' Add New Product']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()=' Enabled']"))).click();

//-----------------------------------name--------------------------------------------

        String itemName = "Abra Ka Duckbra " + System.currentTimeMillis() + "!";

        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(itemName);
        driver.findElement(By.xpath("//input[@name='code']")).click();
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys("Code");
        driver.findElement(By.cssSelector("input[name='categories[]'][data-name='Rubber Ducks']")).click();
        driver.findElement(By.cssSelector("select[name = default_category_id]")).click();
        driver.findElement(By.xpath("//option[text()='Rubber Ducks']")).click();
        driver.findElement(By.cssSelector("input[name='product_groups[]'][value='1-3']")).click();

//----------------------------quantity----------------------------------------------------

        WebElement quantityInput = driver.findElement(By.cssSelector("input[name='quantity']"));
        quantityInput.clear();
        quantityInput.sendKeys("10");

        driver.findElement(By.cssSelector("select[name='sold_out_status_id']")).click();
        driver.findElement(By.cssSelector("select[name='sold_out_status_id'] [value='2']")).click();

//---------------------------image-------------------------------------------

        WebElement pic = driver.findElement(By.cssSelector("input[type='file']"));
        String picPath = System.getProperty("user.dir") + "/src/test/java/ru/stqa/files/duck.jpg";
        pic.sendKeys(picPath);

        WebElement dateValidFrom = driver.findElement(By.cssSelector("input[name='date_valid_from']"));
        dateValidFrom.sendKeys("01052025");
        WebElement dateValidTo = driver.findElement(By.cssSelector("input[name='date_valid_to']"));
        dateValidTo.sendKeys("02062028");
//------------------------------Information tab-----------------------------
        driver.findElement(By.xpath("//a[contains(text(), 'Information')]")).click();
        driver.findElement(By.cssSelector("select[name='manufacturer_id']")).click();
        driver.findElement(By.cssSelector("select[name='manufacturer_id'] [value='1']")).click();
        driver.findElement(By.cssSelector("select[name='supplier_id']")).click();
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("keyword is keyword.");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("This description is very short.");

//----------------------------Cursor offset -------------------------------------
        WebElement textEditor = driver.findElement(By.cssSelector("div.trumbowyg"));
        Actions cursorOffset = new Actions(driver);
        int offsetX = 10;
        int offsetY = 10;
        cursorOffset.moveToElement(textEditor, offsetX, offsetY).click().sendKeys("This description is little bit longer, but not too much longer.").perform();
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("This title is a head title.");
        driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("This description is meta.");
//------------------------------Prices tab-----------------------------
        driver.findElement(By.xpath("//a[contains(text(), 'Prices')]")).click();
        driver.findElement(By.xpath("//input[@name='purchase_price']")).click();
        WebElement purchasePrice = driver.findElement(By.xpath("//input[@name='purchase_price']"));
        purchasePrice.clear();
        purchasePrice.sendKeys("5");
        driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")).click();
        driver.findElement(By.cssSelector("option[value='EUR']")).click();
        driver.findElement(By.cssSelector("option[value='USD']")).click();
        driver.findElement(By.cssSelector("input[name='prices[USD]']")).click();
        driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys("7.35");
        driver.findElement(By.cssSelector("input[name='prices[EUR]']")).click();
        driver.findElement(By.cssSelector("input[name='prices[EUR]']")).sendKeys("8.47");
        driver.findElement(By.cssSelector("button[name='save']")).click();
        System.out.println(itemName+" is being added to the catalog..");

        //---------------------------- verify product added -----------------------------------

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Catalog']"))).click();
        driver.findElement(By.cssSelector("input[name='query']")).click();
        driver.findElement(By.cssSelector("input[name='query']")).sendKeys(itemName);

        List<WebElement> searchResults = driver.findElements(By.xpath("//a[contains(text(), 'Abra Ka Duckbra')]"));
        boolean isMatchFound = false;
        for (WebElement result : searchResults) {
            if (itemName.equals(result.getText())) {
                isMatchFound = true;
                System.out.println(itemName + " added to the catalog and verified..");
                result.click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name='delete']")));
                driver.findElement(By.cssSelector("button[name='delete']")).click();
                Thread.sleep(3000);
                Alert alert = driver.switchTo().alert();
                alert.accept();
                break;

            }
        }

        if (!isMatchFound) {
            System.out.println( itemName+ " was not added to the catalog..." );
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(itemName + " deleted..");
        driver.quit();
        System.out.println("Driver dismantled");

    }
}

