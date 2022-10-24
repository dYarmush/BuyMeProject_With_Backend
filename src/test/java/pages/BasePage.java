package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DriverSingleton;
import java.util.List;

/**
 * Creates base functionality for other pages to utilize.
 */
public class BasePage {
    /**
     * Using a locator sent to the method, the method returns the element.
     * @param locator to identify which element to find.
     * @return WebElement
     */
    public static WebElement getWebElement(By locator){
        return DriverSingleton.getDriverInstance().findElement(locator);
    }

    /**
     * Using a locator sent to the method, the method returns a list of WebElements.
     * @param locator to identify which elements to find.
     * @return list of WebElements
     */
    public List<WebElement> getListOfWebElements(By locator){
        return DriverSingleton.getDriverInstance().findElements(locator);
    }

    /**
     * Method receives locator and sends keys to the element found.
     * @param locator which element to find.
     * @param keysToSend which keys to send to found element.
     */
    public void sendKeys(By locator,String keysToSend){
        getWebElement(locator).sendKeys(keysToSend);
    }

    /**
     * Clicks on the element found by the given locator.
     * @param locator which element to find.
     */
    public void clickOnElement(By locator) {
          getWebElement(locator).click();

    }

    /**
     * clicks on element when given an element.
     * @param element whci element to click.
     */
        public void clickOnElement (WebElement element){
             element.click();
          }

    /**
     * Clears the field of the given element.
     * @param e element that is going to be cleared.
     */
    public static void clearField(WebElement e){
        e.clear();
    }
    }


