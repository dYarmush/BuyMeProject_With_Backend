package pages;
import org.testng.Assert;
import utils.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ReadXML;

import java.time.Duration;
import java.util.List;

public class PickBusinessPage extends BasePage{
private WebDriver driver = DriverSingleton.getDriverInstance();
public void chooseBusiness(){
    assertURL();
    pickBusiness();
    enterAmount();
    choose();
}
    public void pickBusiness() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
       List <WebElement>listOfBusinesses= getListOfWebElements(By.cssSelector("span[class='name bm-subtitle-1']"));

        clickOnElement(listOfBusinesses.get(19));
    }
    public void enterAmount(){
        sendKeys(By.cssSelector("input[placeholder='הכנס סכום']"),"100");
    }
    public void assertURL(){
        try {
            Assert.assertEquals(driver.getCurrentUrl(), ReadXML.getData("URL2"));
        }
        catch(Exception e){}
    }
    public void choose(){
        clickOnElement(By.cssSelector("button[type='submit']"));
    }
}
