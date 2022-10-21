import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class HomeScreen extends BasePage{
    private WebDriver driver = DriverSingleton.getDriverInstance();
    private WebElement element;
    public WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
    private List <WebElement> dropdowns;

    public void chooseGift(){
        choosePricePoint();
        chooseRegion();
        chooseCategory();
        pressFindMeAGift();
    }
    public void choosePricePoint()  {
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       // wait.until(ExpectedConditions.elementToBeClickable(By.className("selected-text")));
        try {Thread.sleep(4000);//couldn't get implicit or explicit wait to work correctly...
        }catch (Exception c){}
        //driver.navigate().refresh();  //clears pop up if not logging in
        dropdowns = getListOfWebElements(By.className("selected-text"));
        dropdowns.get(0).click();
       clickOnElement(By.cssSelector("li[value='2']"));

    }
    public void chooseRegion(){
        dropdowns.get(1).click();
        clickOnElement(By.cssSelector("li[value='11']"));

    }
    public void chooseCategory(){
        dropdowns.get(2).click();
        clickOnElement(By.cssSelector("li[value='75']"));
    }
    public void pressFindMeAGift(){
        clickOnElement(By.cssSelector("a[rel='nofollow']"));
    }
    public void assertURL(){
        try {
            Assert.assertEquals(driver.getCurrentUrl(), ReadXML.getData("URL2"));
        }
        catch(Exception e){}
        }
}
