package ru.stqa;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class AssignmentNumber10 {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String browser;

    // Parameterized constructor to initialize browser type
    public AssignmentNumber10(String browser) {
        this.browser = browser;
    }

    // Define the list of browsers to test
    @Parameterized.Parameters(name = "Browser: {0}")
    public static List<String> browsers() {
        return Arrays.asList("chrome", "firefox", "safari");
    }

    @Before
    public void start() {
        System.out.println();
        System.out.println("Starting test on " + browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }
/*public class AssignmentNumber10 {
    private WebDriver driver;
    private WebDriverWait wait;*/




    private boolean isGray(String color) {
        String[] values = color.replace("rgba(", "").replace("rgb(", "").replace(")", "").split(", ");
        int r = Integer.parseInt(values[0].trim());
        int g = Integer.parseInt(values[1].trim());
        int b = Integer.parseInt(values[2].trim());
        return r == g && g == b;
    }

    private boolean isRed(String color) {
        String[] values = color.replace("rgba(", "").replace("rgb(", "").replace(")", "").split(", ");
        int r = Integer.parseInt(values[0].trim());
        int g = Integer.parseInt(values[1].trim());
        int b = Integer.parseInt(values[2].trim());
        return r > 0 && g == 0 && b == 0;
    }

    @Test
    public void CompareMyDucks() {
        try {
            driver.get("http://localhost/litecart/en/");
            List<WebElement> links = driver.findElements(By.tagName("a"));
            for (WebElement link : links) {
                String url = link.getAttribute("href");
                if ("http://localhost/litecart/en/".equals(url)) {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("---> URL: " + url + " verified... <---");
                    break;
                }
            }

            System.out.println("--------------------------------------------------------");


            WebElement campaigns = driver.findElement(new By.ByCssSelector("#box-campaigns"));

            // -------------------= MAIN PAGE =-----------------------

            // Duck's name
            WebElement productName = campaigns.findElement(new By.ByCssSelector("a.link div.name"));
            String duckNameMainPage = productName.getAttribute("textContent");

            // Regular Price Details

            WebElement regularPrice = campaigns.findElement(new By.ByCssSelector("s.regular-price"));
            String regularPriceMainPageText = regularPrice.getAttribute("textContent");
            String regularPriceMainPageColor = regularPrice.getCssValue("color");
            String regularPriceMainPageFontSize = regularPrice.getCssValue("font-size");
            String regularPriceDecoration = regularPrice.getCssValue("text-decoration");
            String regularPriceFontWeight = regularPrice.getCssValue("font-weight");

            // Campaign Price Details

            WebElement campaignPrice = campaigns.findElement(new By.ByCssSelector("strong.campaign-price"));
            String campaignPriceMainPageText = campaignPrice.getAttribute("textContent");
            String campaignPriceMainPageColor = campaignPrice.getCssValue("color");
            String campaignPriceFontSize = campaignPrice.getCssValue("font-size");
            String campaignPriceDecoration = campaignPrice.getCssValue("text-decoration");
            String campaignPriceFontWeight = campaignPrice.getCssValue("font-weight");

            // --------------------= DETAIL PAGE =-----------------------

            productName.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.title")));
            WebElement detailProductName = driver.findElement(By.cssSelector("h1.title"));
            String duckNameDetailPage = detailProductName.getAttribute("textContent");

            // REGULAR price details
            WebElement detailRegularPrice = driver.findElement(By.cssSelector("s.regular-price"));
            String detailPageRegularPriceColor = detailRegularPrice.getCssValue("color");
            String detailPageRegularPriceText = detailRegularPrice.getAttribute("textContent");
            String detailPageRegularPriceFontSize = detailRegularPrice.getCssValue("font-size");
            String detailPageRegularPriceDecoration = detailRegularPrice.getCssValue("text-decoration");
            String detailPageRegularPriceFontWeight = detailRegularPrice.getCssValue("font-weight");

            // CAMPAIGN price details
            WebElement detailCampaignPrice = driver.findElement(By.cssSelector("strong.campaign-price"));
            String detailPageCampaignPriceText = detailCampaignPrice.getAttribute("textContent");
            String detailPageCampaignPriceColor = detailCampaignPrice.getCssValue("color");
            String detailPageCampaignPriceFontSize = detailCampaignPrice.getCssValue("font-size");
            String detailPageCampaignPriceDecoration = detailCampaignPrice.getCssValue("text-decoration");
            String detailPageCampaignPriceFontWeight = detailCampaignPrice.getCssValue("font-weight");

            System.out.println();

            // а) на главной странице и на странице товара совпадает текст названия товара

            if (duckNameMainPage.equals(duckNameDetailPage)) {

                System.out.println("a) Item's name listed on the <<Main>> Page (" + duckNameMainPage  + ") matched item's name listed on the <<Details>> Page (" + duckNameDetailPage  + ").");
            } else {
                System.out.println("a) Item's names DO NOT match. <<Main>> Page: " + duckNameMainPage + " | <<Details>> Page: " + duckNameDetailPage);
            }

            // б) на главной странице и на странице товара совпадают цены (обычная и акционная)

            if (regularPriceMainPageText.equals(detailPageRegularPriceText)) {

                System.out.println("б) Item's Regular Price listed on the <<Main>> Page (" + regularPriceMainPageText  + ") matched Regular Price listed for the same item on the <<Details>> Page (" + detailPageRegularPriceText  + ").");
            } else {
                System.out.println("б) Item names DO NOT match. <<Main>> Page: " + regularPriceMainPageText + " | <<Details>> Page: " + detailPageRegularPriceText);
            }
            if (campaignPriceMainPageText.equals(detailPageCampaignPriceText )) {

                System.out.println("   Item's Campaign Price listed on the <<Main>> Page (" + campaignPriceMainPageText  + ") matched Campaign Price listed for the same item on the <<Details>> Page (" + detailPageCampaignPriceText + ").");
            } else {
                System.out.println("Item names DO NOT match. <<Main>> Page: " + campaignPriceMainPageText + " | <<Details>> Page: " + detailPageCampaignPriceText );
            }

            //в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)

            // is gray
            if (isGray(regularPriceMainPageColor )) {
                System.out.println("в) Item's Regular Price font color on the <<Main>> Page is Gray --> " + regularPriceMainPageColor + ".");
            } else {
                System.out.println("в) Item's Regular Price font is NOT Gray on the <<Main>> Page. Actual color: " + regularPriceMainPageColor);
            }

            // text has a line-through
            if (regularPriceDecoration.contains("line-through")) {
                System.out.println("   Item's Regular Price on the <<Main>> Page is crossed out (" + regularPriceDecoration + ").");
            } else {
                System.out.println("   Item's Regular Price on the <<Main>> Page is NOT crossed out (" + regularPriceDecoration + ").");
            }

            //  text color is gray or not
            if (isGray(detailPageRegularPriceColor )) {
                System.out.println("   Item's Regular Price font color on the <<Detail>> Page is Gray --> " + detailPageRegularPriceColor + ".");
            } else {
                System.out.println("   Item's Regular Price font color on the <<Detail>> Page is NOT Gray. Actual color: " + detailPageRegularPriceColor);
            }

            // text has a line-through
            if (detailPageRegularPriceDecoration.contains("line-through")) {
                System.out.println("   Item's Regular Price font color on the <<Detail>> Page is crossed out (" + detailPageRegularPriceDecoration + ").");
            } else {
                System.out.println("   Item's Regular Price font color on the <<Detail>> Page is NOT crossed out (" + detailPageRegularPriceDecoration + ").");
            }

            //г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)

            //  main page campaign text color is red or not
            if (isRed(campaignPriceMainPageColor)) {
                System.out.println("г) Item's Campaign Price font color on the <<Main>> Page is Red --> " + campaignPriceMainPageColor + ".");
            } else {
                System.out.println("г) Item's Campaign Price font color on the <<Main>> Page is NOT Red. Actual color: " + campaignPriceMainPageColor);
            }
            // MAin page campaign text is bold
            if (campaignPriceFontWeight.equals("bold") || Integer.parseInt(campaignPriceFontWeight) >= 700) {
                System.out.println("   Item's Campaign Price font on the <<Main>> Page is bold (" + campaignPriceFontWeight + ").");
            } else {
                System.out.println("   Item's Campaign Price font on the <<Main>> Page is NOT bold (" + campaignPriceFontWeight + ").");
            }
            //  Detail page campaign text color is red or not
            if (isRed(detailPageCampaignPriceColor)) {
                System.out.println("   Item's Campaign Price font color on the <<Detail>> Page is Red --> " + detailPageCampaignPriceColor + ".");
            } else {
                System.out.println("   Item's Campaign Price font color on the <<Detail>> Page is NOT Red. Actual color: " + detailPageCampaignPriceColor);
            }

            // Detail page Campaign text is bold
            if (detailPageCampaignPriceFontWeight.equals("bold") || Integer.parseInt(detailPageCampaignPriceFontWeight) >= 700) {
                System.out.println("   Item's Campaign Price font on the <<Detail>> Page is bold (" + detailPageCampaignPriceFontWeight + ").");
            } else {
                System.out.println("   Item's Campaign Price font on the <<Detail>> Page is NOT bold (" + detailPageCampaignPriceFontWeight + ").");
            }

           // д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)

                // Main page
            double regularPriceMainPageFontSizeDouble = Double.parseDouble(regularPriceMainPageFontSize.replace("px", ""));
            double campaignPriceFontSizeDouble = Double.parseDouble(campaignPriceFontSize.replace("px", ""));
            if (campaignPriceFontSizeDouble >= regularPriceMainPageFontSizeDouble ) {
                System.out.println("д) Item's Campaign Price font on the <<Main>> Page (" + campaignPriceFontSize + ") is larger than Regular Price font (" + regularPriceMainPageFontSize + ").");
            } else {
                System.out.println("д) Item's Campaign Price font on the <<Main>> Page (" + campaignPriceFontSizeDouble + ") is NOT larger than Regular Price font (" + regularPriceMainPageFontSize + ").");
            }
                //Details page
            double detailPageRegularPriceFontSizeDouble = Double.parseDouble(detailPageRegularPriceFontSize.replace("px", ""));
            double detailPageCampaignPriceFontSizeDouble = Double.parseDouble(detailPageCampaignPriceFontSize.replace("px", ""));
            if (detailPageCampaignPriceFontSizeDouble >= detailPageRegularPriceFontSizeDouble ) {
                System.out.println("   Item's Campaign Price font on the <<Detail>> Page (" + detailPageCampaignPriceFontSize + ") is larger than Regular Price font (" + detailPageRegularPriceFontSizeDouble + ").");
            } else {
                System.out.println("   Item's Campaign Price font on the <<Detail>> Page (" + detailPageCampaignPriceFontSize + ") is NOT larger than Regular Price font (" + detailPageRegularPriceFontSizeDouble + ").");
            }

            }catch ( NoSuchElementException e) {
                    System.err.println("Element not found: " + e.getMessage());

            }finally{
            System.out.println();
            System.out.println("------------------------------------------------");
            System.out.println("--------------- browser : " + browser + "---------------");
            System.out.println("----------------- TEST COMPLETE ----------------");

            System.out.println("------------------------------------------------");
            driver.quit();
            System.out.println("                Driver dismantled");

            }

        }
    }










