package utils;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.BasePage;
import pages.HomeScreen;
import pages.PickBusinessPage;
import pages.RegistrationPage;

import java.time.Duration;

import static utils.Reporting.extent;
import static utils.Reporting.test;

/**
 * Extras extends Base page for functionality
 */
public class Extras extends BasePage {
    private static WebDriver driver=DriverSingleton.getDriverInstance();
    private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    /**
     * Gets the ball size from the loading page.
     * Needs the page to stop in order to do so. Must change the PageLoadStrategy.EAGER in the DriverSingleton to
     * PageLoadStrategy.NONE in order for the JS to stop the page.
     */
    public static void printBallSize(){
        //in order to run have to change the chromeOptions to "PageLoadStrategy.NONE"
        driver.get("https://www.buyme.co.il");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        ((JavascriptExecutor)driver).executeScript("window.stop();");
        System.out.println( BasePage.getWebElement(By.cssSelector("div[class='bounce2']")).getSize());
    }

    /**
     * Asserst alert message when trying to continue on login page without putting in any information.
     */
    public void assertAlertMessage(){
//        try{ driver.get(ReadXML.getData("URL"));}
//        catch (Exception e){}
        RegistrationPage reg=new RegistrationPage();
        reg.beginRegistration();
        clickOnElement(By.cssSelector("button[gtm='כניסה ל-BUYME']"));
        Assert.assertEquals(getWebElement(By.cssSelector("li[class='parsley-required']")).getText(),"כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה");
    }

    /**
     * Takes a screenshot of the bottom of the business page and adds it to the report.
     */
    public void businessPageScreenShot(){
        try{  driver.get(ReadXML.getData("URL2"));}
        catch (Exception c){}
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Reporting report = new Reporting();

        test = extent.createTest("Business Page Screenshot");

        test.info("details", MediaEntityBuilder.createScreenCaptureFromPath(report.takeScreenShot (driver,
                "C:\\Users\\doviy\\Box\\Automation\\BuyMeAutomationProject\\businessPagePic")).build());

    }

    /**
     * Prints the text color of the "למי לשלוח" text from Send/Receive Page in both RGB and Hex.
     */
    public void printTextColor(){
        try{        driver.get(ReadXML.getData("URL"));}
        catch(Exception e){}
        HomeScreen home = new HomeScreen();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
       // driver.navigate().refresh();
        home.chooseGift();
        PickBusinessPage pb = new PickBusinessPage();
        pb.chooseBusiness();
        String color = getWebElement(By.cssSelector("div[class='label bottom-xs']"))
                .getCssValue("color");
        System.out.println("Rgba color is "+color);
        System.out.println("Hex code is "+ Color.fromString(color).asHex());
    }
}
