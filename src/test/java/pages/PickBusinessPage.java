package pages;
import org.testng.Assert;
import utils.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ReadXML;

import java.time.Duration;
import java.util.List;

/**
  *PickBusinessPage extends BasePage
 * Asserts URL from XML file based on HomeScreen selection.
 * Chooses a business(toysR'Us) for the choices available from the HomeScreen selection.
  */
public class PickBusinessPage extends BasePage{
private WebDriver driver = DriverSingleton.getDriverInstance();

    /**
     * Method that calls all the other functions.
     */
    public void chooseBusiness(){
    assertURL();
    pickBusiness();
    enterAmount();
    choose();
}

    /**
     * Creates a list of WebElements that correspond to the available businesses and chooses the 20 business.
     */
    public void pickBusiness() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
       List <WebElement>listOfBusinesses= getListOfWebElements(By.cssSelector("span[class='name bm-subtitle-1']"));

        clickOnElement(listOfBusinesses.get(19));
    }

    /**
     * After the business is chosen, inputs the value wanted for the giftcard (100שח)
     */
    public void enterAmount(){
        sendKeys(By.cssSelector("input[placeholder='הכנס סכום']"),"100");
    }

    /**
     * Asserts the URL based on the XML files "URL2" which is based on the selection from the HomeScreen.
     */
    public void assertURL(){
        try {
            Assert.assertEquals(driver.getCurrentUrl(), ReadXML.getData("URL2"));
        }
        catch(Exception e){}
    }

    /**
     * Presses the choose button.
     */
    public void choose(){
        clickOnElement(By.cssSelector("button[type='submit']"));
    }
}
